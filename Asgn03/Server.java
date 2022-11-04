package Asgn03;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;


    public void start(int port) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            while (true) {
                Socket newSocket = null;
                newSocket = serverSocket.accept();
                Thread thread = new ClientHandler(newSocket);
                thread.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try{
                    serverSocket.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

}

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);
//        Game window = new Game(ClientServerEnum.SERVER);
//        window.setSize(1000, 600);
//        window.setVisible(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
