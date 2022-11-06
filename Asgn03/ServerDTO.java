package Asgn03;

import java.io.Serializable;

public class ServerDTO implements Serializable {
    private String message;
    private int tileIndex;

    public ServerDTO(String message, int tileIndex){
        this.message = message;
        this.tileIndex = tileIndex;
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
}
