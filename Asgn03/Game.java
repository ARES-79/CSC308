package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * Game Class - battleship app with GUI and functionality
 */
public class Game extends JFrame {

    /**
     * Main creates a new battleship window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        Game window = new Game();
        window.setSize(1000, 600);
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
        Controller controller = new Controller();

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu edit = new JMenu("Edit");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem reset = new JMenuItem("Reset");
        setJMenuBar(menuBar);

        menuBar.add(edit);
        edit.add(undo);
        edit.add(reset);

        //center
        setLayout(new GridLayout(1,2));
        //the two JLabels will be changed to Board Panels
        JLabel shootScreen = new JLabel("This will be the shooting screen.");
        JPanel shipScreen = new JPanel();
        shipScreen.setLayout(new GridLayout(10,10, -1, -1));
        shipScreen.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        List<Tile> tileList = new ArrayList<>();
        for(int i = 0; i<100; i++){
            tileList.add(new Tile(i));
            shipScreen.add(tileList.get(i));
        }
        add(shootScreen);
        add(shipScreen);

        tileList.get(55).setTileType(Tile.TileType.SHIP);
        tileList.get(55).updateView();
        tileList.get(5).setShot(Tile.ShotType.MISS);
        tileList.get(5).updateView();

    }

}
