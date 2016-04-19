/*
 -----------------------------------------------------------------------------------
 Laboratoire : Laboratoire SMTP
 Fichier     : SmtpMailRobot.java
 Auteur(s)   : Pascal Sekley & Ibrahim Ounon
 Date        : Debut: 06.04.16 Fin: 20.04.16
 But         : This class starts the programme, reads the configuration and reads the
               file to fetch all the data necessary fot hte program.
 Remarque(s) :
 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */
package amorce;

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
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;
import utils.Parseur;

public class SmtpMailRobot {

   private static String smtpServerAddress;
   private static int smtpServerPort;
   private static int numberOfGroups;
   private static int numberOfPersonsPerGroup;
   private static String myHostName;
   private int thresholdPerGroup;
   private final String msgDelim;
   private String strMessages;
   private static String[] tabMessages;
   private static PrintWriter out = null;

   private static ArrayList<String> adressList;

   public SmtpMailRobot() {
      this.msgDelim = "==";
      SmtpMailRobot.adressList = new ArrayList<>();
   }

   public void start() {
      Properties prop = new Properties();
      InputStream input = null;

      try {
         input = new FileInputStream("config.properties");

         // Load properties
         prop.load(input);

         // Get properties values
         smtpServerAddress = prop.getProperty("smtpServerAddress");
         smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
         numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
         numberOfPersonsPerGroup = Integer.parseInt(prop.getProperty("sizeForGroup"));
         thresholdPerGroup = Integer.parseInt(prop.getProperty("thresholdPerGroup"));
         myHostName = prop.getProperty("myHostName");

         // Get address from the file
         Parseur p = new Parseur();
         try {
            p.parse("victimsAdress.txt");
            strMessages = Parseur.readFile("victimsMessages.txt");
            tabMessages = Parseur.parser(strMessages, msgDelim, true);
            //System.out.println("tailleTabMessage: " + tabMessages.length);
         } catch (IOException e) {
            System.out.println("Failed to open the file to parse ...");
         }
         // To check
         adressList = p.getAdressList();

         // Verify if the configuration properties help to do the prank or not.
         if ((numberOfPersonsPerGroup < thresholdPerGroup) || (numberOfGroups * numberOfPersonsPerGroup) > adressList.size()) {
            System.out.println(" EEREUR: Verifiz les parametre de config SVP ");
         }

      } catch (IOException ex) {
         ex.getMessage();
      } finally {
         if (input != null) {
            try {
               input.close();
            } catch (IOException ex) {
               ex.getMessage();
            }
         }
      }

   }

   /*
    Description: This fonction creat and opens a socket in relation to the 
                 smtp server.
    */
   public void connect() {
      try {
         InputStream input = null;

         Socket smtpSocket;
         InputStream inn;
         OutputStream outt;

         // Create a new socket for communication
         smtpSocket = new Socket(SmtpMailRobot.getSmtpserverAddress(), SmtpMailRobot.getSmtpServerPort());
         inn = smtpSocket.getInputStream();
         outt = smtpSocket.getOutputStream();

         out = new PrintWriter(new OutputStreamWriter(outt), true);
         if (inn == null || outt == null) {
            System.out.println("Failed to open streams to socket ...");
         } else {
            System.out.println("Socket opened successfuly ...");
         }
      } catch (IOException ex) {
         Logger.getLogger(SmtpMailRobot.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   // Get the stream that helps to write to the server
   public static PrintWriter getStream() {
      return out;
   }

   // Get the server smtp address
   public static String getSmtpserverAddress() {
      return smtpServerAddress;
   }
   
   // Get the server smtp port. Returns the server's port
   public static int getSmtpServerPort() {
      return smtpServerPort;
   }

   // Get the list of address to use to send the email
   public static ArrayList<String> getAddressList() {
      return new ArrayList(adressList);
   }
   
   // Get the number of groups to send the message
   public static int getNumberOfGroupes() {
      return numberOfGroups;

   }
   
   // Get the number of persons in a group
   public static int getNumberOfPersonsPerGroup() {
      return numberOfPersonsPerGroup;
   }
   
   // Get an array ok the prank messages to send
   public static String[] getTabMessages() {
      return tabMessages;
   }

   public static void main(String[] args) {
      SmtpMailRobot smtpRobot = new SmtpMailRobot();
      ArrayList<Prank> myPranks = new ArrayList<>();
      int numberOfPranks;

      // Start the programme
      smtpRobot.start();

      PrankGenerator prankGen = new PrankGenerator();
      myPranks = prankGen.generatePrank();
      numberOfPranks = myPranks.size();

      // Send pranks to each group
      SmtpClient client = new SmtpClient();
      for (int i = 0; i < numberOfPranks; i++) {
         try {
            smtpRobot.connect();
            client.sendMessage(myPranks.get(i), SmtpMailRobot.getStream());
         } catch (IOException ex) {
            Logger.getLogger(SmtpMailRobot.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

   }

}
