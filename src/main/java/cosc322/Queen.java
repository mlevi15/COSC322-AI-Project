package cosc322;

import java.util.ArrayList;
import java.util.Arrays;

public class Queen {
    
    Position currentQueenPos;
    State state;
    ArrayList<State> moves;
    Utility u = new Utility();
    
    public Queen(State state, Position currentQueen){
        this.currentQueenPos = currentQueen;
        this.state = state;
        setMoves();
    }
    
    public void setMoves(){
        this.moves = new ArrayList<State>();
        int playersTurn = this.state.getPlayersTurn();
        
        //iterate to the left of queen position
        for(int i = currentQueenPos.j - 1; i > 0; i--){
            Position p = new Position(currentQueenPos.i, i);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;
        }
        
        //iterate to the right of queen position
        for(int i = currentQueenPos.j + 1; i < 11; i++){
            Position p = new Position(currentQueenPos.i, i);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;
        }
        
        //iterate up from the queens position
        for(int i = currentQueenPos.i - 1; i > 0; i--){
            Position p = new Position(i, currentQueenPos.j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;
        }
        
        //iterate down from the queens position
        for(int i = currentQueenPos.i + 1; i < 11; i++){
            Position p = new Position(i, currentQueenPos.j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;
        }
        
        //iterate up and left from the queens position
        int i = currentQueenPos.i - 1;
        int j = currentQueenPos.j - 1;
        while(i > 0 && j > 1){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;
            i--;
            j--;
        }
        
        //iterate up and right from the queens position
        i = currentQueenPos.i - 1;
        j = currentQueenPos.j + 1;
        while(i > 0 && j < 11){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;
            i--;
            j++;
        }
        
        //iterate down and right from the queens position
        i = currentQueenPos.i + 1;
        j = currentQueenPos.j + 1;
        while(i < 11 && j < 11){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;  
            i++;
            j++;
        }
        
         //iterate down and left from the queens position
        i = currentQueenPos.i + 1;
        j = currentQueenPos.j - 1;
        while(i < 11 && j > 0){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0){
                State s = new State(u.copyBoard(this.state.getBoard()), playersTurn + 1);
                s.setValue(currentQueenPos, 0);
                s.setValue(p, playersTurn);
                Arrow a = new Arrow(p, s);
                ArrayList<State> shots = a.getShots();
                for(int k = 0; k < shots.size(); k++){
                    this.moves.add(shots.get(k));
                }
            }else
                break;  
            i++;
            j--;
        }
    }
    
    public ArrayList<State> getMoves(){
        return this.moves;
    }
}
