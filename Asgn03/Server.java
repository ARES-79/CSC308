package Asgn03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(this.port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if ("Exit".equals(inputLine)) {
                    out.println("good bye");
                    System.out.println("Exiting");
                    break;
                }
                out.println(inputLine);
                System.out.println(inputLine);
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
