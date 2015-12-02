package connectivity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import datastructures.*;
import java.util.List;
import my.cachesync.CacheSyncUI;
/**
 * @author Aparna
 */
public class ServerThread extends Thread{
    private Thread t;
    private static javax.swing.JTextArea outputField;
    
    /**
     * Starts a server listening for socket connections on port 9090
     * @param statusField the text area to print status messages
     */
    public ServerThread(javax.swing.JTextArea statusField) {
        outputField = statusField;
        log("Listening for incoming connections...");
    }        
    @Override
    public void start() {
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
                new Comm(listener.accept()).start();
                }
            } finally {
                listener.close();
            }
        } catch (IOException e) {
            System.out.println("Server IO Exception caught!");
            e.printStackTrace();
        }
    }
   
    private static void log(String message) {
            outputField.append(message);
        }

    private static class Comm extends Thread {
        private Thread t;
        private final Socket socket;
        public Comm(Socket socket) {
            this.socket = socket;
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
                // reads bloomfilter bytes, makes a bloomfilter
                byte[] bloomFilterBytes = new byte[bloomFilterSize];
                in.readFully(bloomFilterBytes);
                log("\nReceived bloom filter data:" + bloomFilterSize + " bytes");
                BloomFilter remoteBloomFilter = new BloomFilter(bloomFilterBytes);
                // checks every query against the Bloom filter and saves ones not present into a String
                String queriesToSend = "";
                List<String> localQueries = CacheSyncUI.myTrie.list_all();
                int queryCount = 0;
                for (String query : localQueries) {
                    if (!remoteBloomFilter.contains(query)) {
                        queriesToSend = queriesToSend.concat(query+"\t");
                        queryCount += 1;
                    }
                }
                // sends query string
                out.println(queriesToSend);
                log("\nData sent: "+ queryCount + " queries");
            } catch (IOException e) {
                log("Error handling client: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection closed");
            }
        }
    }
}
