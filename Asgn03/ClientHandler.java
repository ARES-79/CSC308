package Asgn03;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    final Socket mynewSocket;

    public ClientHandler(Socket mynewSocket) {
        this.mynewSocket = mynewSocket;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try{
            out = new PrintWriter(mynewSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(mynewSocket.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
                if(line.equals("Exit")){
                    out.close();
                    in.close();
                    break;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println("Closing a client");
            // closing resources
            assert out != null;
            out.close();
            assert in != null;
            in.close();
            mynewSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
