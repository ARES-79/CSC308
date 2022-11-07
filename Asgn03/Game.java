package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 03
 *
 * @author Andrew Estrada
 * @version 1.0
 * Game Class - battleship app with GUI and functionality
 */
public class Game extends JFrame implements ActionListener {
    private JPanel myBoard;
    private OpponentBoard opponentBoard;
    private MyBoard MyBoardController = new MyBoard();

    /**
     * Main creates a new battleship window and allows it to be seen and closed properly.
     */
//    public static void main(String[] args) {
//        Game window = new Game(null);
//        window.setSize(1000, 600);
//        window.setVisible(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }

    /**
     * Game constructor
     * Uses inheritance from JFrame to create a window with the elements
     * necessary for a GUI with battleship functionality.
     */

    public Game(String player){
        super(player);

        Blackboard.getBlackboard().setMyTurn(player.equals("Player1"));


        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu edit = new JMenu("Edit");
        JMenuItem reset = new JMenuItem("Reset");
        setJMenuBar(menuBar);

        menuBar.add(edit);
        edit.add(reset);
        reset.addActionListener(this);

        //center
        setLayout(new GridLayout(1, 3));
        //the two JLabels will be changed to Board Panels
        opponentBoard = new OpponentBoard();
        myBoard = setUpMyBoard();

        add(opponentBoard);
        add(myBoard);
        add(Blackboard.getBlackboard().getStatus());

//        Blackboard.getBlackboard().getTileList().get(55).setTileType(Tile.TileType.SHIP);
//        Blackboard.getBlackboard().getTileList().get(55).updateView();
//        myBoard.getMyTiles().get(5).setShot(Tile.ShotType.MISS);
//        myBoard.getMyTiles().get(5).updateView();

    }

    public JPanel setUpMyBoard(){
        JPanel shipScreen = new JPanel();
        shipScreen.setLayout(new GridLayout(11, 11, -1, -1));
        shipScreen.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        //List<Tile> tileList = new ArrayList<>();
        shipScreen.add(new JLabel(""));
        int value = 0;
        for (int i = 1; i < 121; i++) {
            if (i < 11)
                shipScreen.add(new JLabel("     " + String.valueOf(i)));
            else if (i % 11 == 0) {
                int alpha = (i % 10 == 0) ? 10 : i % 10;
                shipScreen.add(new JLabel("   " + Character.toString((char) (alpha + 64))));

            }
            else{
                Blackboard.getBlackboard().addTile(new Tile(value));
                value +=1;
                shipScreen.add(Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() -1));
                Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() -1).addActionListener(MyBoardController);
            }
        }

        return shipScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
//        if (e.getActionCommand().equals("Reset")){
//            myBoard = setUpMyBoard();
//            opponentBoard = new OpponentBoard();
//            Blackboard.getBlackboard().
//            private java.util.List<Tile> myTileList = new ArrayList<>();
//            private List<List<Integer>> enemyShipTiles = new ArrayList<>();
//            //opponentShips
//            int shotIndex;
//            boolean myTurn;
//        }
    }
}
