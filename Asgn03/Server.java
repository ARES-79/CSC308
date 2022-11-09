package Asgn03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Assignment 03
 *
 * @author Archie Jones
 * @version 1.0
 * Server Class - Handles server side actions of client server interactions.
 */
public class Server implements Runnable {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    /**
     * Sets up the server and handles input from opponent client using the handInput method.
     */
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ServerDTO inputObject;
            while ((inputObject = (ServerDTO) in.readObject()) != null) {
                this.handleInput(inputObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Class that handles when the opponent sends over data, including when they send
     * their ships in game setup and when they take a shot.
     *
     * @param inputObject- the ServerDTO that was sent from the opponents client
     * @throws IOException- Exception needed for updating the blackboard data
     */
    public void handleInput(ServerDTO inputObject) throws IOException {
        Blackboard blackboard = Blackboard.getBlackboard();
        switch (inputObject.getMessage()) {
            case "Ships" -> {
                blackboard.setEnemyShipTiles(inputObject.getShips());
                blackboard.setReceivedShips(true);
                blackboard.updateData();
            }
            case "Game over" -> {
                blackboard.setGameOver(true);
                blackboard.getStatus().setText("You lost");
            }
            case "Reset" -> blackboard.reset();
            default -> {
                blackboard.setMyTurn(true);
                blackboard.getStatus().setText("Your turn");
                if (inputObject.getMessage().equals(Tile.ShotType.HIT.toString())) {
                    Tile temp = blackboard.getTileList().get(inputObject.getTileIndex());
                    temp.setShot(Tile.ShotType.HIT);
                    temp.updateView();
                } else if (inputObject.getMessage().equals(Tile.ShotType.MISS.toString())) {
                    Tile temp = blackboard.getTileList().get(inputObject.getTileIndex());
                    temp.setShot(Tile.ShotType.MISS);
                    temp.updateView();
                }
            }
        }
    }
}
