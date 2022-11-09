package Asgn03;

import java.io.Serializable;
import java.util.List;

/**
 * Assignment 03
 *
 * @author Archie Jones
 * @version 1.0
 * ServerDTO- Transfer Object for sending information between players
 */
public class ServerDTO implements Serializable {
    private final String message;
    private final int tileIndex;
    private final List<List<Integer>> ships;

    public ServerDTO(String message, int tileIndex, List<List<Integer>> ships) {
        this.message = message;
        this.tileIndex = tileIndex;
        this.ships = ships;
    }

    public String getMessage() {
        return message;
    }

    public int getTileIndex() {
        return this.tileIndex;
    }

    public List<List<Integer>> getShips() {
        return ships;
    }

}
