
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
    private String userName = "Solace";
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
        //testChildGeneration();
        //detectChange();
        //testSolace();
        //testGetRandomChild
        //testGetRandomChild();
        testSolace();
    }
    
    public void testSolace(){
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
        
        int[][] test2 = {{0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}, 
                        {0,  -1,  0, -1,  0, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  -1,  0, -1,  1}, 
                        {0, -1, -1,  -1,  0,  0,  -1,  0,  0, 0, -1}, 
                        {0,  -1,  0,  0,  0,  0 , 0,  -1,  0,  0,  0},
                        {0,  0,  0,  0, -1, -1,  0,  0,  0,  0,  -1}, 
                        {0,  0, -1,  0, -1, -1, -1,  0,  -1,  0,  0},
                        {0,  2,  0,  0, -1, -1, -1,  -1,  0,  0,  2},
                        {0, -1, 0,  0,  0,  -1,  -1,  0,  0,  -1,  0},
                        {0,  1, -1,  0,  2,  0,  0,  2,  -1,  0,  0}};
        
        long st = System.currentTimeMillis();
        State s = new State(1, 2, test2);
        Solace solace = new Solace(s);
        solace.think();
        u.print("Old: " + solace.oldQueen.toString());
        u.print("New: " + solace.newQueen.toString());
        u.print("Arrw: " + solace.arrow.toString());
    }
    
    public void testGetRandomChild(){
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
        
        int[][] test2 = {{0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}, 
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
        
        State s1 = new State(1, 1, test);
        u.print("Random Move:");
        s1 = s1.getRandomChild();
        if(s1 != null)
            u.print(s1.toString());
        else
            u.print("Terminal");
    }
    
    public String userName() {
	return userName;
    }
}//end of class
