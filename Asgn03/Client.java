package Asgn03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        this.setUp();
    }

    private void setUp(){
        boolean scanning = true;
        while (scanning) {
            try {
                clientSocket = new Socket(this.ip, this.port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
        System.out.println("message sent");
        String resp = in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

//    public static void main(String[] args) throws IOException {
//        Client client = new Client();
//        client.startConnection("127.0.0.1", 6666);
////        client.sendMessage("Sent from client");
//        client.sendMessage("Exiti");
////        Game window = new Game();
////        window.setSize(1000, 600);
////        window.setVisible(true);
////        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        System.out.println("Done with client");
//    }
}