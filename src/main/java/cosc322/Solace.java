package cosc322;

import static cosc322.COSC322Test.u;
import java.util.Iterator;
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
    Position arrow;
    Position newQueen;
    Position oldQueen;
    
    public Solace(State root){
        this.turn = root.turn;
        this.player = root.player;
        this.root = root;
        
    }
    
    public void think(){
        long s = System.currentTimeMillis();

//        for(int i = 0; i < 3; i++){
//            root.selectMove();
//        }
        
        while((System.currentTimeMillis() - s) < 10 * 1000){
            root.selectMove();
        }
        
        double maxRatio = 0;
        State move = null;
        Iterator moveItr = root.children.iterator();
        while(moveItr.hasNext()){
            State state = (State)moveItr.next();
            double ratio = (double)state.wins / (double)state.sims;
            if(ratio > maxRatio){
                maxRatio = ratio;
                move = state;
            }
        }
        u.print("============================================");
        u.print("Max Ratio: " + maxRatio);
        u.print("Wins: " + move.wins + " Sims " + move.sims);
        u.print(move.toString());
        u.print("============================================");

        this.oldQueen = move.move.oldQueen;
        this.newQueen = move.move.newQueen;
        this.arrow = move.move.arrow;
        
        u.print("Time: " + (System.currentTimeMillis() - s) + " milliseconds");
        u.print("Simulations: " + root.sims);
//        StateView sv = new StateView(root);
//        sv.showTree("After play outs");
    }
}

