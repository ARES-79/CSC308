package Asgn03;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * Tile - class in charge of containing data of
 */
public class Tile extends JButton {

    enum TileType{
        DEFAULT,
        WATER,
        SHIP,
        SUNK
    }

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

    public Tile(int index){
        super();
        this.index = index;
        tileType = TileType.DEFAULT;
        shot = ShotType.DEFAULT;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        addActionListener(Controller.getInstance());
    }

    public TileType getTileType() {
        return tileType;
    }

    public ShotType getShot() {
        return shot;
    }

    public int getIndex() {
        return index;
    }

    public void setTileType(TileType tileType) {this.tileType = tileType;}

    public void setShot(ShotType shot) {this.shot = shot;}

    public void updateView(){
        updateColor();
        updateText();
    }

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
