/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : Group.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Class Group that helps to form a group of recipients
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */

package mail;
import java.util.ArrayList;
import java.util.List;


public class Group {
   
   private final List<Person> membersOfGroup;
   
   public Group(){
      membersOfGroup = new ArrayList<>();
   }
   
   // Add recepient to a group
   public void addRecipient(Person person){
      membersOfGroup.add(person);
   }
   
   // Add a recipient to a group
   public void addRecipient(List<Person> membres){
      membres.stream().forEach((person) -> {
         membersOfGroup.add(person);
      });
   }
   
   // Get the recipients of a group
   public List<Person> getRecipients(){
      return new ArrayList<>(membersOfGroup);
   }

}
