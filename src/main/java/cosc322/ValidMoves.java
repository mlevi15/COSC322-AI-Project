package cosc322;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 * Holds all the valid moves for a specified player (1 or 2) for a given state.
 * Basically a 3D array list of positions.
 * 
 * First Dimension: one of 4 queens
 * Second Dimension: The positions of possible moves for each queen
 * Third Dimension: The positions of possible arrows for each possible move of each possible queen
 */
public class ValidMoves {
    
    ArrayList<ArrayList<ArrayList<Position>>> moves;
    ArrayList<Position> queens;
    State state;
    
    public ValidMoves(State state, int player){
        this.moves = new ArrayList<ArrayList<ArrayList<Position>>>();
        this.state = state;
        if(player == 1)
            this.queens = state.getWhiteQueensPos();
        else
            this.queens = state.getBlackQueensPos();
    }
    
    public boolean isAvailable(Position p){
        //Return false if the passed position is on a border, on a players, or on an arrow
        return true;
    }
    
}
