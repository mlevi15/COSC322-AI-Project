package cosc322;

import java.util.ArrayList;

public class Arrow {
    
    Position currentQueenPos;
    State state;
    ArrayList<State> shots;
    Utility u = new Utility();
    
    public Arrow(Position currentQueenPos, State state){
        this.currentQueenPos = currentQueenPos;
        this.state = state;
        setShots();
    }
    
    public void setShots(){
        this.shots = new ArrayList<State>();
        int playersTurn = this.state.getPlayersTurn();
        
        //iterate to the left of queen position
        for(int i = currentQueenPos.j - 1; i > 0; i--){
            Position p = new Position(currentQueenPos.i, i);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }else
                break;
        }
        
        //iterate to the right of queen position
        for(int i = currentQueenPos.j + 1; i < 11; i++){
            Position p = new Position(currentQueenPos.i, i);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }
            else
                break;
        }
        
        //iterate up from the queens position
        for(int i = currentQueenPos.i - 1; i > 0; i--){
            Position p = new Position(i, currentQueenPos.j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }
            else
                break;
        }
        
        //iterate down from the queens position
        for(int i = currentQueenPos.i + 1; i < 11; i++){
            Position p = new Position(i, currentQueenPos.j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }
            else
                break;
        }
        
        //iterate up and left from the queens position
        int i = currentQueenPos.i - 1;
        int j = currentQueenPos.j - 1;
        while(i > 0 && j > 1){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }  
            i--;
            j--;
        }
        
        //iterate up and right from the queens position
        i = currentQueenPos.i - 1;
        j = currentQueenPos.j + 1;
        while(i > 0 && j < 11){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }  
            i--;
            j++;
        }
        
        //iterate down and right from the queens position
        i = currentQueenPos.i + 1;
        j = currentQueenPos.j + 1;
        while(i < 11 && j < 11){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }  
            i++;
            j++;
        }
        
         //iterate down and left from the queens position
        i = currentQueenPos.i + 1;
        j = currentQueenPos.j - 1;
        while(i < 11 && j > 0){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn);
                s.setValue(p, -1);
                this.shots.add(s);
            }  
            i++;
            j--;
        }
    }
    
    public ArrayList<State> getShots(){
        return shots;
    }
    
}
