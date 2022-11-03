package Asgn03;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * Tile - class in charge of containing data of a cell on the board
 */
public class Tile extends JButton {

    /**
     * TileType - info about the kind of cell
     */
    enum TileType{
        DEFAULT,
        WATER,
        SHIP,
        SUNK
    }

    /**
     * ShotType - info about a shot made on the cell
     */
    enum ShotType{
        DEFAULT,
        HIT,
        MISS
    }

    /*
    Ex:
      JButton shootScreen = new JButton("This will be the shooting screen.");
      shootScreen.setBorderPainted( false );
      shootScreen.addActionListener(controller);
    * */

    TileType tileType;
    ShotType shot;
    int index;

    /**
     * Tile Constructor
     * @param index - index of the tile on the board / in a tile list
     */
    public Tile(int index){
        super();
        this.index = index;
        tileType = TileType.DEFAULT;
        shot = ShotType.DEFAULT;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        addActionListener(Controller.getInstance());
    }

    //various getters
    public TileType getTileType() {return tileType;}
    public ShotType getShot() {return shot;}
    public int getIndex() {return index;}

    //necessary setters
    public void setTileType(TileType tileType) {this.tileType = tileType;}
    public void setShot(ShotType shot) {this.shot = shot;}

    /**
     * updateView - changes the look of a tile
     *  to be used when something is changed or clicked, etc.
     */
    public void updateView(){
        updateColor();
        updateText();
    }

    /**
     * updateText - updateView helper related to shots
     */
    public void updateText(){
        switch(shot){
            case DEFAULT:
                setText("");
                break;
            case MISS:
                setText("0");
                break;
            case HIT:
                setText("X");
                break;
        }
    }

    /**
     * updateColor - updateView helper related to tile type
     */
    public void updateColor(){
        switch(tileType){
            case DEFAULT:
                setBackground(Color.LIGHT_GRAY);
                break;
            case WATER:
                setBackground(Color.CYAN);
                break;
            case SHIP:
                setBackground(Color.GRAY);
                break;
            case SUNK:
                setBackground(Color.RED);
                break;
        }
    }
}
