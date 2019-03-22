
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
 * @author Kevin & Levi
 */
public class Amazons extends GamePlayer{
    private State state;
    private GameClient gameClient;   
    private JFrame guiFrame = null;    
    private GameBoard board = null;
    private int[][] ourBoard;
    public String userName = null;
    static Utility u = new Utility();
    int turnCount = 0;
    int player = 0;
    String ourPlayer = "";
    String enemyPlayer = "";
    
   
    public static void main(String[] args) { 
        Amazons game = new Amazons("Levi", "cosc322");
    }
    
    /*
    * Constructor
    * @param userName
    * @param passwd
    */
    public Amazons(String userName, String passwd){
        this.userName = userName;		       	    
        this.gameClient = new GameClient(userName, passwd, this);
        setupGUI();      
    }
    
    /*
     * Implements the abstract method as defined in GamePlayer in the API. Once
     * a user joins a room, all of the game-related messages from the server will be forwarded
     * to this method by the GameClient. 
     *
     * For a detailed description of the message types and format, 
     * see the method GamePlayer.handleGameMessage() in the game-client-api document. 
     * 
     * @param messageType - the type of message
     * @param msgDetails - a HashMap with info and data about a game action
     */
    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
	
        if(messageType.equals(GameMessage.GAME_ACTION_START)){
            //assuming black goes first as stated by Dr. Gao
            if(((String)msgDetails.get("player-black")).equals(this.userName())){
                //we take the first turn
                u.print("Game State: " + msgDetails.get("player-black"));
                ourPlayer = "Black Player: " + this.userName();
                enemyPlayer = "White Player: " + msgDetails.get("player-white");
                player = 1;
                
                turnCount++;
                
                guiFrame.setTitle("Turn: " + turnCount + " | Move: " + this.userName() + " | " + ourPlayer + " | " + enemyPlayer);
                
                state = new State(player, 2);
                Solace solace = new Solace(state);
                
                u.print("Initial Board");
                u.print(state.toString());
                
                solace.think();
                
                Position currentQueen = solace.oldQueen;
                Position ourQueenMove = solace.newQueen;
                Position ourArrowMove = solace.arrow;

                u.print("Our Queen Move: [" + ourQueenMove.i + ", " + ourQueenMove.j + "]");
                u.print("Our Arrow Move: [" + ourArrowMove.i + ", " + ourArrowMove.j + "]");
                
                board.markPosition(ourQueenMove.i, ourQueenMove.j, ourArrowMove.i, ourArrowMove.j, currentQueen.i, currentQueen.j, true);
                
                gameClient.sendMoveMessage(this.combinedMove(currentQueen.i, currentQueen.j), this.combinedMove(ourQueenMove.i, ourQueenMove.j), this.combinedMove(ourArrowMove.i, ourArrowMove.j));
                
            }else{
                //enemy goes first we wait for now and handleOpponentMove when the server sends it to us
                turnCount++;
                ourPlayer = "White Player: " + this.userName();
                enemyPlayer = "Black Player: " + msgDetails.get("player-black");
                guiFrame.setTitle("Turn: " + turnCount + " | Move: " + msgDetails.get("player-black") + " | " + ourPlayer + " | " + enemyPlayer);
                player  = 2;
            }
            
        }else if(messageType.equals(GameMessage.GAME_ACTION_MOVE)){
            handleOpponentMove(msgDetails);
        }
        
