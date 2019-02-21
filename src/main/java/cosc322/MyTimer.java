
package cosc322;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ygraphs.ai.smart_fox.GameMessage;
import ygraphs.ai.smart_fox.games.AmazonsGameMessage;
import ygraphs.ai.smart_fox.games.GameClient;
import ygraphs.ai.smart_fox.games.GameModel;
import ygraphs.ai.smart_fox.games.GamePlayer;

 /** 
* @author yongg
*/

import ygraphs.ai.smart_fox.games.GameClient;

   class MyTimer extends TimerTask{
	GameClient gameClient = null;
	int[] qf;
	int[] qn;
	int[] ar;
	
	public MyTimer(GameClient gameClient, int[] qf, int[] qn, int[] ar){	
	    this.gameClient = gameClient;
	    this.qf = qf;
	    this.qn = qn;
	    this.ar = ar;
	}
		
	/**
	 * send the move 
	 */
	public void run() {
		gameClient.sendMoveMessage(qf, qn, ar);
	}
    }