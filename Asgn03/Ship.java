package Asgn03;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    List<Integer> startingTiles;
    List<Integer> tilesLeft;

    public Ship(List<Integer> tiles){
        this.startingTiles = tiles;
        this.tilesLeft = new ArrayList<>(tiles);
    }

    public boolean checkShot(int shot){
        if (tilesLeft.contains(shot)){
            tilesLeft.remove((Integer) shot);
            return true;
        }
        return false;
    }

    public boolean checkSunk(){
        return tilesLeft.size() == 0;
    }
}
