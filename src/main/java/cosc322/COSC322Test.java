
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
                        {0, -1, -1,  -1,  0,  0,  -1,  0,  0, -1, -1}, 
                        {0,  -1,  -1,  0,  0,  0 , 0,  -1,  0,  0,  0},
                        {0,  0,  0,  0, -1, -1,  0,  0,  0,  0,  -1}, 
                        {0,  0, -1,  0, -1, -1, -1,  0,  -1,  0,  -1},
                        {0,  2,  -1,  0, -1, -1, -1,  -1,  0,  -1,  2},
                        {0, -1, 0,  -1,  0,  -1,  -1,  0,  0,  -1,  -1},
                        {0,  1, -1,  0,  2,  0,  0,  2,  -1,  0,  0}};
        
        int[][] test3 = {{0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}, 
                        {0,  0,  0, -1,  0, -1, -1,  1, -1,  0,  0}, 
                        {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
                        {0,  1, -1,  0,  0,  0,  0,  0,  0, -1,  1}, 
                        {0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1}, 
                        {0,  0,  0,  0,  0,  0 , 0,  0,  0,  0,  0},
                        {0,  0,  0,  0, -1, -1,  0,  0,  0,  0,  0}, 
                        {0,  -1,  0,  0, -1, -1, -1,  0,  0,  -1,  -1},
                        {0,  2,  -1,  0, -1, -1, -1,  0,  0,  -1,  2},
                        {0, -1, -1,  0,  -1,  0,  -1,  -1,  -1,  0,  0},
                        {0,  1, -1,  -1,  2,  -1,  0,  2,  0,  0,  0}};
        
//Failing state        
//0 0 0 0 0 0 -1 0 1 -1 
//0 0 -1 0 0 -1 -1 -1 2 0 
//2 0 -1 -1 -1 -1 -1 -1 -1 -1 
//0 0 -1 -1 0 2 -1 -1 -1 -1 
//0 -1 0 -1 -1 1 -1 0 0 0 
//-1 -1 0 -1 -1 -1 -1 0 -1 0 
//-1 -1 -1 -1 0 -1 -1 0 -1 0 
//0 0 -1 1 -1 1 -1 -1 -1 -1 
//-1 -1 -1 -1 -1 -1 -1 -1 0 0 
//0 -1 0 -1 2 -1 -1 0 0 -1 

//============================================
//Max Ratio: 0.8481375358166189
//Wins: 9176 Sims 10819
//0 0 0 0 0 0 -1 0 1 -1 
//0 0 -1 0 0 -1 -1 -1 2 0 
//0 0 -1 -1 -1 -1 -1 -1 -1 -1 
//0 0 -1 -1 0 2 -1 -1 -1 -1 
//0 -1 2 -1 1 0 -1 0 0 0 
//0 -1 0 -1 -1 -1 -1 0 -1 0 
//-1 -1 -1 -1 0 -1 -1 0 -1 0 
//0 0 -1 1 -1 1 -1 -1 -1 -1 
//-1 -1 -1 -1 -1 -1 -1 -1 0 0 
//0 -1 0 -1 2 -1 -1 0 0 -1 
//
//Time: 15009 milliseconds
//Simulations: 14409
//Current Queen Position: [7, 5]
//Our Queen Move: [8, 6]
//Our Arrow Move: [9, 6]
//8, 6, 9, 6, 7, 5
//Opponent's Queen Move: [5, 3]
//Opponent's Arrow Move: [6, 1]
//3, 1, 6, 1, 5, 3
//============================================
//Max Ratio: 0.07216494845360824
//Wins: 21 Sims 291
//0 0 0 0 0 0 -1 0 1 -1 
//0 0 -1 0 0 -1 -1 -1 2 0 
//2 0 -1 -1 -1 -1 -1 -1 -1 -1 
//0 0 -1 -1 0 2 -1 -1 -1 -1 
//0 -1 0 -1 -1 1 -1 0 0 0 
//-1 -1 0 -1 -1 -1 -1 0 -1 0 
//-1 -1 -1 -1 0 -1 -1 0 -1 0 
//0 0 -1 1 -1 1 -1 -1 -1 -1 
//-1 -1 -1 -1 -1 -1 -1 -1 0 0 
//0 -1 0 -1 2 -1 -1 0 0 -1 
//
//Time: 16395 milliseconds
//Simulations: 14666
//Current Queen Position: [5, 5]
//Our Queen Move: [5, 6]
//Our Arrow Move: [5, 5]
//5, 6, 5, 5, 5, 5
//Opponent's Queen Move: [4, 6]
//Opponent's Arrow Move: [4, 6]
//4, 5, 4, 6, 4, 6
//Terminal State
//Time: 15504 milliseconds
//Simulations: 14922
        
        long st = System.currentTimeMillis();
        State s = new State(1, 2, test2);
        Solace solace = new Solace(s);
        solace.think();
//        u.print("Old: " + solace.oldQueen.toString());
//        u.print("New: " + solace.newQueen.toString());
//        u.print("Arrow: " + solace.arrow.toString());
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
