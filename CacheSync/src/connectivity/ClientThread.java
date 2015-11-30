package connectivity;

import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import datastructures.*;
import my.cachesync.*;
        
/**
 *
 * @author aparna
 * connects to the given IP address
 * when sync button is pressed, sends the bloom filter
 * expects a string which is all the queries to add delimited by "\t"
 * 
 */
public class ClientThread extends Thread {
    private final String serverIP;
    private final javax.swing.JTextArea outputField;
    private final javax.swing.JButton syncButton;
    
    private BufferedReader in;
    private DataOutputStream out;
    private Socket socket;
    
    public ClientThread(String serverAddress, javax.swing.JTextArea statusField, javax.swing.JButton sync) {
        serverIP = serverAddress;
        outputField = statusField;
        syncButton = sync;
        System.out.println("Client initialized!");  
        
        syncButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                BloomFilter localBloomFilter = CacheSyncUI.getBloomFilter();
                int bloomFilterSize = localBloomFilter.size()/8; //number of bytes
                System.out.println("\nCalculated bloom filter size in bytes: " + bloomFilterSize);
                outputField.append("\nSending data to " + serverIP);
                byte[] bytesToSend = localBloomFilter.toByteArray(); 
                try { 
                    out.writeInt(bloomFilterSize);     //send number of bytes
                    out.write(bytesToSend);         // send byte data
                    outputField.append("\n" + bloomFilterSize + " bytes sent to " + serverIP);
                    //read in string of queries
                    String response = in.readLine();
                    System.out.println("Received response: " + response);
                    String[] queries = response.split("\t");
                    if (queries.length!=0) {
                        for (String query : queries) {
                            //add query to Tarana's data structure.
                            System.out.println("Read Query: "+query);
                        }
                        outputField.append("\n" + queries.length + " queries added to local dataset");
                    }
                    outputField.append("\n Data is synced!");
                    //Consider closing connection here?
//                    if (response == null || response.equals("")) {
//                          System.exit(0);
//                      }
                } catch (IOException ex) {
                    System.out.println("Client IO Exception caught! Error: " + ex);
                }
            }});
    }
    
    @Override
    public void run() {
        try { 
            socket = new Socket(serverIP, 9090);
            System.out.println("Client socket initialized!");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); 
            System.out.println("Client waiting for response!");
            String response = in.readLine();  
            System.out.println("Client read response!");
            outputField.append("\n" + serverIP + " says " + response); 
        } catch (IOException e) {
            System.out.println("Client IO Exception caught!");
        }
    }
    
}