/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc322;

/**
 * The Position class is a data structure that holds two integers
 * signifying the position of a square on a game board
 * @author Kevin
 */
public class Position {
    int i;
    int j;
    
    public Position(int i, int j){
        this.i = i;
        this.j = j;
    }
    
    public String toString(){
        return "(" + this.i + ", " + this.j + ")";
    }
}
