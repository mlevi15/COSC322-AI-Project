
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
 * @author Kevin
 */
public class Amazons extends GamePlayer{
    private GameClient gameClient;   
    private JFrame guiFrame = null;    
    private GameBoard board = null;  
    public String userName = null;
    
    public Amazons(String userName, String passwd){
        this.userName = userName;		       	    
        this.gameClient = new GameClient(userName, passwd, this);
        setupGUI();      
    }
    
    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
	//This method will be called by the GameClient when it receives a game-related message
	//from the server.
	
	//For a detailed description of the message types and format, 
	//see the method GamePlayer.handleGameMessage() in the game-client-api document. 
	return true;
    }
    
    private void handleOpponentMove(Map<String, Object> msgDetails){
	System.out.println("OpponentMove(): " + msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR));
	ArrayList<Integer> qcurr = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR);
	ArrayList<Integer> qnew = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.Queen_POS_NEXT);
	ArrayList<Integer> arrow = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.ARROW_POS);
	System.out.println("QCurr: " + qcurr);
	System.out.println("QNew: " + qnew);
	System.out.println("Arrow: " + arrow);

	board.markPosition(qnew.get(0), qnew.get(1), arrow.get(0), arrow.get(1), qcurr.get(0), qcurr.get(1), true);		
    }
    
    public void playerMove(int x, int y, int arow, int acol, int qfr, int qfc){		
		 
	int[] qf = new int[2];
	qf[0] = qfr;
	qf[1] = qfc;

	int[] qn = new int[2];
	qn[0] = x;
	qn[1] = y;

	int[] ar = new int[2];
	ar[0] = arow;
	ar[1] = acol;

        java.util.Timer timer = new java.util.Timer();
        MyTimer timee = new MyTimer(this.gameClient, qf, qn, ar);
        timer.schedule(timee, 10000);
    }
    
    @Override
    public void onLogin() {
        ArrayList<String> roomList = this.gameClient.getRoomList();
        System.out.println("Available rooms are: " + roomList.toString());
        System.out.println("Joining room: " + roomList.get(0) + "...");
        this.gameClient.joinRoom(roomList.get(0));
        System.out.println(roomList.get(0) + " successfully joined.");
        System.out.println("Let the games begin.");
    }
    
    private void setupGUI(){
        guiFrame = new JFrame();

        guiFrame.setSize(800, 600);
        guiFrame.setTitle("Game of the Amazons (COSC 322, )" + this.userName());	

        guiFrame.setLocation(200, 200);
        guiFrame.setVisible(true);
        guiFrame.repaint();		
        guiFrame.setLayout(null);

        Container contentPane = guiFrame.getContentPane();
        contentPane.setLayout(new  BorderLayout());

        contentPane.add(Box.createVerticalGlue()); 

        board = createGameBoard();
        contentPane.add(board,  BorderLayout.CENTER);
    }
    
    private GameBoard createGameBoard(){
	return new GameBoard(this);
    }	
    
    public boolean handleMessage(String msg) {
	System.out.println("Time Out ------ " + msg); 
	return true;
    }
    
    @Override
    public String userName() {
	return userName;
    }
}
