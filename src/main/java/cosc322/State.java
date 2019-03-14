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
    private int[][] board; //the state object at its core is a 2D array that holds all game data
    private State parent; //parnet of this node
    private ArrayList<State> children; //children of this node
    private int wins; //number of wins that this branch state can result in
    private int sims; //number of total simulations in this branch
    
    public State(){
        setBoard(startingState());
        setParent(null);
        setChildren(new ArrayList<State>());
        setWins(0);
        setSims(0);
    }
    
    public State(int[][] state){
        setBoard(state);
        setParent(null);
        setChildren(new ArrayList<State>());
        setWins(0);
        setSims(0);
    }
    
    public State(BoardGameModel bgm){
        int[][] state = new int[n+1][n+1];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                switch(bgm.gameBoard[i][j]){
                    case BoardGameModel.POS_MARKED_BLACK:
                        state[i][j] = POS_MARKED_BLACK;
                        break;
                    case BoardGameModel.POS_MARKED_WHITE: 
                        state[i][j] = POS_MARKED_WHITE;
                        break;
                    case BoardGameModel.POS_MARKED_ARROW:
                        state[i][j] = POS_MARKED_ARROW;
                        break;
                    case BoardGameModel.POS_AVAILABLE:
                        state[i][j] = POS_AVAILABLE;
                        break;
                }
            }
        }
        setBoard(state);
        setParent(null);
        setChildren(new ArrayList<State>());
        setWins(0);
        setSims(0);
    }
    
    public void addChild(State state){
        this.children.add(state);
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
    public int checkGoalState(){
        boolean win = true;
        boolean lose = true;
        
        ArrayList<Position> w = getWhiteQueensPos();
        ArrayList<Position> b = getBlackQueensPos();
        Iterator wIter = w.iterator();
        Iterator bIter = b.iterator();
        
         while(bIter.hasNext()){
            if(canPlayerMove((Position)bIter.next())){
                win = false;
            }
        }
         
        while(wIter.hasNext()){
            if(canPlayerMove((Position)wIter.next())){
                lose = false;
            }
        }
       
        if(win)
            return 1;
        else if(lose)
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
        String s = "Player: " + p.toString() + " || Neighbors: " + neigh.toString() + " || Values: ";
        Iterator it = neigh.iterator();
        while(it.hasNext())
            s += this.getValue((Position)it.next()) + ", ";
        u.print(s);
        return neigh;
    }
    
    public ArrayList<Position> getBlackQueensPos(){
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
    
    public ArrayList<Position> getWhiteQueensPos(){
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
    
    public void setBoard(int[][] board){
        this.board = board;
    }
    
    public int[][] getBoard(){
        return this.board;
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

