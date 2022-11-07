package Asgn03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int port;
    private Blackboard blackboard = Blackboard.getBlackboard();
    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(this.port);
            clientSocket = serverSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            ServerDTO inputObject = null;
            while ((inputObject = (ServerDTO) in.readObject()) != null) {
                Blackboard.getBlackboard().setMyTurn(true);
                if(inputObject.getMessage().equals(Tile.ShotType.HIT.toString())){
                    Tile temp = blackboard.getTileList().get(inputObject.getTileIndex());
                    temp.setShot(Tile.ShotType.HIT);
                    temp.updateView();
                }
                else if(inputObject.getMessage().equals(Tile.ShotType.MISS.toString())){
                    Tile temp = blackboard.getTileList().get(inputObject.getTileIndex());
                    temp.setShot(Tile.ShotType.MISS);
                    temp.updateView();
                }
                else if(inputObject.getMessage().equals("Ships")){
                    Blackboard.getBlackboard().setEnemyShipTiles(inputObject.getShips());
                    Blackboard.getBlackboard().setReceivedShips(true);
                    Blackboard.getBlackboard().updateData();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws IOException {
//        Game window = new Game();
//        window.setSize(1000, 600);
//        window.setVisible(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Server server = new Server();
//        server.start(6666);
//    }
}
