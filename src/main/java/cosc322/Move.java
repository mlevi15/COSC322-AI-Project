/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc322;

/**
 *
 * @author Kevin
 */
public class Move {
    
    Position oldQueen;
    Position newQueen;
    Position arrow;
    
    public Move(Position oldQueen, Position newQueen, Position arrow){
        this.oldQueen = oldQueen;
        this.newQueen = newQueen;
        this.arrow = arrow;
    }
}
