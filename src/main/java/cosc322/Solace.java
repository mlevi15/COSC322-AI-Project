package cosc322;

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

        root.setChildren(root.getPossibleMoves());
        int numchildren = root.getChildren().size();//getting the number of children
        while((secondtimer - firsttimer) < 20){
            secondtimer = System.currentTimeMillis();
            int randomnumber = rand.nextInt(numchildren); //picking a random "node" between 0-(numchildren-1)
            rootrandomchild = root.getChildren().get(randomnumber);
            System.out.println(rootrandomchild.toString());
            rootrandomchild.setParent(null);
            
            while(rootrandomchild.checkGoalState(1) == 0){
                rootrandomchild.setChildren(rootrandomchild.getPossibleMoves());
                int nchildren = rootrandomchild.getChildren().size();
                int rnumber = rand.nextInt(nchildren);
                randomchild = rootrandomchild.getChildren().get(rnumber);
                randomchild.setParent(rootrandomchild);
                System.out.println(randomchild.toString());

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
        for(int i = 0; i < numchildren; i++){
            double wins = root.getChildren().get(i).getWins();
            double sims = root.getChildren().get(i).getSims();
            if(maxratio < (wins/sims)){
                maxratio = (wins/sims);
                node = i;
            }
        }
        int originalboard[][] = root.getBoard();
        int newboard[][] = rootrandomchild.getBoard();
        for(int i = 0; i < originalboard.length; i++){
            for(int j = 0; j < newboard.length; j++){
                if(originalboard[i][j] != newboard[i][j]){
                    if(newboard[i][j] == -1){
                        newarrowposition = new Position(i,j);
                    }
                    else if(newboard[i][j] == 1 || newboard[i][j] == 2){
                        newqueenposition = new Position(i,j);
                    }   
                    else if(originalboard[i][j] == 1 || originalboard[i][j] == 2){
                        oldqueenposition = new Position(i,j);
                    }  
                }
            }
        }
        
        
    }

    
}

