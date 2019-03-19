package cosc322;

import static cosc322.COSC322Test.u;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kevin
 * Solace AI object (Our artificial intelligence) 
 */
public class Solace {

    Utility u = new Utility();
    
    int player;
    int turn;
    State root;
    
    public Solace(State root){
        this.turn = root.turn;
        this.player = root.player;
        this.root = root;
        
    }
    
    public void think(){
        List<State> list = new LinkedList<State>();
        list.add(null);
        
//        int n = 10;
//        for(int i = 0; i < n; i++){
//            root.selectMove();
//        }
        long s = System.currentTimeMillis();
        while((System.currentTimeMillis() - s) < 25 * 1000){
            root.selectMove();
        }
        
            
        u.print("Time: " + (System.currentTimeMillis() - s) + " milliseconds");
        u.print("Total Simulations: " + root.numSims);
        u.print("Expanded to level: " + root.numExp);
        //write code to pick the most favorable child
    }
}

