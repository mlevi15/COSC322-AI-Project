package cosc322;

import java.util.ArrayList;
import java.util.Random;

public class RandomShot {
    
    Utility u = new Utility();
    Random r = new Random();
    
    State state;
    Position currentQueen;
    State ranState;

    public RandomShot(Position oldQueenPos, Position newQueenPos, State state){
        this.currentQueen = newQueenPos;
        this.state = state;
        this.state.setValue(oldQueenPos, 0);
        this.state.setValue(newQueenPos, this.state.turn);
        this.state.queens = this.state.getPlayersQueens();
        this.ranState = getRandomShot();
    }
    
    public State getRandomShot(){
        int count = 0;
        State s = null;
        
        while(s == null && count < 8){
            int randDir = r.nextInt(8);
            switch(randDir){
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
            count++;
        }
        return checkAllDirections();       
    }
    
    public State checkAllDirections(){
        State s = state;
        for(int i = 0; i < 8; i++){
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
        return null;
    }
    
    public State up(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate up from the queens position
        for(int i = currentQueen.i - 1; i > 0; i--){
            Position p = new Position(i, currentQueen.j);
            if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
    
    public State right(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate to the right of queen position
        for(int i = currentQueen.j + 1; i < 11; i++){
            Position p = new Position(currentQueen.i, i);
            if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
    
    public State down(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate down from the queens position
        for(int i = currentQueen.i + 1; i < 11; i++){
            Position p = new Position(i, currentQueen.j);
           if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
    
    public State left(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate to the left of queen position
        for(int i = currentQueen.j - 1; i > 0; i--){
            Position p = new Position(currentQueen.i, i);
            if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }    
    
    public State upLeft(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate up and left from the queens position
        int i = currentQueen.i - 1;
        int j = currentQueen.j - 1;
        while(i > 0 && j > 0){
            Position p = new Position(i, j);
               if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
            i--;
            j--;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
    
    public State upRight(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate up and right from the queens position
        int i = currentQueen.i - 1;
        int j = currentQueen.j + 1;
        while(i > 0 && j < 11){
            Position p = new Position(i, j);
              if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
            i--;
            j++;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
    
    public State downRight(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
        //iterate down and right from the queens position
        int i = currentQueen.i + 1;
        int j = currentQueen.j + 1;
        while(i < 11 && j < 11){
            Position p = new Position(i, j);
              if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
            i++;
            j++;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
    
    public State downLeft(){
        ArrayList<Position> shots = new ArrayList<Position>();
        
         //iterate down and left from the queens position
        int i = currentQueen.i + 1;
        int j = currentQueen.j - 1;
        while(i < 11 && j > 0){
            Position p = new Position(i, j);
                if(this.state.getValue(p) == 0)
                shots.add(p);
            else
                break;
            i++;
            j--;
        }
        
        if(shots.size() > 0){
            int rand = r.nextInt(shots.size());
            Position randPos = shots.get(rand);
            State s = state;
            s.setValue(randPos, -1);
            return s;
        }else {
            return null;
        }
    }
}
