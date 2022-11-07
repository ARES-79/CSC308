package Asgn03;

import java.io.Serializable;
import java.util.List;

public class ServerDTO implements Serializable {
    private String message;
    private int tileIndex;


    private List<List<Integer>> ships;

    public ServerDTO(String message, int tileIndex, List<List<Integer>> ships){
        this.message = message;
        this.tileIndex = tileIndex;
        this.ships = ships;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTileIndex() {
        return this.tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public List<List<Integer>> getShips() {
        return ships;
    }

    public void setShips(List<List<Integer>> ships) {
        this.ships = ships;
    }
}
