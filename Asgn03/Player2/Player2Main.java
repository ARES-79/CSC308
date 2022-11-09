package Asgn03.Player2;

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
 * Client Class - Creates Player2 of the game.
 */
public class Player2Main {
    private Client client;

    public Player2Main() {
    }

    public Client getClient() {
        return client;
    }

    /**
     * Creates a new client and server for player 2 and puts them in different threads, so they can run
     * at the same time.
     */
    public synchronized void serverSetup() throws InterruptedException {
        Server server = new Server(4444);
        this.client = new Client("localhost", 6666);
        Thread serverThread = new Thread(server);
        Thread clientThread = new Thread(this.client);
        serverThread.start();
        clientThread.start();
        clientThread.join();
    }

    /**
     * Sets up the game and blackboard for player 2
     */
    public static void main(String[] args) throws InterruptedException {
        Player2Main player2 = new Player2Main();
        player2.serverSetup();
        Game game = new Game("Player2");
        Blackboard blackboard = Blackboard.getBlackboard();
        blackboard.setGameBoard(game);
        blackboard.setClient(player2.getClient());
        blackboard.getGameBoard().setSize(1000, 600);
        blackboard.getGameBoard().setVisible(true);
        blackboard.getGameBoard().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
