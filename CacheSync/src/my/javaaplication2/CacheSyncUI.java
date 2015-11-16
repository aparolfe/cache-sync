/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.javaaplication2;
import connectivity.*;
//import filechosingtest.*;
//import static filechosingtest.JFilePicker.MODE_OPEN;
//import static filechosingtest.JFilePicker.MODE_SAVE;

import java.awt.FlowLayout;
import java.io.File; 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Tarana
 * User interface for Cache Sync application
 */
public class CacheSyncUI extends javax.swing.JFrame {

private ServerThread s;
private ClientThread c;

private String textFieldLabel;
private String buttonLabel;

private JLabel label;
private JTextField textField;
private JButton button;

private JFileChooser fileChooser;
//int EXIT_ON_CLOSE;
    
public static void main(String args[]) {
        final CacheSyncUI ui = new CacheSyncUI();
        ui.s = new ServerThread(ui.statusField);
        ui.s.start();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ui.setVisible(true);
            }
        });
        
    }

    /**
     * Creates new form CacheSyncUI
     */
    public CacheSyncUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ipField = new javax.swing.JTextField();
        connect = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        uploadField = new javax.swing.JTextField();
        browse = new javax.swing.JButton();
        queryField = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        sync = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        suggestions = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusField = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        ipField.setText("IP address");
        ipField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipFieldActionPerformed(evt);
            }
        });

        connect.setBackground(new java.awt.Color(153, 153, 153));
        connect.setText("Connect");
        connect.setMaximumSize(new java.awt.Dimension(53, 23));
        connect.setMinimumSize(new java.awt.Dimension(53, 23));
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));

        uploadField.setEditable(false);
        uploadField.setText("File name");
        uploadField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFieldActionPerformed(evt);
            }
        });

        browse.setBackground(new java.awt.Color(153, 153, 153));
        browse.setText("Browse");
        browse.setMaximumSize(new java.awt.Dimension(53, 23));
        browse.setMinimumSize(new java.awt.Dimension(53, 23));
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        queryField.setText("Search Query");
        queryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryFieldActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(204, 204, 204));
        search.setText("Search");
        search.setMaximumSize(new java.awt.Dimension(53, 23));
        search.setMinimumSize(new java.awt.Dimension(53, 23));
        search.setPreferredSize(new java.awt.Dimension(53, 23));
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        sync.setBackground(new java.awt.Color(153, 153, 153));
        sync.setText("Sync");
        sync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncActionPerformed(evt);
            }
        });

        title.setBackground(new java.awt.Color(102, 102, 102));
        title.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 255, 204));
        title.setLabelFor(jPanel1);
        title.setText("Cache Sync");

        suggestions.setText("Queries Listed here");
        suggestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestionsActionPerformed(evt);
            }
        });

        statusField.setBackground(new java.awt.Color(51, 51, 51));
        statusField.setColumns(20);
        statusField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statusField.setForeground(new java.awt.Color(0, 255, 153));
        statusField.setRows(5);
        statusField.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        statusField.setEnabled(false);
        jScrollPane1.setViewportView(statusField);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                            .addComponent(ipField)
                            .addComponent(uploadField)
                            .addComponent(queryField)
                            .addComponent(suggestions, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(connect, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(browse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sync, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(46, 46, 46))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connect, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sync, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(queryField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(suggestions, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   // public class TestJFilePicker extends JFrame{
        //super("Test using JFilePicker");
      //  setLayout(new FlowLayout();
        
        
        
        
    
  //  }
    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        String ipAddress = ipField.getText();
        statusField.append("\nTrying to connect to "+ ipAddress +"...");
        c = new ClientThread(ipAddress, uploadField, statusField, sync);
        c.start();
        connect.setText("Reset");
    }//GEN-LAST:event_connectActionPerformed

    private void queryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_queryFieldActionPerformed

    private void syncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_syncActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void suggestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suggestionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suggestionsActionPerformed

    private void uploadFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFieldActionPerformed
       
    }//GEN-LAST:event_uploadFieldActionPerformed

    private void ipFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipFieldActionPerformed
    
    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
       fileChooser= new JFileChooser();
       setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
       fileChooser.showOpenDialog(this);
       fileChooser.setCurrentDirectory(new File("D:/"));
       add(fileChooser);       
       uploadField.setText(fileChooser.getSelectedFile().getAbsolutePath());
       setLocationRelativeTo(null);    // center on screen
       fileChooser.setVisible(false);
    }//GEN-LAST:event_browseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browse;
    private javax.swing.JButton connect;
    private javax.swing.JTextField ipField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField queryField;
    private javax.swing.JButton search;
    private javax.swing.JTextArea statusField;
    private javax.swing.JTextField suggestions;
    private javax.swing.JButton sync;
    private javax.swing.JLabel title;
    private javax.swing.JTextField uploadField;
    // End of variables declaration//GEN-END:variables
}
