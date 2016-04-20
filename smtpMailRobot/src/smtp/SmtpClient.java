/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : SmtpClient.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Class that implements ISmtpClient interface. 
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */

package smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import mail.Group;
import mail.Person;
import prank.Prank;
import utils.Parseur;


public class SmtpClient implements ISmtpClient {
   

   @Override
   public void sendMessage (Prank prank, PrintWriter out, BufferedReader in) throws IOException{
      
      // Header of the message to be sent
      String header;
      String subjectOfMail;

      // We fetch all the informations that contains the prank to be sent
      Person sender = prank.getSender();
      Group groupeDestinataire = prank.getRecipientGroups();
      String messageToSend = prank.getMessage();
      
       subjectOfMail = Parseur.parser(messageToSend, "\n", false)[1];
      
      
      // We fetch the real message without the subject
      
      messageToSend = messageToSend.substring(subjectOfMail.length()+1);
      
      
      // Number of persons we have to send the mail to.
      int nbrPersonne = groupeDestinataire.getRecipients().size();
      out.print("EHLO Pascal\r\n");
      out.flush();
      
      // We read from the stream the server response till we'are allowed to start sending
      // messages
      String welcome = in.readLine();
      while (welcome != null) {
         if(welcome.startsWith("250 ")){
            System.out.println("Server: " + welcome);
            break;
         }
            System.out.println("Server: " + welcome);
            welcome = in.readLine();
      }
      
      String concatHeader = "From: " + sender.getAddress() + "\r\n" + "To: ";
      for(int i=0; i<nbrPersonne; i++){
         // We send the mail to other members of the group not includint the sender.
         if(!groupeDestinataire.getRecipients().get(i).getAddress().equals(sender.getAddress())){
            concatHeader = concatHeader.concat(groupeDestinataire.getRecipients().get(i).getAddress());
            
            // We still add members till we did not reach the number of persons that
            // are supposed to receive the message.
            if(i != nbrPersonne-1)
               concatHeader = concatHeader.concat(", ");
         }
      }
      header = concatHeader.concat("\r\n") + subjectOfMail;
      
      // We now follow the smtp protocol to send the information to the server
      out.print("MAIL FROM: " + sender.getAddress()+"\r\n");
      out.flush();
      System.out.println("Server: " + in.readLine());
      
      // We set all the recipients of each messages
      for(int i=0; i<nbrPersonne; i++){
         out.print("RCPT TO: " + groupeDestinataire.getRecipients().get(i).getAddress()+"\r\n");
         out.flush();
         System.out.println("Server: " + in.readLine());
      }
      // Setting the data to send
      out.print("DATA\r\n");
      out.flush();
      System.out.println("Server: " + in.readLine());
      
      // Set the header of the messages
      out.print(header+"\r\n");
      out.flush();
      
      // Set the message to send
      out.print(messageToSend+"\r\n");
      out.flush();
      
      // End of the message
      out.print(".\r\n");
      out.flush();
      System.out.println("Server: " + in.readLine());

      
      System.out.println("Mail sent ...");
      
   }

}
