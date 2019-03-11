
package cosc322;

import java.util.ArrayList;
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
        u.print(new State().toString());
    }
	
    public COSC322Test(String userName, String passwd) {
	this.userName = userName;
	amazons = new Amazons(userName, passwd);	 
    }
    
    public String userName() {
	return userName;
    }
}//end of class
