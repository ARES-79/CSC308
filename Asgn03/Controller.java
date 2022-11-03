package Asgn03;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment 03
 * @author Andrew Estrada
 * ...
 * @version 1.0
 * Controller Class - the controller of the MVC pattern
 *          responsible for connecting the view and the model
 *          Singleton pattern
 */
public class Controller implements ActionListener {

    private static Controller controller;

    protected Controller(){}

    /**
     * Singleton Pattern getInstance()
     * @return static Controller instance
     */
    public static Controller getInstance(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides interactivity for the BattleShip elements
     * @param e ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//       System.out.println(e.getActionCommand());
        if (e.getSource() instanceof Tile){
            Tile temp = (Tile) e.getSource();
            System.out.println(
                    "index: " + temp.getIndex() +
                    " type: " + temp.getTileType() +
                    " shot: " + temp.getShot());
        }
    }
}
