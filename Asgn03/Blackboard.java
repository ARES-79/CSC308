package Asgn03;

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

    //opponentShips
    private int shotIndex = -1;
    private boolean myTurn;
    private boolean receivedShips = false;
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

    /**
     * updateData - calls notifying which then updates all the observers
    */
    public void updateData() throws IOException {
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
}
