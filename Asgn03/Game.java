package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @version 1.0
 * Game Class - battleship app with GUI and functionality
 */
public class Game extends JFrame implements ActionListener {
    private JPanel myBoard;
    private OpponentBoard opponentBoard;
    private MyBoard MyBoardController = new MyBoard();
    private String Player;

    /**
     * Game constructor
     * @param player - String to determine the title of the window - Player1 or Player2
     *      Uses inheritance from JFrame to create a for a GUI a battleship
     */
    public Game(String player) {
        super(player);
        this.Player = player;

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
        setLayout(new BorderLayout());
        opponentBoard = new OpponentBoard();
        myBoard = setUpMyBoard();

        JPanel boards = new JPanel();
        boards.setLayout(new GridLayout(1, 2));
        boards.add(opponentBoard);
        boards.add(myBoard);

        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(1, 2));
        JLabel myLabel = new JLabel("MY BOARD");
        JLabel oppLabel = new JLabel("OPPONENT'S BOARD");
        labels.add(oppLabel);
        labels.add(myLabel);

        add(boards, BorderLayout.CENTER);
        add(Blackboard.getBlackboard().getStatus(), BorderLayout.NORTH);
        add(labels, BorderLayout.SOUTH);
        myBoard.setBackground(new Color(100, 0, 250, 50));
        opponentBoard.setBackground(new Color(250, 50, 0, 50));

    }

    /**
     * getPlayer
     * @return String representing player number - Player1 or Player2
     */
    public String getPlayer() {
        return Player;
    }

    /**
     * setUpMyBoard
     * @return JPanel - Jpanel set up to be a MyBoard
     */
    public JPanel setUpMyBoard() {
        JPanel shipScreen = new JPanel();
        shipScreen.setLayout(new GridLayout(11, 11, -1, -1));
        shipScreen.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        shipScreen.add(new JLabel(""));
        int value = 0;
        for (int i = 1; i < 121; i++) {
            if (i < 11)
                shipScreen.add(new JLabel("     " + i));
            else if (i % 11 == 0) {
                int alpha = (i % 10 == 0) ? 10 : i % 10;
                shipScreen.add(new JLabel("   " + (char) (alpha + 64)));

            } else {
                Blackboard.getBlackboard().addTile(new Tile(value));
                value += 1;
                shipScreen.add(Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() - 1));
                Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() - 1).addActionListener(MyBoardController);
            }
        }

        return shipScreen;
    }

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
