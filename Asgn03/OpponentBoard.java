package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * OpponentBoard - class in charge of functionality of attempting to shoot the opponent
 */
public class OpponentBoard extends BoardPanel implements ActionListener {

    List<Tile> enemyWaters = new ArrayList<>();
    List<Ship> enemyShips = new ArrayList<>();
    //test code to prove concept
    boolean updated = false;

    /**
     * OpponentBoard constructor - sets up the game panel
     */
    public OpponentBoard() {
        setLayout(new GridLayout(11,11, -1, -1));
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        //List<Tile> tileList = new ArrayList<>();

        add(new JLabel(""));
        int value = 0;
        for( int i = 1; i<121; i++) {
            if (i < 11){
                add(new JLabel("     " + i));
            }
            else if (i % 11 == 0){
                int alpha = (i%10 == 0) ? 10 : i%10;
                add(new JLabel("   " + (char) (alpha + 64)));
            }
            else{
                enemyWaters.add(new Tile(value));
                add(enemyWaters.get(enemyWaters.size() -1));
                enemyWaters.get(enemyWaters.size() -1).addActionListener(this);
                value +=1;
            }
        }

        /** Test code to show that it works*/
//        Blackboard.getBlackboard().setMyTurn(true);
        enemyShips.add(new Ship( new ArrayList<>(List.of(1,2,3,4,5))));
        enemyShips.add(new Ship( new ArrayList<>(List.of(20,30,40))));
        enemyShips.add(new Ship( new ArrayList<>(List.of(99, 89, 79 ))));

    }

    /**
     * checkAllShips
     * @param shot - index of the tile that was pressed
     * @return the Ship object that holds the hit tile or null if it was a miss
     */
    public Ship checkAllShips(int shot){
        for (Ship s: enemyShips){
            boolean shipHit = s.checkShot(shot);
            if (shipHit)
                return s;
        }
        return null;
    }

    /**
     * shipSunk - updates the view of all tiles of a ship
     * @param ship - ship object with all tiles of a sunken ship
     */
    public void shipSunk(Ship ship){
        for (Integer index: ship.startingTiles){
            enemyWaters.get(index).setTileType(Tile.TileType.SUNK);
            enemyWaters.get(index).updateView();
        }
    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides functionality for tile objects within the board
     * @param e - ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile && Blackboard.getBlackboard().isMyTurn()) {
            Blackboard.getBlackboard().setMyTurn(false);
            //prints out which tile was clicked
            Tile temp = (Tile) e.getSource();
            /** switch in the if statement below when the connections are set up */
            // also think if we need to use the ready flag or something similar
//            if (Blackboard.getBlackboard().isMyTurn() && temp.getShot() == Tile.ShotType.DEFAULT){
            if (temp.getShot() == Tile.ShotType.DEFAULT){
                /** set the shot index in blackBoard or send it straight through the client*/
                Blackboard.getBlackboard().setShotIndex(temp.getIndex());
                Ship hit = checkAllShips(temp.getIndex());
                if (hit == null){
                    temp.setShot(Tile.ShotType.MISS);
                    try {
                        ServerDTO data = new ServerDTO(Tile.ShotType.MISS.toString(), temp.getIndex(), null);
                        Blackboard.getBlackboard().getClient().sendObject(data);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    temp.setShot(Tile.ShotType.HIT);
                    try {
                        ServerDTO data = new ServerDTO(Tile.ShotType.HIT.toString(), temp.getIndex(), null);
                        Blackboard.getBlackboard().getClient().sendObject(data);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (hit.checkSunk()){
                        shipSunk(hit);
                    }
                }
                temp.updateView();
            }
        }
    }

    /**
     * update - Changes the list of enemy ships when they are sent over to check shots later
     * @param ob - blackboard object that has been updated
     */
    @Override
    public void update(MyObservable ob) {
        if (!updated) {
            //for (List<Integer> tileGroup : Blackboard.getBlackboard().getEnemyShipTiles())
            for (List<Integer> tileGroup : ((Blackboard)ob).getEnemyShipTiles()) {
                enemyShips.add(new Ship(tileGroup));
            }
            updated = true;
        }
    }

}
