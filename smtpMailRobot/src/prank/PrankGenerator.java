/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : PrankGenerator.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Class PrankGenerator that generate a prank for a group of recipients
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */

package prank;

import initiation.SmtpMailRobot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import mail.Group;
import mail.Person;


public class PrankGenerator {
   
   ArrayList<Prank> tabPrank;
   private final int numberOfGroups;
   private final int numberOfAddress;
   private final int numberOfMessages;
   private final int numberOfPersonsPerGroup;
   private int index;
   
   private final ArrayList<String> chosenRecipient;
   private final ArrayList<String> chosenMessages;
   
   private ArrayList<String> adressList;
   private ArrayList<String> messagesList;
   private final ArrayList<Group> tabGroup;
   private final ArrayList<String> tabMessages;
   private ArrayList<Person> tabPerson;
   
   
   public PrankGenerator(){
      tabPrank=new ArrayList<>();
      adressList = new ArrayList<>();
      messagesList = new ArrayList<>();
      tabGroup = new ArrayList<>();
      tabMessages = new ArrayList<>();
      chosenRecipient = new ArrayList<>();
      chosenMessages = new ArrayList<>();
      adressList = SmtpMailRobot.getAddressList();
      numberOfGroups = SmtpMailRobot.getNumberOfGroupes();
      
      // Convertion from an Array to an ArrayList
      messagesList = new ArrayList<>(Arrays.asList(SmtpMailRobot.getTabMessages()));


      numberOfPersonsPerGroup = SmtpMailRobot.getNumberOfPersonsPerGroup();
      numberOfAddress = adressList.size();
      numberOfMessages = messagesList.size();

     
   }

   /*
      Description: Function that generate a message and returns the mesage to be sent
   */
   public String generateMessage(){
      
      for (String adressList1 : messagesList) {
         index = ThreadLocalRandom.current().nextInt(1, numberOfMessages);
         String msg = messagesList.get(index);
         if(!chosenMessages.contains(msg)){
            chosenMessages.add(msg);
            break;
         }
      }
      return messagesList.get(index);
   }
   /*
      Description: Function that generate a prank. It set the groups, set the sender,
                   set the message to be sent to the group.
   */
   public ArrayList<Prank> generatePrank(){
      int nbrPersonne;
      String msgToSend;
      // Pour chaque groupe on choisie des personne aléatoire
      for (int i = 0; i < numberOfGroups; i++) {
         nbrPersonne = numberOfPersonsPerGroup;
         // Création du groupe
         tabGroup.add(i,new Group());

         // Generate a random number that helps to chose the sender and the recipients
         
         while(nbrPersonne != 0){
            index = ThreadLocalRandom.current().nextInt(0, numberOfAddress);
            
            // Check if the recipient has not been selected before
            if(chosenRecipient.contains(adressList.get(index))){
               index = ThreadLocalRandom.current().nextInt(0, numberOfAddress);
            }
            else{
               chosenRecipient.add(adressList.get(index));
               tabGroup.get(i).addRecipient(new Person(adressList.get(index)));
               index = ThreadLocalRandom.current().nextInt(0, numberOfAddress);
               nbrPersonne--;
            }
         }
         
         msgToSend = generateMessage();
         tabMessages.add(i, msgToSend);

      }
      // Construction of the pranks to be sent
      for(int j=0; j<numberOfGroups; j++){
         tabPrank.add(new Prank());
         tabPrank.get(j).setgroup(tabGroup.get(j));
         tabPrank.get(j).setMessage(tabMessages.get(j));
         
         // The sender is selected randomly
         index = ThreadLocalRandom.current().nextInt(0, numberOfPersonsPerGroup);
         tabPrank.get(j).setSender(new Person (tabPrank.get(j).getRecipientGroups().getRecipients().get(index).getAddress()));
      }
      
         /*                        * FOR DEBUG *
      
            This following code is just for debug in order to see in the console what  
            is really sent and the recipients of each messages.
      
                                   * FOR DEBUG *
      
         */
     
         for(int k = 0; k<tabPrank.size(); k++){
            System.out.println("Groupe ---> " + (k+1));
            Person envoy = tabPrank.get(k).getSender();
            Group desti = tabPrank.get(k).getRecipientGroups();
            String mail = tabPrank.get(k).getMessage();
            
            System.out.println("Envoyeur: " + envoy.getAddress());
            System.out.println("Groupe: ");
            for(int w=0; w<desti.getRecipients().size(); w++){
               System.out.println("     " + desti.getRecipients().get(w).getAddress());
            }
            System.out.println("Mail ---> " + mail);
         
      }
      return tabPrank;
   }
  
}
