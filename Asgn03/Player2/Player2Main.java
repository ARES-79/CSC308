package Asgn03.Player2;

import Asgn03.Blackboard;
import Asgn03.Client;
import Asgn03.Game;
import Asgn03.Player1.Player1Main;
import Asgn03.Server;

import javax.swing.*;
import java.io.IOException;

public class Player2Main {
    Server server;
    Client client;
    Thread serverThread;
    Thread clientThread;
    public Player2Main(){}


    public synchronized void serverSetup() throws InterruptedException {
        this.server = new Server(4444);
        this.client = new Client("localhost", 6666);
        this.serverThread = new Thread(this.server);
        this.clientThread = new Thread(this.client);
        this.serverThread.start();
        this.clientThread.start();
        this.clientThread.join();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Player2Main player2 = new Player2Main();
        player2.serverSetup();
        Game game = new Game("Player2");
        Blackboard blackboard = Blackboard.getBlackboard();
        blackboard.setGameBoard(game);
        blackboard.setClient(player2.client);
        blackboard.getGameBoard().setSize(1000, 600);
        blackboard.getGameBoard().setVisible(true);
        blackboard.getGameBoard().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
