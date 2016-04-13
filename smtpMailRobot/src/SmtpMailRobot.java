/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;

/**
 * 
 * @author Pascal SEKLEY
 */
public class SmtpMailRobot {
   
   private static String smtpServerAddress;
   private int smtpServerPort;
   private int numberOfGroups;
   private static String myHostName;
   
   
   public void start(){
      Properties prop = new Properties();
      InputStream input = null;
      
      
      Socket smtpSocket;
      InputStream inn;
      OutputStream outt;
      PrintWriter out;
      
      try{
         input = new FileInputStream("config.properties");
         
         // Load properties
         prop.load(input);
         
         // Get properties values
         smtpServerAddress = prop.getProperty("smtpServerAddress");
         smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
         numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
         myHostName = prop.getProperty(myHostName);
         
         // Create a new socket for communication
         smtpSocket = new Socket(smtpServerAddress, smtpServerPort);
         inn = smtpSocket.getInputStream();
         outt = smtpSocket.getOutputStream();
         
         out = new PrintWriter (new OutputStreamWriter(outt), true);
         if (inn == null || outt == null) {
            System.out.println("Failed to open streams to socket ...");
         }
         else{
            System.out.println("Socket opened successfuly ...");
         }
    
      }catch (IOException ex) {
         ex.printStackTrace();
      } finally {
         if (input != null) {
            try {
               input.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
     
   }
   
   public static String getSmtpserverAddress(){
      return smtpServerAddress;
   }
   
   public static String getMyHostName(){
      return myHostName;
   }
   

   public static void main(String[] args) { 
      SmtpMailRobot smtpRobot = new SmtpMailRobot();
      smtpRobot.start();
      
   }
    
   
}
