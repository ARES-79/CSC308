package Asgn03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connection Established");
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);System.out.println("message sent");
        String resp = in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
//        Game window = new Game(ClientServerEnum.SERVER);
//        window.setSize(1000, 600);
//        window.setVisible(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Client client=new Client();
        client.startConnection("127.0.0.1", 6666);
        client.sendMessage("Exit");
    }
}
