/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author aparna
 * connects to the given IP address and displays messages received from the server
 * 
 */
public class ClientThread extends Thread {
    private final String serverIP;
    private final javax.swing.JTextField uploadField;
    private final javax.swing.JTextArea outputField;
    private final javax.swing.JButton syncButton;
    
    private BufferedReader in;
    private PrintWriter out;
    
    public ClientThread(String serverAddress, javax.swing.JTextField upload, javax.swing.JTextArea statusField, javax.swing.JButton sync) {
        serverIP = serverAddress;
        uploadField = upload;
        outputField = statusField;
        syncButton = sync;
        System.out.println("Client initialized!");  
        syncButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = uploadField.getText(); 
                outputField.append("\nSending '" + message + "' to " + serverIP);
                out.println(uploadField.getText());
//                String response;
//                try {
//                    response = in.readLine();
//                    if (response == null || response.equals("")) {
//                          System.exit(0);
//                      }
//                } catch (IOException ex) {
//                       response = "Error: " + ex;
//                }
//                outputField.append("\n" + response);
            }});
    }
    
    @Override
    public void run() {
        Socket s;
        try { 
            s = new Socket(serverIP, 9090);
            System.out.println("Client socket initialized!");
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));        
            out = new PrintWriter(s.getOutputStream(), true);
            System.out.println("Client waiting for response!");
            String response = in.readLine();  
            System.out.println("Client read response!");
            outputField.append("\n" + serverIP + " says " + response); 
        } catch (IOException e) {
            System.out.println("Client IO Exception caught!");
        }
    }
    
}
