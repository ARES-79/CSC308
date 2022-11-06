package Asgn03.Player2;

import Asgn03.Client;
import Asgn03.Game;
import Asgn03.Server;

import javax.swing.*;
import java.io.IOException;

public class Player2Main {
    Client client;
    Thread serverThread;
    Thread clientThread;
    public Player2Main(){}


    public synchronized void serverSetup() throws InterruptedException {
        Server server = new Server(4444);
        this.client = new Client("localhost", 6666);
        this.serverThread = new Thread(server);
        this.clientThread = new Thread(client);
        this.serverThread.start();
        this.clientThread.start();
        this.clientThread.join();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Player2Main player2 = new Player2Main();
        player2.serverSetup();
        Game window = new Game(player2.client, "Player2");
        window.setSize(1000, 600);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
