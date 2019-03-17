
package cosc322;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import ygraphs.ai.smart_fox.GameMessage;
import ygraphs.ai.smart_fox.games.AmazonsGameMessage;
import ygraphs.ai.smart_fox.games.GameClient;
import ygraphs.ai.smart_fox.games.GamePlayer;

public class COSC322Test{
    private Amazons amazons;
    private String userName = null;
    static Utility u = new Utility();
 
    public static void main(String[] args) {
	COSC322Test player_01 = new COSC322Test("user", "pass");
       
//        int[][] test = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
//                        {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
//                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                        {0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0}};
       
    }
	
    public COSC322Test(String userName, String passwd) {
	this.userName = userName;
	//amazons = new Amazons(userName, passwd);
        //testCheckGoalState();
        testChildGeneration();
    }
    
    public void testChildGeneration(){
//        State state = new State();
//        ArrayList<State> children = state.getPossibleMoves();
//        u.print("Total Possible moves at current level: " + children.size());
//        u.print("Root State: ");
//        u.print(state.toString());
//        Iterator itr = children.iterator();
//        while(itr.hasNext()){
//            u.print(itr.next().toString());
//        }
         
        int[][] test = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0}};
        
        int[][] test2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1},
                        {0,  2, -1,  0,  0,  0,  0,  0,  0, -1, 2},
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                        {0,  0,  0, -1,  2, -1, -1,  2, -1,  0, 0}};
        
        State state = new State(test, 2);
        ArrayList<State> children = state.getPossibleMoves();
        u.print("Total Possible moves at current level: " + children.size());
        u.print("Root State: ");
        u.print(state.toString());
        Iterator itr = children.iterator();
        while(itr.hasNext()){
            u.print(itr.next().toString());
        }
    }
    
    public void testCheckGoalState(){
        int[][] test1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1},
                        {0,  2, -1,  0,  0,  0,  0,  0,  0, -1, 2},
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                        {0,  0,  0, -1,  2, -1, -1,  2, -1,  0, 0}};
        
        int[][] test2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0,  0,  0, -1,  1, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  0,  0, -1,  1}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0}};
        
        int[][] test3 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1},
                        {0,  2, -1,  0,  0,  0,  0,  0,  0, -1, 2},
                        {0, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1},
                        {0,  0,  0, -1,  2, -1, -1,  2, -1,  0, 0}};
        
        int[][] test4 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0,  0,  0, -1,  1, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  0,  0, -1,  1}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0}};
        
        int[][] test5 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0,  0,  0, -1,  0, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  0,  0, -1,  1}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, -1, -1, -1, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, -1,  1, -1, 0, 0, 0, 0},
                        {0, 2, 0, 0, -1, -1, -1, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0}};
        
        int[][] test6 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                        {0,  0,  0, -1,  0, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  0,  0, -1,  1}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1}, 
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, -1, -1,  0, 0, 0, 0, 0}, 
                        {0, 0, 0, 0, -1,  1, -1, 0, 0, 0, 0},
                        {0, 2, 0, 0, -1, -1, -1, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0}};
        
        int[][] test7 = {{0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}, 
                        {0,  0,  0, -1,  0, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  0,  0, -1,  1}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1}, 
                        {0,  0,  0,  0,  0,  0 , 0,  0,  0,  0,  0},
                        {0,  0,  0,  0, -1, -1,  0,  0,  0,  0,  0}, 
                        {0,  0,  0,  0, -1, -1, -1,  0,  0,  0,  0},
                        {0,  2,  0,  0, -1, -1, -1,  0,  0,  0,  2},
                        {0, -1, -1,  0,  0,  0,  0,  0,  0,  0,  0},
                        {0,  1, -1,  0,  2,  0,  0,  2,  0,  0,  0}};
        
        

        State s1= new State(test1, 1);
        u.print(s1.toString());
        u.print("Goal State: " + s1.checkGoalState(1) + "\n");
        
        State s2 = new State(test2, 1);
        u.print(s2.toString());
        u.print("Goal State: " + s2.checkGoalState(1) + "\n");
        
        State s3 = new State(test3, 1);
        u.print(s3.toString());
        u.print("Goal State: " + s3.checkGoalState(1) + "\n");
        
        State s4 = new State(test4, 1);
        u.print(s4.toString());
        u.print("Goal State: " + s4.checkGoalState(1) + "\n");
        
        State s5 = new State(test5, 1);
        u.print(s5.toString());
        u.print("Goal State: " + s5.checkGoalState(1) + "\n");
        
        State s6 = new State(test6, 1);
        u.print(s6.toString());
        u.print("Goal State: " + s6.checkGoalState(1) + "\n");
        
        State s7 = new State(test7, 1);
        u.print(s7.toString());
        u.print("Goal State: " + s7.checkGoalState(1) + "\n");
    }
    public String userName() {
	return userName;
    }
}//end of class
