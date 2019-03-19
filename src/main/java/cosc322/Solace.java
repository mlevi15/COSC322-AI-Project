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
        long s = System.currentTimeMillis();
        
//        int n = 100;
//        for(int i = 0; i < n; i++){
//            root.selectMove();
//        }

        while((System.currentTimeMillis() - s) < 28 * 1000){
            root.selectMove();
        }

        StateView sv = new StateView(root);
        sv.showTree("After play outs");
        
        u.print("Time: " + (System.currentTimeMillis() - s) + " milliseconds");
    }
}

