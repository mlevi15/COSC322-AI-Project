package cosc322;

import java.util.ArrayList;
import java.util.Iterator;

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
    
    static int n = 10;
    private int playersTurn; //the player who is analysing this board to make a move (1 or 2, black or white)
    private int[][] board; //the state object at its core is a 2D array that holds all game data
    private ArrayList<Position> blackQueens; //the positions of all black queens on the current board
    private ArrayList<Position> whiteQueens; //the positions of all white queens on the current board
    private State parent; //parnet of this node
    private ArrayList<State> children; //children of this node
    private int wins; //number of wins that this branch state can result in
    private int sims; //number of total simulations in this branch
    
    public State(){
        this.playersTurn = 1;
        this.board = startingState();
        this.blackQueens = getBlackQueensPos();
        this.whiteQueens = getWhiteQueensPos();
        this.parent = null;
        this.children = null;
        this.wins = 0;
        this.sims = 0;
        
    }
    
    public State(int[][] board, int playersTurn){
        this.playersTurn = playersTurn;
        this.board = board;
        this.blackQueens = getBlackQueensPos();
        this.whiteQueens = getWhiteQueensPos();
        this.parent = null;
        this.children = null;
        this.wins = 0;
        this.sims = 0;
    }
    
    public State(BoardGameModel bgm, int playerTurn){
        int[][] board = new int[n+1][n+1];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
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
        this.playersTurn = playersTurn;
        this.board = board;
        this.blackQueens = getBlackQueensPos();
        this.whiteQueens = getWhiteQueensPos();
        this.parent = null;
        this.children = null;
        this.wins = 0;
        this.sims = 0;
    }
    
    public static int[][] startingState(){
        int[][] state = new int[n+1][n+1];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                state[i][j] = POS_AVAILABLE;
            }
        }

        state[1][4] = POS_MARKED_WHITE;
        state[1][7] = POS_MARKED_WHITE;
        state[3][1] = POS_MARKED_WHITE;
        state[3][10] = POS_MARKED_WHITE;

        state[8][1] = POS_MARKED_BLACK;;
        state[8][10] = POS_MARKED_BLACK;;
        state[10][4] = POS_MARKED_BLACK;;
        state[10][7] = POS_MARKED_BLACK;
        
        return state;
    }
    
    /**
    * checkGoalState checks if this state is a game 
    * terminating/goal state. If the state is a winning state a 1 is 
    * returned, if it is a losing state a -1 is returned, and if it is neither or a 
    * non-goal state a 0 is returned.
    **/
    public int checkGoalState(int player){
        boolean canBlackMove = false;
        boolean canWhiteMove = false;
        Iterator wIter = this.whiteQueens.iterator();
        Iterator bIter = this.blackQueens.iterator();
        
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
            return -1;
        else if(player == 2 && !canWhiteMove)
            return -1;
        else
            return 0;
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
    
    public ArrayList<Position> getBlackQueensPos(){
        ArrayList<Position> pos = new ArrayList<Position>();
        int[][] board = getBoard();
        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < n + 1; j++){
                if(board[i][j] == 1)
                    pos.add(new Position(i, j));
            }
        }
        return pos;
    }
    
    public ArrayList<Position> getWhiteQueensPos(){
        ArrayList<Position> pos = new ArrayList<Position>();
        int[][] board = getBoard();
        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < n + 1; j++){
                if(board[i][j] == 2)
                    pos.add(new Position(i, j));
            }
        }
        return pos;
    }
    
    public ArrayList<State> getPossibleMoves(){
        ArrayList<State> moves = new ArrayList<State>();
        ArrayList<Position> queenPos;
        if(playersTurn == 1)
            queenPos = this.blackQueens;
        else
            queenPos = this.whiteQueens;
        
        ArrayList<Queen> queens = new ArrayList<Queen>();
        queens.add(new Queen(this, queenPos.get(0)));
        queens.add(new Queen(this, queenPos.get(1)));
        queens.add(new Queen(this, queenPos.get(2)));
        queens.add(new Queen(this, queenPos.get(3)));
        
        for(int i = 0; i < queens.size(); i++){
            Queen q = queens.get(i);
            ArrayList<State> queenMoves = q.getMoves();
            for(int j = 0; j < queenMoves.size(); j++){
                moves.add(queenMoves.get(j));
            }
        }
        return moves;
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
    
    public void setPlayersTurn(int playerTurn){
        this.playersTurn = playersTurn;
    }
    
    public int getPlayersTurn(){
        return this.playersTurn;
    }
    
    public void setBoard(int[][] board){
        this.board = board;
    }
    
    public int[][] getBoard(){
        return this.board;
    }
    
    public void setBlackQueens(ArrayList<Position> queens){
        this.blackQueens = queens;
    }
    
    public ArrayList<Position> getBlackQueens(){
        return blackQueens;
    }
    
     public void setWhiteQueens(ArrayList<Position> queens){
        this.whiteQueens = queens;
    }
    
    public ArrayList<Position> getWhiteQueens(){
        return whiteQueens;
    }
    
    public void setParent(State parent){
        this.parent = parent;
    }
    
    public State getParent(){
        return this.parent;
    }
    
    public void setChildren(ArrayList<State> children){
        this.children = children;
    }
    
    public ArrayList<State> getChildren(){
        return this.children;
    }
    
    public void setWins(int wins){
        this.wins = wins;
    }
    
    public int getWins(){
        return this.wins;
    }
    
    public void setSims(int sims){
        this.sims = sims;
    }
    
    public int getSims(){
        return this.sims;
    }
    
    public String toString(){
        String b = "";

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
		b = b + this.board[i][j] + " ";
	    }
	    b = b + "\n";
        }  	  
        return b;
    }	
}

