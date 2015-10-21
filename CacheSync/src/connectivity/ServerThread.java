/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author aparna
 * 
 * A TCP server that runs on port 9090.  When a client connects, it
 * says "Hello World"
 * 
 */
public class ServerThread extends Thread{
    private Thread t;
    private final javax.swing.JTextArea outputField;
    public ServerThread(javax.swing.JTextArea statusField) {
        outputField = statusField;
        System.out.println("Starting Server!");
        outputField.append("\nListening for incoming connections...");
    }
    
    @Override
    public void run() {
        ServerSocket listener;
        try {        
            listener = new ServerSocket(9090);
            try {
                while (true) { 
                new Comm(listener.accept(),outputField).start();
                }
            } finally {
                listener.close();
            }
        } catch (IOException e) {
            System.out.println("Server IO Exception caught!");
            e.printStackTrace();
        }
    }
    
    @Override
    public void start() {
        System.out.println("Start Server...");
        if (t==null) {
            t = new Thread (this, "Server");
            t.start();
        }   
    }

    private static class Comm extends Thread {
        private Thread t;
        private Socket socket;
        private final javax.swing.JTextArea outputField;
        public Comm(Socket socket, javax.swing.JTextArea statusField) {
            this.socket = socket;
            outputField = statusField;
            System.out.println("Starting Comm!");
            log("New connection with client at " + socket);
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Welcome to Data Cache Sync App");

                while (true) {
                    String input = in.readLine();
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    log("Client says " + input.toUpperCase());
                    
                }
            } catch (IOException e) {
                log("Error handling client: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# closed");
            }
        }
     
        
    private void log(String message) {
            outputField.append("\n"+message);
        }
    }

    
}
