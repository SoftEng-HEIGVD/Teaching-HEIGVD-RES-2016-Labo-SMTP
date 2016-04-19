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

import java.io.IOException;
import java.io.PrintWriter;
import mail.Group;
import mail.Person;
import prank.Prank;
import utils.Parseur;


public class SmtpClient implements ISmtpClient {
   

   @Override
   public void sendMessage (Prank prank, PrintWriter out) throws IOException{
      
      // Header of the message to be sent
      String header;

      // We fetch all the informations that contains the prank to be sent
      Person sender = prank.getSender();
      Group groupeDestinataire = prank.getRecipientGroups();
      String messageToSend = prank.getMessage();
      String subjectOfMail = Parseur.parser(messageToSend, "\n", false)[1];
      
      // We fetch the real message without the subject
      messageToSend = messageToSend.substring(subjectOfMail.length()+1);
      
      // Number of persons we have to send the mail to.
      int nbrPersonne = groupeDestinataire.getRecipients().size();
      out.println("EHLO Pascal");
      
      String concatHeader = "From: " + sender.getAddress() + "\n" + "To: ";
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
      header = concatHeader.concat("\n") + subjectOfMail;
      
      // We now follow the smtp protocol to send the information to the server
      out.println("MAIL FROM: " + sender.getAddress());
      
      for(int i=0; i<nbrPersonne; i++){
         out.println("RCPT TO: " + groupeDestinataire.getRecipients().get(i).getAddress());
      }
      out.println("DATA");
      out.println(header + "\n");
      out.println(messageToSend);
      out.println(".");
      out.println("quit");
    
      
      System.out.println("Mail sent ...");
   }

}
