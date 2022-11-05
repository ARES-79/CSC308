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
    private MyBoard myBoard = new MyBoard();
    private YourBoard yourBoard;
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
        shipScreen.setLayout(new GridLayout(11,11, -1, -1));
        shipScreen.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        //List<Tile> tileList = new ArrayList<>();

        shipScreen.add(new JLabel(""));
        int value = 0;
        for(int i = 1; i<121; i++) {
            if (i < 11)
                shipScreen.add(new JLabel("     " + String.valueOf(i)));
            else if (i % 11 == 0){
                int alpha = (i%10 == 0) ? 10 : i%10;
                shipScreen.add(new JLabel("   " + Character.toString((char) (alpha + 64))));
            }
            else{
                myBoard.addTile(new Tile(value));
                value +=1;
                shipScreen.add(myBoard.getMyTiles().get(myBoard.getMyTiles().size() -1));
            }
        }


        add(shootScreen);
        add(shipScreen);

        myBoard.getMyTiles().get(55).setTileType(Tile.TileType.SHIP);
        myBoard.getMyTiles().get(55).updateView();
        myBoard.getMyTiles().get(5).setShot(Tile.ShotType.MISS);
        myBoard.getMyTiles().get(5).updateView();

    }

}
