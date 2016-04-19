/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : Prank.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Class Prank that defines the specificity of a prank. The sender, the 
               group of recipients and the message to be sent to a group.
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */

package prank;

import mail.Group;
import mail.Person;


public class Prank {
   
   private Person sender;
   private Group groupeDestinataires;
   private String message;
   
   
   // Get the sender of the message
   public Person getSender(){
      return sender;
   }
   
   // Get the message of the prank
   public String getMessage(){
      return message;
   }
   
   // Get the recipient group
   public Group getRecipientGroups(){
      return groupeDestinataires;
   }
   
   // Set the message of the prank
   public void setMessage(String message){
      this.message = message;
   }
   
   // Set a group of recipients
   public void setgroup(Group groupe){
      this.groupeDestinataires = groupe;
   }
   
   // Set the sender of the mail
   public void setSender(Person personne){
      this.sender = personne;
   }

   
}
