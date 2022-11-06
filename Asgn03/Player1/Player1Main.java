package Asgn03.Player1;

import Asgn03.Client;
import Asgn03.Game;
import Asgn03.Server;

import javax.swing.*;

public class Player1Main {
    Client client;
    Server server;
    Thread serverThread;
    Thread clientThread;
    public Player1Main(){}


    public synchronized void serverSetup() throws InterruptedException {
        this.server = new Server(6666);
        this.client = new Client("localhost", 4444);
        this.serverThread = new Thread(thisserver);
        this.clientThread = new Thread(this.client);
        this.serverThread.start();
        this.clientThread.start();
        this.clientThread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        Player1Main player1 = new Player1Main();
        player1.serverSetup();
        Game window = new Game(player1.client, player1.server, "Player1");
        window.setSize(1000, 600);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
