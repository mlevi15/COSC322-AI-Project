package cosc322;

import java.util.ArrayList;

public class Queen {
    
    Position currentQueen;
    State state;
    ArrayList<Position> moves;
    
    public Queen(State state, Position currentQueen){
        this.state = state;
        this.currentQueen = currentQueen;
    }
    
}
