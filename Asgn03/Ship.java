package Asgn03;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * Ship - class in charge of containing data of groups of SHIP tiles
 * */
public class Ship {

    List<Integer> startingTiles;
    List<Integer> tilesLeft;

    /**
     * Ship Constructor
     * @param tiles - list of tile indices that make up a ship
     */
    public Ship(List<Integer> tiles){
        this.startingTiles = tiles;
        this.tilesLeft = new ArrayList<>(tiles);
    }

    /**
     * checkShot - checks to see if the shot was a success and if so removes the index from tilesLeft
     * @param shot - the index of the attempted shot
     * @return true if there is a ship there, false otherwise
     */
    public boolean checkShot(int shot){
        if (tilesLeft.contains(shot)){
            tilesLeft.remove((Integer) shot);
            return true;
        }
        return false;
    }

    /**
     * checkSunk
     * @return - true if the entire ship has been hit, false otherwise
     */
    public boolean checkSunk(){
        return tilesLeft.size() == 0;
    }
}
