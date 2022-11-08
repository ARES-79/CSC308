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

    public Game getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Game gameBoard) {
        this.gameBoard = gameBoard;
    }

    //opponentShips
    private int shotIndex;
    private boolean myTurn;
    private boolean receivedShips = false;
    private boolean sentShips = false;
    private boolean readyToSendShips = false;
    private boolean gameOver = false;
    private JLabel status = new JLabel("Please place 10 tiles to select your ships") ;
    private static Blackboard blackboard;

    /**
     *
     */
    private Blackboard(){}

    /**
     * 
     */
    public static Blackboard getBlackboard() {
        if(blackboard == null){
            blackboard = new Blackboard();
        }
        return blackboard;
    }

    /**
     * 
     */
    public List<Tile> getTileList(){
        return myTileList;
    }

    public void addTile(Tile t) {myTileList.add(t);}

    public List<List<Integer>> getEnemyShipTiles() {
        return enemyShipTiles;
    }

    public void setMyShipTiles(List<List<Integer>> myShipTiles) {
        this.myShipTiles = myShipTiles;
    }

    public int getShotIndex() {
        return shotIndex;
    }

    public boolean isSentShips() {
        return sentShips;
    }

    public boolean isReadyToSendShips() {
        return readyToSendShips;
    }

    public void setReadyToSendShips(boolean readyToSendShips) {
        this.readyToSendShips = readyToSendShips;
    }

    public void setSentShips(boolean sentShips) {
        this.sentShips = sentShips;
    }

    public boolean isReceivedShips() {
        return receivedShips;
    }

    public void setReceivedShips(boolean receivedShips) {
        this.receivedShips = receivedShips;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setShotIndex(int shotIndex) {
        this.shotIndex = shotIndex;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public JLabel getStatus() {
        status.setHorizontalTextPosition(2);
        status.setPreferredSize(new Dimension(30,30));
        return status;
    }

    /**
     * updateData - calls notifying which then updates all the observers
    */
    public void updateData() throws IOException {
        System.out.println(this + "is notifying");
        notifying();
    }

    public void setClient(Client client){
        this.client = client;
    }
    public Client getClient(){
        return this.client;
    }

    public void setEnemyShipTiles(List<List<Integer>> enemyShipTiles) {
        this.enemyShipTiles = enemyShipTiles;
    }

    public List<List<Integer>> getMyShipTiles() {
        return myShipTiles;
    }

    public void reset(){

        this.myTileList = new ArrayList<>();
        this.enemyShipTiles = new ArrayList<>();
        this.myShipTiles = new ArrayList<>();
        this.receivedShips = false;
        this.sentShips = false;
        this.readyToSendShips = false;
        this.gameOver = false;
        this.status.setText("Please place 10 tiles to select your ships");
        Game temp = new Game(this.gameBoard.getPlayer());
        temp.setSize(1000, 600);
        temp.setVisible(true);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameBoard.dispose();
        this.gameBoard = temp;
    }
}
