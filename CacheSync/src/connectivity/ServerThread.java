package connectivity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import datastructures.*;
import java.util.HashSet;
import my.cachesync.CacheSyncUI;
/**
 * @author Aparna
 */
public class ServerThread extends Thread{
    private Thread t;
    private final javax.swing.JTextArea outputField;
    
    /**
     * Starts a server listening for socket connections on port 9090
     * @param statusField the text area to print status messages
     */
    public ServerThread(javax.swing.JTextArea statusField) {
        outputField = statusField;
        System.out.println("Starting Server!");
        outputField.append("\nListening for incoming connections...");
    }        
    @Override
    public void start() {
        System.out.println("Start Server...");
        if (t==null) {
            t = new Thread (this, "Server");
            t.start();
        }   
    }

    /**
     * Spawns a comm thread for each incoming connection
     */
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

    private static class Comm extends Thread {
        private Thread t;
        private final Socket socket;
        private final javax.swing.JTextArea outputField;
        public Comm(Socket socket, javax.swing.JTextArea statusField) {
            this.socket = socket;
            outputField = statusField;
            System.out.println("Starting Comm!");
            log("New connection with client at " + socket);
        }

        /**
         * Prints welcome message to new connection 
         * Listens for incoming sync requests
         * on receiving request, sends out list of queries to sync
         */
        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream (new BufferedInputStream(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Welcome to Data Cache Sync App");
                // reads an int which is the number of bytes expected for the BF
                int bloomFilterSize = in.readInt();
                System.out.println("\nReceived bloom filter size: " + bloomFilterSize);
                // reads bloomfilter bytes, makes a bloomfilter
                byte[] bloomFilterBytes = new byte[bloomFilterSize];
                in.readFully(bloomFilterBytes);
                System.out.println("\nReceived bloom filter data");
                BloomFilter remoteBloomFilter = new BloomFilter(bloomFilterBytes);
                System.out.println("\nReconstructed bloom filter");
                // checks every query against the Bloom filter and saves ones not present into a String
                String queriesToSend = "\t";
                HashSet<String> localQueries = CacheSyncUI.getQueries();
                System.out.println("\nStarted Query search");
                for (String query : localQueries) {
                    if (!remoteBloomFilter.contains(query)) 
                        queriesToSend = queriesToSend.concat(query+"\t");
                }
                System.out.println("\nFinished Query search");
                System.out.println("\n Sending:" + queriesToSend + " That's all!");
                // sends query string
                out.println(queriesToSend);
                System.out.println("\nSent Queries");
                
//                while (true) {
//                    //BloomFilter input = (BloomFilter)in.read();
//                   String input = in.toString();
//                    if (input == null || input.equals(".")) {
//                        break;
//                    }
//                    log("Client says " + input.toUpperCase());
//                    
//                }
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
