package Asgn03;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment 03
 * @author Andrew Estrada
 * @author Jamie Luna
 * @author Mitashi Parikh
 * @version 1.0
 * Game Class - battleship app with GUI and functionality
 */
public class Game extends JFrame {
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
        GameController gc = new GameController();
        reset.addActionListener(gc);

        //center
        setLayout(new BorderLayout());
        OpponentBoard opponentBoard = new OpponentBoard();
        MyBoard myBoard = new MyBoard();

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

}
