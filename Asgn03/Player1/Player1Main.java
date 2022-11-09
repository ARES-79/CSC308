package Asgn03.Player1;

import Asgn03.Blackboard;
import Asgn03.Client;
import Asgn03.Game;
import Asgn03.Server;

import javax.swing.*;


/**
 * Assignment 03
 *
 * @author Archie Jones
 * @version 1.0
 * Client Class - Creates Player 1 of the game.
 */
public class Player1Main {
    private Client client;

    public Player1Main() {
    }

    /**
     * Creates a new client and server for player 1 and puts them in different threads, so they can run
     * at the same time.
     */
    public synchronized void serverSetup() throws InterruptedException {
        this.client = new Client("localhost", 4444);
        Server server = new Server(6666);
        Thread serverThread = new Thread(server);
        Thread clientThread = new Thread(this.client);
        serverThread.start();
        clientThread.start();
        clientThread.join();
    }

    /**
     * Sets up the game and blackboard for player 1
     */
    public static void main(String[] args) throws InterruptedException {
        Player1Main player1 = new Player1Main();
        player1.serverSetup();
        Game game = new Game("Player1");
        Blackboard blackboard = Blackboard.getBlackboard();
        blackboard.setGameBoard(game);
        blackboard.setClient(player1.getClient());
        blackboard.getGameBoard().setSize(1000, 600);
        blackboard.getGameBoard().setVisible(true);
        blackboard.getGameBoard().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Client getClient() {
        return client;
    }
}
