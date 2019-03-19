package cosc322;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kevin
 */

public class State {
    public static final int POS_MARKED_BLACK = 2;
    public static final int POS_MARKED_WHITE = 1;
    public static final int POS_AVAILABLE = 0;
    public static final int POS_MARKED_ARROW = -1;
    Utility u = new Utility();
    
    //Stats of run
    static int numSims = 0;
    static int numExp = 0;
    
    static Random r = new Random();
    static int numActions = 5;
    static double ep = 1e-6;
    double sims, wins;
    
    int player; //the player that we are assigned at the beginning of the game. 1 or 2 for every state in a game
    int turn; //the player who is analysing this board to make a move (1 or 2, black or white)
    int[][] board; //the state object at its core is a 2D array that holds all game data
    ArrayList<State> children;
    ArrayList<Position> queens;
    
    public State(int player, int turn){
        this.player = player;
        this.turn = turn;
        this.board = startingState();
        this.children = null;
        this.queens = getPlayersQueens();
        this.wins = 0;
        this.sims = 0;
    }
    
    public State(int player, int turn, int[][] board){
        this.player = player;
        this.turn = turn;
        this.board = board;
        this.children = null;
        this.queens = getPlayersQueens();
        this.wins = 0;
        this.sims = 0;
    }
    
    public State(int player, int turn, BoardGameModel bgm){
        int[][] board = new int[n+1][n+1];
        for(int i = 1; i < 11; i++){
            for(int j = 1; j < 11; j++){
                switch(bgm.gameBoard[i][j]){
                    case BoardGameModel.POS_MARKED_BLACK:
                        board[i][j] = POS_MARKED_BLACK;
                        break;
                    case BoardGameModel.POS_MARKED_WHITE: 
                        board[i][j] = POS_MARKED_WHITE;
                        break;
                    case BoardGameModel.POS_MARKED_ARROW:
                        board[i][j] = POS_MARKED_ARROW;
                        break;
                    case BoardGameModel.POS_AVAILABLE:
                        board[i][j] = POS_AVAILABLE;
                        break;
                }
            }
        }
        this.player = player;
        this.turn = turn;
        this.board = board;
        this.children = null;
        this.queens = getPlayersQueens();
        this.wins = 0;
        this.sims = 0;
    }
    
    public void selectMove(){
        State current = this;
        List<State> visited = new LinkedList<State>();
        visited.add(current);
        
        while(!current.isTerminal()){
            current = current.select();
            u.print("Adding: \n" + current);
            visited.add(current);
        }
        
        current.expand();
        
        State selected = current.select();
        double winOrLoss = simulate(selected);
        for(State node : visited){
            node.updateWinsSims(winOrLoss);
            u.print("Wins: " + current.wins + " Sims " + current.sims);
        }
    }
    
    public State select(){
        State selected = null;
        double best = Double.MIN_VALUE;
        
        Iterator itr = this.children.iterator();
        State child;
        while(itr.hasNext()){
            child = (State)itr.next();
            double upperConfidence = child.wins / (child.sims + ep) + Math.sqrt(Math.log(sims + 1) / (child.sims + ep)) + r.nextDouble() * ep;
            if (upperConfidence > best){
                selected = child;
                best = upperConfidence;
            }
            
        }
        u.print("Returning: \n" + selected);
        return selected;
    }
    
    public boolean isTerminal(){
        return this.children == null;
    }
    
    public void expand(){
        numExp++;
        u.print("EXPAND");
        u.print("================================================");
        this.children = new ArrayList<State>();
        
        for(int i = 0; i < numActions; i++){
            State child = this.getRandomChild();
            u.print(child.toString());
            children.add(child);
        }
        u.print("================================================");
        u.print("END EXPAND \n");
    }
    
    public void updateWinsSims(double winOrLoss){
        this.sims++;
        if(winOrLoss == 1){
            this.wins++;
        }
    }
    
