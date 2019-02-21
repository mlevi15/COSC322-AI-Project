
package cosc322;

import java.util.ArrayList;
import java.util.Map;

import ygraphs.ai.smart_fox.GameMessage;
import ygraphs.ai.smart_fox.games.AmazonsGameMessage;
import ygraphs.ai.smart_fox.games.GameClient;
import ygraphs.ai.smart_fox.games.GamePlayer;

/**
 * An example showing how to implement a GamePlayer 
 * @author yong.gao@ubc.ca
 */
public class COSC322Test{
    private Amazons amazons;
    private String userName = null;
 
	
    /**
     * The main method
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {				 
	COSC322Test player_01 = new COSC322Test(args[0], args[1]);  		 
    }
	
    /**
     * Any name and passwd 
     * @param userName
     * @param passwd
     */
    public COSC322Test(String userName, String passwd) {
	this.userName = userName;
	amazons = new Amazons(userName, passwd);	 
    }
 
    @Override
    public String userName() {
	return userName;
    }
}//end of class
