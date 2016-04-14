package amorce;

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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mail.Mail;
import prank.PrankGenerator;
import smtp.SmtpClient;
import utils.Parseur;

/**
 * 
 * @author Pascal SEKLEY
 */
public class SmtpMailRobot {
   
   private static String smtpServerAddress;
   private int smtpServerPort;
   private static int numberOfGroups;
   private static int tailleParGroup;
   private static String myHostName;
   private final int seuilNombreParGroupe = 3;
   private boolean status;
   private final String msgDelim;
   private String strMessages;
   private static String[] tabMessages;
   
   private static ArrayList<String> adressList;

   public SmtpMailRobot() {
      this.msgDelim = "==";
      SmtpMailRobot.adressList = new ArrayList<>();
      status = true;
   }
   
   
   public void start(){
      Properties prop = new Properties();
      InputStream input = null;
      
      
      Socket smtpSocket;
      InputStream inn;
      OutputStream outt;
      PrintWriter out = null;
      
      try{
         input = new FileInputStream("config.properties");
         
         // Load properties
         prop.load(input);
         
         // Get properties values
         smtpServerAddress = prop.getProperty("smtpServerAddress");
         smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
         numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
         tailleParGroup = Integer.parseInt(prop.getProperty("sizeForGroup"));
         myHostName = prop.getProperty("myHostName");
         
         
         // Get address from the file
         Parseur p = new Parseur();
         try {
            p.parse("victimsAdress.txt");
            strMessages = Parseur.readFile("victimsMessages.txt");
            tabMessages = Parseur.parser(strMessages, msgDelim, true);
            System.out.println("tailleTabMessage: " + tabMessages.length);
         } catch (IOException e) {
            System.out.println("Failed to open the file to parse ...");
         }
         // To check
         adressList = p.getAdressList();
         
         // Vérifier si les informations de config peuvent réaliser la plaisanterie
         if((tailleParGroup < seuilNombreParGroupe) || (numberOfGroups*tailleParGroup)>adressList.size()){
            System.out.println(" EEREUR: Verifier les parametre de config ");
            return;
         }
         status = true;
   
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
         System.out.println("Erreur_1");
      } finally {
         if (input != null) {
            try {
               input.close();
            } catch (IOException e) {
               System.out.println("Erreur_2");
            }
         }
      }
      
      Mail msg = new Mail();
      SmtpClient client = new SmtpClient();
      try {
         client.sendMessage(msg, out);
      } catch (IOException ex) {
         Logger.getLogger(SmtpMailRobot.class.getName()).log(Level.SEVERE, null, ex);
      }
     
   }
   
   public static String getSmtpserverAddress(){
      return smtpServerAddress;
   }
   
   public static String getMyHostName(){
      return myHostName;
   }
   
   public static ArrayList<String> getAddressList(){
      return new ArrayList(adressList);
   }
   
   public static int getNbrDeGroupes(){
      return numberOfGroups;

   }
   
   public static int getTailleParGroup(){
      return tailleParGroup;
   }
   

   public static void main(String[] args) { 
      SmtpMailRobot smtpRobot = new SmtpMailRobot();
      smtpRobot.start();
      
      // tester status avant de continuer ou pas
      
      PrankGenerator prankGen = new PrankGenerator();
      prankGen.generatePersonne();
      
      for(int i=0; i<tabMessages.length; i++)
         System.out.println(i+") ---> "+tabMessages[i]);
      //System.out.println("messages: " + tabMessages[1]);
      
   }
   
}
