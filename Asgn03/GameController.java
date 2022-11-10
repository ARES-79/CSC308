package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * GameController Class - controller to connect GUI of the game window with functionality
 */
public class GameController implements ActionListener {

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides interactivity for the Reset menu item elements
     *      also logs the request to reset
     * @param e ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Reset")) {
            Blackboard blackboard = Blackboard.getBlackboard();
            ServerDTO transfer = new ServerDTO("Reset", -1, null);
            try {
                blackboard.getClient().sendObject(transfer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            blackboard.reset();
        }
    }
}
