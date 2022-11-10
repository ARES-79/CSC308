package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 2.0
 * MyBoardController - connects the GUI buttons with the model for the ship placement board
 */
public class MyBoardController implements ActionListener {
    MyBoard myboard;

    /**
     * setMyboard - setter
     * @param myboard - myBoard to be used
     */
    public void setMyboard(MyBoard myboard) {
        this.myboard = myboard;
    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides functionality for tile objects within the ship placement board
     * @param e - ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile){
            System.out.println(e.getSource());
            myboard.placeShipPiece((Tile) e.getSource());
        }
    }
}