    public int simulate(State state){
        this.numSims++;
        u.print("SIMULATION");
        u.print("================================================");
        State curr = state;
        curr.children = new ArrayList<State>();
        State child = curr.getRandomChild();
        
        while(child != null){
            curr.children.add(child);
            curr = child;
            curr.children = new ArrayList<State>();
            child = curr.getRandomChild();
        }
        int status = this.checkGoalState();
        
        u.print("================================================");
        u.print("END SIMULATION \n");
        
        return status;
    }
    
    public State getRandomChild(){
        int nextTurn;
        if(this.turn == 1)
            nextTurn = 2;
        else
            nextTurn = 1;
        
        State currState = new State(this.player, nextTurn, u.copyBoard(this.board));
        
        int count = 0;
        State s = null;
        boolean[] canMove = {true, true, true, true};
        
        while(s == null && count < 4){
            int randQueen = r.nextInt(4);
            switch(randQueen){
                case 0:
                    if(canMove[0]){
                        canMove[0] = false;
                        s = new RandomMove(currState, currState.queens.get(0)).ranState;
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 1:
                    if(canMove[1]){
                        canMove[1] = false;
                        s = new RandomMove(currState, currState.queens.get(1)).ranState;
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 2:
                    if(canMove[2]){
                        canMove[2] = false;
                        s = new RandomMove(currState, currState.queens.get(2)).ranState;
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 3:
                    if(canMove[3]){
                        canMove[3] = false;
                        s = new RandomMove(currState, currState.queens.get(3)).ranState;
                    }
                    if(s == null)
                        break;
                    else
                        return s;
            }
            count++;
        }
        boolean defCantMove = true;
        for(boolean move : canMove)
            if(move)
                defCantMove = false;
        if(defCantMove)
            return null;
        for(int i = 0; i < this.queens.size(); i++){
            if(canMove[i]){
                s = new RandomMove(currState, this.queens.get(i)).getRandomMove();
                if(s != null)
                    return s;
            }
        }
        return null;
    }
    
    public static int[][] startingState(){
        int[][] state = new int[11][11];
        for(int i = 1; i < 11 + 1; i++){
            for(int j = 1; j < 11 + 1; j++){
                state[i][j] = POS_AVAILABLE;
            }
        }

        state[1][4] = POS_MARKED_WHITE;
        state[1][7] = POS_MARKED_WHITE;
        state[3][1] = POS_MARKED_WHITE;
        state[3][10] = POS_MARKED_WHITE;

        state[8][1] = POS_MARKED_BLACK;
        state[8][10] = POS_MARKED_BLACK;
        state[10][4] = POS_MARKED_BLACK;
        state[10][7] = POS_MARKED_BLACK;
        
        return state;
    }
    
    public int checkGoalState(){
        boolean canBlackMove = false;
        boolean canWhiteMove = false;
        Iterator wIter = this.getWhiteQueens().iterator();
        Iterator bIter = this.getBlackQueens().iterator();
        
         while(bIter.hasNext()){
             if(canPlayerMove((Position)bIter.next())) 
                 canBlackMove = true;
        }
         
        while(wIter.hasNext()){
            if(canPlayerMove((Position)wIter.next()))
                canWhiteMove = true;
        }
        
        if(player == 1 && !canWhiteMove)
            return 1;
        else if(player == 2 && !canBlackMove)
            return 1;
        else if(player == 1 && !canBlackMove)
            return 0;
        else if(player == 2 && !canWhiteMove)
            return 0;
        else
            return -1;
    }
    
    /**
     * canPLayerMove checks all 8-connected neighbors a passed Position 
     **/
    public boolean canPlayerMove(Position p){
        boolean canPlayerMove = false;
        Position topLeft, top, topRight, left, right, botLeft, bot, botRight;
        ArrayList<Position> neigh = getNeighborsPos(p);
        Iterator iter = neigh.iterator();
        while(iter.hasNext()){
            if(this.getValue((Position)iter.next()) == 0){
               canPlayerMove = true;
               break;
            }
        }
        return canPlayerMove;
    }
    
    public ArrayList<Position> getNeighborsPos(Position p){
        ArrayList<Position> neigh = new ArrayList<Position>();
        Position topLeft, top, topRight, left, right, botLeft, bot, botRight;
        topLeft = new Position(p.i - 1, p.j - 1);
        top = new Position(p.i - 1, p.j);
        topRight = new Position(p.i - 1, p.j + 1);
        left = new Position(p.i, p.j - 1);
        right = new Position(p.i, p.j + 1);
        botLeft = new Position(p.i + 1, p.j - 1);
        bot = new Position(p.i + 1, p.j);
        botRight = new Position(p.i + 1, p.j + 1);
        
        //check all border cases
        if(p.i == 1 && p.j == 1){
            //top left corner
            neigh.add(right);
            neigh.add(botRight);
            neigh.add(bot);
        }else if(p.i == 1 && p.j == 10){
            //top right corner
            neigh.add(left); 
            neigh.add(botLeft);
            neigh.add(bot);
        }else if(p.i == 10 && p.j == 1){
            //bottom left corner
            neigh.add(top);
            neigh.add(topRight);
            neigh.add(right);
        }else if(p.i == 10 && p.j == 10){
            //bottom right corner
            neigh.add(top);
            neigh.add(topLeft);
            neigh.add(left);
        }else if(p.i == 1){
            //top border
            neigh.add(left);
            neigh.add(botLeft);
            neigh.add(bot);
            neigh.add(botRight);
            neigh.add(right);
        }else if(p.i == 10){
            //bottom border
            neigh.add(left);
            neigh.add(topLeft);
            neigh.add(top);
            neigh.add(topRight);
            neigh.add(right);
        }else if(p.j == 1){
            //left border
            neigh.add(top);
            neigh.add(topRight);
            neigh.add(right);
            neigh.add(botRight);
            neigh.add(bot);
        }else if(p.j == 10){
            //right border
            neigh.add(top);
            neigh.add(topLeft);
            neigh.add(left);
            neigh.add(botLeft);
            neigh.add(bot);
        }else{
            neigh.add(topLeft);
            neigh.add(top);
            neigh.add(topRight);
            neigh.add(right);
            neigh.add(botRight);
            neigh.add(bot);
            neigh.add(botLeft);
            neigh.add(left);
        }
        
        /**FOR TESTING PURPOSES-------------------------------------------------------------------------------------------------
            String s = "Player: " + p.toString() + " || Neighbors: " + neigh.toString() + " || Values: ";
            Iterator it = neigh.iterator();
            while(it.hasNext())
                s += this.getValue((Position)it.next()) + ", ";
            u.print(s); 
        -----------------------------------------------------------------------------------------------------------------------**/
        
        return neigh;
    }
    
    public ArrayList<Position> getPlayersQueens(){
        if(this.turn == 1)
            return this.getBlackQueens();
        else
            return this.getWhiteQueens();
    }
    
    public ArrayList<Position> getBlackQueens(){
        ArrayList<Position> pos = new ArrayList<Position>();
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(this.board[i][j] == 1)
                    pos.add(new Position(i, j));
            }
        }
        return pos;
    }
    
    public ArrayList<Position> getWhiteQueens(){
        ArrayList<Position> pos = new ArrayList<Position>();
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(this.board[i][j] == 2)
                    pos.add(new Position(i, j));
            }
        }
        return pos;
    }
    
    /**GETTERS AND SETTERS**/
    public int getValue(Position p){
        return board[p.i][p.j];
    }
    
    public int getValue(int i, int j){
        return board[i][j];
    }
    
    public void setValue(Position p, int v){
        this.board[p.i][p.j] = v;
    }
    
    public void setValue(int i, int j, int v){
        this.board[i][j] = v;
    }
    
    public String toString(){
        String b = "";

        for(int i = 1; i < 11; i++){
            for(int j = 1; j < 11; j++){
		b = b + this.board[i][j] + " ";
	    }
	    b = b + "\n";
        }  	  
        return b;
    }	
}

