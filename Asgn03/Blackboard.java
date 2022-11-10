package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 03
 * @author Mitashi Parikh
 * ...
 * @version 1.0
 * Blackboard Class - the blackboard for the blackboard pattern
 *          Is the central location for accessing data and is observervable
 *          Singleton pattern
 */
public class Blackboard extends MyObservable {
    private List<Tile> myTileList = new ArrayList<>();
    private List<List<Integer>> enemyShipTiles = new ArrayList<>();
    private List<List<Integer>> myShipTiles = new ArrayList<>();
    private Client client;
    private Game gameBoard;

    //opponentShips
    private int shotIndex;
    private boolean myTurn;
    private boolean receivedShips = false;
    private boolean sentShips = false;
    private boolean readyToSendShips = false;
    private boolean gameOver = false;
    private JLabel status = new JLabel("Please place 17 tiles to select your ships") ;
    private static Blackboard blackboard;

    /**
     * Blackboard private constructor for singleton Blackboard class
     */
    private Blackboard(){}

    /**
     * getBlackboard - getter method to access the data stored on the Blackboard
     * @return returns the Blackboard object
     */
    public static Blackboard getBlackboard() {
        if(blackboard == null){
            blackboard = new Blackboard();
        }
        return blackboard;
    }

    /**
     * getTileList - getter method to access myTileList which contains all the tiles on the board
     * @return returns myTileList
     */
    public List<Tile> getTileList(){
        return myTileList;
    }

    public void setMyTileList(List<Tile> myTileList) {
        this.myTileList = myTileList;
    }

    /**
     * addTile - adds the next tile to myTileList which contains all the tiles on the board
     * @param t - Tile object to be added to myTileList
     */
    public void addTile(Tile t) {myTileList.add(t);}

    /**
     * getEnemyShipTiles - getter method to access enemyShipTiles list which contains the indices of the tile where the opponent has placed their ships
     * @return returns the enemyShipTile list
     */
    public List<List<Integer>> getEnemyShipTiles() {
        return enemyShipTiles;
    }

    /**
     * setEnemyShipTile - setter method to set enemyShipTiles list to the list of enemy ship tile received from the other player
     * @param enemyShipTiles - List of ships, which is a list of integers which represent the indices of the tiles in an opponent ship
     */
    public void setEnemyShipTiles(List<List<Integer>> enemyShipTiles) {
        this.enemyShipTiles = enemyShipTiles;
    }

    /**
     * getMyShipTiles - getter method to access myShipTiles list which stores the tiles on MyBoard where the ships have been placed
     * @return returns myShipTiles, a list of ships, which is a list of integers which represent the indices of the tiles in a ship
     */
    public List<List<Integer>> getMyShipTiles() {
        return myShipTiles;
    }

    /**
     * setMyShipTiles - setter methods which updates myShipTiles to reflect the current list of tiles where ships have been placed
     * @param myShipTiles - List of ships, which is also a list of indices of the tiles that belong to one of a ship
     */
    public void setMyShipTiles(List<List<Integer>> myShipTiles) {
        this.myShipTiles = myShipTiles;
    }

    /**
     * getClient - getter method to access the client of the current instance of the game
     * @return returns the Client object
     */
    public Client getClient(){
        return this.client;
    }

    /**
     * setClient - setter method to set the client
     * @param client - Client object
     */
    public void setClient(Client client){
        this.client = client;
    }

    /**
     * getGameBoard - getter method to access the gameBoard which is a Game instance
     * @return returns the Game object
     */
    public Game getGameBoard() {
        return gameBoard;
    }

    /**
     * setGameBoard - setter method to set the gameBoard to a Game object
     * @param gameBoard - Game object
     */
    public void setGameBoard(Game gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * getShotIndex - getter method to access the index of the most recent shot placed
     * @return returns an integer, shotIndex, which is the index of the most recent tile that was clicked
     */
    public int getShotIndex() {
        return shotIndex;
    }

    /**
     * setShotIndex - setter method to set shotIndex to the index of the most recent tile clicked
     * @param shotIndex - int that represents the index of the most recent shot placed
     */
    public void setShotIndex(int shotIndex) {
        this.shotIndex = shotIndex;
    }

    /**
     * isMyTurn - getter method to check which player's turn it is
     * @return - boolean, myTurn, which is false if it is the opponent's turn and true otherwise
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * setMyTurn - setter method to change the turn once a player has placed a shot
     * @param myTurn - boolean, myTurn, to keep track of which player's turn it is
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    /**
     * isReceivedShips - getter methods to check if the opponent's ships have been received
     * @return - boolean, receivedShips, which is false until the ships are received from the opponent
     */
    public boolean isReceivedShips() {
        return receivedShips;
    }

    /**
     * setReceivedShips - setter method to set to true when opponent's ship list has been received
     * @param receivedShips - boolean, receivedShips, to know when the opponent has sent over their ship list
     */
    public void setReceivedShips(boolean receivedShips) {
        this.receivedShips = receivedShips;
    }

    /**
     * isSentShips - getter method to check if our ship list has been sent to the opponent
     * @return - boolean, sentShips, which is false until we send over our ship list to the opponent
     */
    public boolean isSentShips() {
        return sentShips;
    }

    /**
     * setSentShips - setter method to update when we have sent over our ships to the opponent
     * @param sentShips - boolean, sentShips, to know when we have sent over our ships to the opponent
     */
    public void setSentShips(boolean sentShips) {
        this.sentShips = sentShips;
    }

    /**
     * isReadyToSendShips - getter method to check if we are ready to send over or ships list to the opponent
     * @return - boolean, readyToSendShips, which is false until we are ready to send over our ships list to the opponent's game instance
     */
    public boolean isReadyToSendShips() {
        return readyToSendShips;
    }

    /**
     * setReadyToSendShips - setter method to set to true when we are ready to send over our ship list to the opponent's server
     * @param readyToSendShips - boolean, readyToSendShips, to know when we are ready to send our ship list to the opponent
     */
    public void setReadyToSendShips(boolean readyToSendShips) {
        this.readyToSendShips = readyToSendShips;
    }

    /**
     * isGameOver - getter method to know when a player has won and the game is over
     * @return - boolean, gameOver, which is false until a player satisfies the win condition and wins the game
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * setGameOver - setter method to set the GameOver flag to indicate that a player has won
     * @param gameOver - boolean, gameOver, to know when a player has won and the game is over
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * getStatus - getter method to access the status of the game, which indicates how many ship tiles to click and whose turn it is
     * @return - JLabel object status where the current status is stored
     */
    public JLabel getStatus() {
        status.setHorizontalTextPosition(2);
        status.setPreferredSize(new Dimension(30,30));
        return status;
    }

    /**
     * updateData - calls notifying which then updates all the observers
     * @throws IOException
     */
    public void updateData() throws IOException {
        notifying();
    }

    /**
     * reset - is used to reset the game to the start state
     */
    public void reset(){
        this.myTileList = new ArrayList<>();
        this.enemyShipTiles = new ArrayList<>();
        this.myShipTiles = new ArrayList<>();
        this.receivedShips = false;
        this.sentShips = false;
        this.readyToSendShips = false;
        this.gameOver = false;
        this.status.setText("Please place 17 tiles to select your ships");
        Game temp = new Game(this.gameBoard.getPlayer());
        temp.setSize(1000, 600);
        temp.setVisible(true);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameBoard.dispose();
        this.gameBoard = temp;
    }
}
