package Asgn03;

import Asgn02.src.DotApp;
import Asgn02.src.DrawArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * Game Class - battleship app with GUI and functionality
 */
public class Game extends JFrame implements ActionListener {

    /**
     * Main creates a new battleship window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        Game window = new Game();
        window.setSize(900,500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Game constructor
     * Uses inheritance from JFrame to create a window with the elements
     * necessary for a GUI with battleship functionality.
     */
    public Game(){
        super("BattleShip App");

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu edit = new JMenu("Edit");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem erase = new JMenuItem("Erase");
        setJMenuBar(menuBar);

        menuBar.add(edit);
        edit.add(undo);
        edit.add(erase);

        //


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
