package Asgn03;

import Asgn02.src.Dot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MyBoard extends BoardPanel {
//    boolean ready = false;
//    int numShipTiles = 10;
    List<List<Integer>> shipList = new ArrayList<>();
    //ArrayList<Tile> myTiles;
    //Boolean ships = true;

    //has a list of ships
    //allows you to place the ships
    //does not have the list of tiles
    //show tiles in the blackboard

    //need to show where opponent has shot!!!!!!!!!!!!!

    //placing ship: blackboard.tiles.getindex -> if water, make ship

    //connection between what person is seeing and what is stored in data

    public MyBoard() {}


//        for(int i = 0; i<size; i++){
//            myTiles.add(new Tile(i));
//            screen.add(myTiles.get(i));
//        }


//    public void placeShip(){
//        List<Tile> tiles = Blackboard.getBlackboard().getTileList();
//
//        //MyBoardController.getInstance().actionPerformed();
//    }

    /**
     * Shows where the opponent has taken a shot
     * @param idx the index of the tile where the opponent took a shot
     */
    public void placeOppShot(Integer idx){
        Tile t = Blackboard.getBlackboard().getTileList().get(idx);
        for (List<Integer> tiles : shipList){
            //check if whole ship is sunken
            int size = tiles.size();
            for (Integer tile : tiles){
                if (Blackboard.getBlackboard().getTileList().get(tile).shot == Tile.ShotType.HIT){
                    size -= 1;
                }
            }
            //change view if whole ship is sunken
            if (size == 0){
                for (Integer tile : tiles) {
                    Blackboard.getBlackboard().getTileList().get(tile).setTileType(Tile.TileType.SUNK);
                    Blackboard.getBlackboard().getTileList().get(tile).updateView();
                }
            }
            //check if hit or miss
            if (tiles.contains(idx)){
                t.setShot(Tile.ShotType.HIT);
            } else{
                t.setShot(Tile.ShotType.MISS);
            }
        }


    }

    public List<List<Integer>> getShipList() {
        return shipList;
    }

    @Override
    public void update(MyObservable ob) {

    }
}
