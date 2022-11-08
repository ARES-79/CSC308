package Asgn03.Player1;

import Asgn03.Blackboard;
import Asgn03.Client;
import Asgn03.Game;
import Asgn03.Server;

import javax.swing.*;

public class Player1Main {
    private Client client;
    private Server server;
    private Thread serverThread;
    private Thread clientThread;
    public Player1Main(){}


    public synchronized void serverSetup() throws InterruptedException {
        this.server = new Server(6666);
        this.client = new Client("localhost", 4444);
        this.serverThread = new Thread(this.server);
        this.clientThread = new Thread(this.client);
        this.serverThread.start();
        this.clientThread.start();
        this.clientThread.join();
    }

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

    public void setClient(Client client) {
        this.client = client;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Thread getServerThread() {
        return serverThread;
    }

    public void setServerThread(Thread serverThread) {
        this.serverThread = serverThread;
    }
}
