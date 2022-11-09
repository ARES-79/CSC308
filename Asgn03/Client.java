package Asgn03;

import java.io.*;
import java.net.Socket;

/**
 * Assignment 03
 *
 * @author Archie Jones
 * @version 1.0
 * Client Class - Handles client side actions of client server interactions.
 */
public class Client implements Runnable, MyObserver {
    private ObjectOutputStream out;
    private final String ip;
    private final int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        Blackboard.getBlackboard().addObserver(this);
    }

    public void run() {
        this.setUp();
    }

    /**
     * During setUp, the client will continuously try and make a connection with the server until one is made
     */
    private void setUp() {
        boolean scanning = true;
        while (scanning) {
            try {
                Socket clientSocket = new Socket(this.ip, this.port);
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                scanning = false;
                System.out.println("Connection Established");
                notify();
            } catch (Exception e) {
                System.out.println("Connection failed");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * @param data -An instance of ServerDTO which contains info about the players shot or ships
     * @throws IOException - An IOException is thrown in case sending the object fails.
     */
    public void sendObject(ServerDTO data) throws IOException {
        out.writeObject(data);
        out.flush();
    }


    /**
     * Handles the setup of the board and sending the users placed ships to the opponents board.
     *
     * @param ob- An instance of MyObservable as part of the Observer Design Pattern.
     * @throws IOException- Exception is thrown for the sendObject function
     */
    @Override
    public void update(MyObservable ob) throws IOException {
        Blackboard blackboard = Blackboard.getBlackboard();
        if (!blackboard.isSentShips() && blackboard.isReadyToSendShips()) {
            ServerDTO serverDTO = new ServerDTO("Ships", 0, blackboard.getMyShipTiles());
            this.sendObject(serverDTO);
            blackboard.setSentShips(true);
            blackboard.getStatus().setText("Waiting for Opponent to finish setup");
        }
        if (blackboard.isSentShips() && blackboard.isReceivedShips()) {
            if (blackboard.isMyTurn()) {
                blackboard.getStatus().setText("Your Turn");
            } else {
                blackboard.getStatus().setText("Opponents turn");
            }
        }
    }
}
