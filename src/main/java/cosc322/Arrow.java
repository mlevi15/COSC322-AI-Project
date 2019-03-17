package cosc322;

import java.util.ArrayList;

public class Arrow {
    
    Position newQueen;
    State state;
    ArrayList<Position> shots;
    
    public Arrow(State state, Position newQueen){
        this.state = state;
        this.newQueen = newQueen;
    }
    
}
