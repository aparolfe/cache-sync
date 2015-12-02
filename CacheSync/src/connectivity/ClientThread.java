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
 * @author Aparna
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
        
        syncButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                BloomFilter localBloomFilter = CacheSyncUI.getBloomFilter();
                int bloomFilterSize = localBloomFilter.size()/8; //number of bytes
                log("\nSending data to " + serverIP + "... ");
                byte[] bytesToSend = localBloomFilter.toByteArray(); 
                try { 
                    out.writeInt(bloomFilterSize);          //send number of bytes
                    out.write(bytesToSend);                 // send byte data
                    log(bloomFilterSize + " bytes sent");
                    String response = in.readLine();        //read in string of queries
                    int responseSize = response.getBytes().length;
                    log("Received response: " + responseSize + " bytes");
                    if (responseSize != 0) {
                        String[] queries = response.split("\t");
                        if (queries.length!=0) {
                            for (String query : queries) {  // add queries to trie
                                CacheSyncUI.myTrie.add(query, 1);   
                            }
                        }
                        log("\n" + queries.length + " queries added to local dataset");
                        log("\nData is synced!\n");
                    }
                    else
                        log("\nData is already synced\n");
                } catch (IOException ex) {
                    System.out.println("Client IO Exception caught! Error: " + ex);
                }
            }});
    }
    
    @Override
    public void run() {
        try { 
            socket = new Socket(serverIP, 9090);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); 
            String response = in.readLine();  
            log(serverIP + " says " + response); 
        } catch (IOException e) {
            System.out.println("Client IO Exception caught!");
        }
    }
    
    private void log(String message) {
            outputField.append("\n"+message);
        }
    
}