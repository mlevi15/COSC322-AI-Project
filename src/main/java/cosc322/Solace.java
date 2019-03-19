package cosc322;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kevin
 * Solace AI object (Our artificial intelligence) 
 */
public class Solace {
    
State root;
    long firsttimer = System.currentTimeMillis();
    long secondtimer = System.currentTimeMillis();
    Random rand = new Random(); 
    State rootrandomchild;
    State randomchild;
    Position newarrowposition = null;
    Position newqueenposition = null;
    Position oldqueenposition = null;
    public Solace(State root){
        this.root = root;
    }

    public void think(){
        double maxratio = 0;
        int node = 0;
        int iterations = 0;
        root.setChildren(root.getPossibleMoves());

        ArrayList<State> children = root.getChildren();

        int numchildren = root.getChildren().size();//getting the number of children
        while((secondtimer-firsttimer) < 20*1000){
            secondtimer = System.currentTimeMillis();
            iterations +=1;
            
            System.out.println(iterations);
            secondtimer = System.currentTimeMillis();
            int randomnumber = (int)(Math.random() * numchildren); //picking a random "node" ebetween 0-(numchildren-1)
            rootrandomchild = children.get(randomnumber);
            rootrandomchild.setParent(null);
            
            while(rootrandomchild.getPossibleMoves().size() != 0){  
                rootrandomchild.setChildren(rootrandomchild.getPossibleMoves());
                int nchildren = rootrandomchild.getChildren().size();
                int rnumber = (int)(Math.random()*nchildren);
                randomchild = rootrandomchild.getChildren().get(rnumber);
                randomchild.setParent(rootrandomchild);

                rootrandomchild = randomchild;          
            }
            
            if(rootrandomchild.checkGoalState(1) == 1){
                rootrandomchild.setWins(rootrandomchild.getWins() + 1);
                rootrandomchild.setSims(rootrandomchild.getSims() + 1);
            }
            
            else {
                rootrandomchild.setSims(rootrandomchild.getSims() + 1);
            }
            
            while(rootrandomchild.getParent() != null){
                rootrandomchild.getParent().setWins(rootrandomchild.getWins());
                rootrandomchild.getParent().setSims(rootrandomchild.getSims());
                rootrandomchild = rootrandomchild.getParent();
            }    
        }
//        for(int i = 0; i < numchildren; i++){
//            double wins = root.getChildren().get(i).getWins();
//            double sims = root.getChildren().get(i).getSims();
//            if(maxratio < (wins/sims)){
//                maxratio = (wins/sims);
//                node = i;
//            }
//        }
//        int originalboard[][] = root.getBoard();
//        int newboard[][] = rootrandomchild.getBoard();
//        for(int i = 0; i < originalboard.length; i++){
//            for(int j = 0; j < newboard.length; j++){
//                if(originalboard[i][j] != newboard[i][j]){
//                    if(newboard[i][j] == -1){
//                        newarrowposition = new Position(i,j);
//                    }
//                    else if(newboard[i][j] == 1 || newboard[i][j] == 2){
//                        newqueenposition = new Position(i,j);
//                    }   
//                    else if(originalboard[i][j] == 1 || originalboard[i][j] == 2){
//                        oldqueenposition = new Position(i,j);
//                    }  
//                }
//            }
//        }
        
        
    }

    
}

