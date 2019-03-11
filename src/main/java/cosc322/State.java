package cosc322;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */

public class State {
    static int n = 10;
    private String[][] state; //the state object at its core is a 2D array that holds all game data
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
    
    public State(String[][] state){
        setState(state);
        setParent(null);
        setChildren(new ArrayList<State>());
        setWins(0);
        setSims(0);
    }
    
    public State(BoardGameModel bgm){
        String[][] state = new String[n][n];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                state[i][j] = bgm.gameBoard[i][j];
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
    
    public static String[][] startingState(){
        String[][] state = new String[n][n];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                state[i][j] = BoardGameModel.POS_AVAILABLE;
            }
        }
        
        String tagB = BoardGameModel.POS_MARKED_BLACK;
        String tagW = BoardGameModel.POS_MARKED_WHITE;

        state[1][4] = tagW;
        state[1][7] = tagW;
        state[3][1] = tagW;
        state[3][10] = tagW;

        state[8][1] = tagB;
        state[8][10] = tagB;
        state[10][4] = tagB;
        state[10][7] = tagB;
        
        return state;
    }
    
    
    /**GETTERS AND SETTERS**/
    public void setState(String[][] state){
        this.state = state;
    }
    
    public String[][] getState(){
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