	return true;
    }
    
    //handles the event when an opponent makes a move
    private void handleOpponentMove(Map<String, Object> msgDetails){
                
        //ENEMY TURN
        turnCount++;
        
        if(player == 1){
            guiFrame.setTitle("Turn: " + turnCount + " | Move: " + msgDetails.get("player-black") + " | " + ourPlayer + " | " + enemyPlayer);
        }else{
            guiFrame.setTitle("Turn: " + turnCount + " | Move: " + msgDetails.get("player-white") + " | " + ourPlayer + " | " + enemyPlayer);
        }
        
	u.print("Opponent's Queen Move: " + msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR));
        u.print("Opponent's Arrow Move: " + msgDetails.get(AmazonsGameMessage.ARROW_POS));
        
	ArrayList<Integer> qcurr = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR);
	ArrayList<Integer> qnew = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.Queen_POS_NEXT);
	ArrayList<Integer> arrow = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.ARROW_POS);
        
	//System.out.println("QCurr: " + qcurr);
	//System.out.println("QNew: " + qnew);
	//System.out.println("Arrow: " + arrow);

	board.markPosition(qnew.get(0), qnew.get(1), arrow.get(0), arrow.get(1), qcurr.get(0), qcurr.get(1), false);	
        
        int turn = 0;
        if(player == 1){
            turn = 2;
        }else{
            turn = 1;
        }
        
        State opponentState = new State(player, turn, this.board.gameModel);
        
        //TO-DO: add code to handle enemy move in our search tree
        
        //check to see if we are at a goal state
        
        if(opponentState.checkGoalState() == 1){
            u.print("The Game is Over - We Win!!!");
        }else if(opponentState.checkGoalState() == 0){
            u.print("The Game is Over - We Lose...");
        }
         
        
        //OUR TURN
        turnCount++;
        
        if(player == 1){
            guiFrame.setTitle("Turn: " + turnCount + " | Move: " + msgDetails.get("player-black") + " | " + ourPlayer + " | " + enemyPlayer);
        }else{
            guiFrame.setTitle("Turn: " + turnCount + " | Move: " + msgDetails.get("player-white") + " | " + ourPlayer + " | " + enemyPlayer);
        }
        
        Solace solace = new Solace(opponentState);
        
        solace.think();
        
        Position currentQueen = solace.oldQueen;
        Position ourQueenMove = solace.newQueen;
        Position ourArrowMove = solace.arrow;

        u.print("Current Queen Position: [" + currentQueen.i + ", " + currentQueen.j + "]");
        u.print("Our Queen Move: [" + ourQueenMove.i + ", " + ourQueenMove.j + "]");
        u.print("Our Arrow Move: [" + ourArrowMove.i + ", " + ourArrowMove.j + "]");
        
        board.markPosition(ourQueenMove.i, ourQueenMove.j, ourArrowMove.i, ourArrowMove.j, currentQueen.i, currentQueen.j, true);

        gameClient.sendMoveMessage(this.combinedMove(currentQueen.i, currentQueen.j), this.combinedMove(ourQueenMove.i, ourQueenMove.j), this.combinedMove(ourArrowMove.i, ourArrowMove.j));

        //check to see if we are at a goal state
        if(opponentState.checkGoalState() == 1){
            u.print("The Game is Over - We Win!!!");
        }else if(opponentState.checkGoalState() == 0){
            u.print("The Game is Over - We Lose...");
        }
    }
    
    /*
     * handle a move made by this player and send the info to the server.
     * @param x queen row index 
     * @param y queen col index
     * @param arow arrow row index
     * @param acol arrow col index
     * @param qfr queen original row
     * @param qfc queen original col
     */
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
    /*
    * Implements the abstract method defined in GamePlayer. Will be invoked by the GameClient
    * when the server says the login is successful
    */
    @Override
    public void onLogin() {
        ArrayList<String> roomList = this.gameClient.getRoomList();
        String room = roomList.get(3);
        System.out.println("Available rooms are: " + roomList.toString());
        System.out.println("Joining room: " + room + "...");
        this.gameClient.joinRoom(room);
        System.out.println(room + " successfully joined.");
        System.out.println("Let the games begin.");
    }
    
    private void setupGUI(){
        guiFrame = new JFrame();

        guiFrame.setSize(800, 600);
        guiFrame.setTitle("Turn: " + turnCount + " | Move: " + this.userName() + " | " + ourPlayer + " | " + enemyPlayer);	

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
    
    /*
     * combinedMove: creates an int array storing the row, and column of a Queen in order to pass it
     * to the server
     * @param row: an int containing a Queen's row position
     * @param col: an int containing Queen's column position
     * @return: an int array containing the row, and column of a Queen
     */
    public int[] combinedMove(int row, int col) {
        int[] move = new int[2];
        move[0] = row;
        move[1] = col;
        return move;
    }
    
    @Override
    public String userName() {
	return userName;
    }
}
