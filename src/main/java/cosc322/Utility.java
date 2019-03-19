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
public class Utility {
    
    public Utility(){
        
    }
    
    public void print(String msg){
        System.out.println(msg);
    }
    
    public void print(int i){
        System.out.println(i);
    }
    
    public void print(double d){
        System.out.println(d);
    }
    
    public void print(long l){
        System.out.println(l);
    }
    
    public void print(boolean b){
        System.out.println(b);
    }
    
    public int[][] copyBoard(int [][] b){
        int[][] copy = new int[11][11];
        for(int i = 0; i < 11; i++){
            copy[i] = b[i].clone();
        }
        return copy;
    }
}
