package cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class RandomMove {
    Utility u = new Utility();
    Random r = new Random();
    
    State state;
    Position currentQueen;
    State ranState;
    
    
    public RandomMove(State state, Position currentQueen){
        this.state = state;
        this.currentQueen = currentQueen;
        this.ranState = getRandomMove();
    }
    
    private State getRandomMove(){
        int count = 0;
        State s = null;
        
        boolean[] canMove = {true, true, true, true, true, true, true, true}; //if array is all false, there is no possible move
        
        while(s == null && count < 8){
            int randDir = r.nextInt(8);
            switch(randDir){
                case 0:
                    if(canMove[0]){
                        canMove[0] = false;
                        s = up();
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 1:
                    if(canMove[1]){
                        canMove[1] = false;
                        s = right();
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 2:
                    if(canMove[2]){
                        canMove[2] = false;
                        s = down();
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 3:
                    if(canMove[3]){
                        canMove[3] = false;
                        s = left();
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 4:
                    if(canMove[4]){
                        canMove[4] = false;
                         s = upLeft();
                    }
                    if(s == null)
                        break;
                    else
                        return s;
                case 5:
                    if(canMove[5]){
                        canMove[5] = false;
                        s = upRight();
                    }
                    if(s == null) 
                        break;
                    else 
                        return s;
                case 6:
                    if(canMove[6]){
                        canMove[6] = false;
                        s = downRight();
                    }
                    if(s == null) 
                        break;
                    else
                        return s;
                case 7:
                    
                    if(canMove[7]){
                        canMove[7] = false;
                        s = downLeft();
                    }
                    if(s == null) 
                        break;
                    else
                        return s;
            }
            count++;
        }
        
        if(count < 8)
            return s;
        else
            return checkAllDirections(canMove);       
    }
    
    public State checkAllDirections(boolean[] canMove){
        //Check to see if all array value are already false, if so just return null because there are no moves
        boolean defCantMove = true;
        for(int i = 0; i < canMove.length; i++)
            if(canMove[i])
                defCantMove = false;
        if(defCantMove)
            return null;
        
        State s = null;
        for(int i = 0; i < canMove.length; i++){
            if(canMove[i]){
                switch(i){
                    case 0:
                        s = up();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 1:
                        s = right();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 2:
                        s = down();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 3:
                        s = left();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 4:
                        s = upLeft();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 5:
                        s = upRight();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 6:
                        s = downRight();
                        if(s == null)
                            break;
                        else
                            return s;
                    case 7:
                        s = downLeft();
                        if(s == null)
                            break;
                        else
                            return s;
                } 
            }
        }
        return null;
    }
    
    public State up(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate up from the queens position
        for(int i = currentQueen.i - 1; i > 0; i--){
            Position p = new Position(i, currentQueen.j);
           if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
        }
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
    public State right(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate to the right of queen position
        for(int i = currentQueen.j + 1; i < 11; i++){
            Position p = new Position(currentQueen.i, i);
            if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
        }
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
    public State down(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate down from the queens position
        for(int i = currentQueen.i + 1; i < 11; i++){
            Position p = new Position(i, currentQueen.j);
            if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
        }
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
    public State left(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate to the left of queen position
        for(int i = currentQueen.j - 1; i > 0; i--){
            Position p = new Position(currentQueen.i, i);
            if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
        }
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
    public State upLeft(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate up and left from the queens position
        int i = currentQueen.i - 1;
        int j = currentQueen.j - 1;
        while(i > 0 && j > 0){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
            i--;
            j--;
        }
        
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
    public State upRight(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate up and right from the queens position
        int i = currentQueen.i - 1;
        int j = currentQueen.j + 1;
        while(i > 0 && j < 11){
            Position p = new Position(i, j);
              if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
            i--;
            j++;
        }
        
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
    public State downRight(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
        //iterate down and right from the queens position
        int i = currentQueen.i + 1;
        int j = currentQueen.j + 1;
        while(i < 11 && j < 11){
            Position p = new Position(i, j);
              if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
            i++;
            j++;
        }
        
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }
    
   
    public State downLeft(){
        ArrayList<Position> moves = new ArrayList<Position>();
        
         //iterate down and left from the queens position
        int i = currentQueen.i + 1;
        int j = currentQueen.j - 1;
        while(i < 11 && j > 0){
            Position p = new Position(i, j);
            if(this.state.getValue(p) == 0)
                moves.add(p);
            else
                break;  
            i++;
            j--;
        }
        
        if(moves.size() > 0){
            return getShot(moves);
        }else
            return null;
    }  
    
    public State getShot(ArrayList<Position> moves){
            int rand = r.nextInt(moves.size());
            Position newQueen = moves.get(rand);
            RandomShot shot = new RandomShot(currentQueen, newQueen, this.state);
            return shot.ranState;
    }
}