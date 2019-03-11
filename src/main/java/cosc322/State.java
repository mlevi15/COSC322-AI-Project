package cosc322;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */

public class State {
    public static final int POS_MARKED_BLACK = 2;
    public static final int POS_MARKED_WHITE = 1;
    public static final int POS_AVAILABLE = 0;
    public static final int POS_MARKED_ARROW = -1;
    
    static int n = 10;
    private int[][] state; //the state object at its core is a 2D array that holds all game data
    private State parent; //parnet of this node
    private ArrayList<State> children; //children of this node
    private int wins; //number of wins that this branch state can result in
    private int sims; //number of total simulations in this branch
    
    public State(){
        setState(startingState());
        setParent(null);
        setChildren(new ArrayList<State>());
        setWins(0);
        setSims(0);
    }
    
    public State(int[][] state){
        setState(state);
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
        setState(state);
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
    
    
    /**GETTERS AND SETTERS**/
    public void setState(int[][] state){
        this.state = state;
    }
    
    public int[][] getState(){
        return this.state;
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
        String b = null;

        for(int i = 1; i < 11; i++){
            for(int j = 1; j< 11; j++){
		b = b + this.state[i][j] + " ";
	    }
	    b = b + "\n";
        }  	  
        return b;
    }	
}

