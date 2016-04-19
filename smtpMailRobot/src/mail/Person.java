/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : Person.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Class Person that helps to define the recipient.
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */

package mail;

import utils.Parseur;


public class Person {
   
   private final String name;
   private final String firstName;
   private final String address;
   
   
   // Create a person with his name, first name and address
   public Person(String name, String firstName, String address){
      this.name = name;
      this.firstName = firstName;
      this.address = address;
   }

   // Create a person with his e mail address
   public Person(String address) {
      String[] FirstNameNameArray;
      String delimiter = ".@";
      FirstNameNameArray = Parseur.parser(address, delimiter, false);
      name = FirstNameNameArray[0];
      firstName = FirstNameNameArray[1];
      this.address = address;
   }
   
   // Get the person's name
   public String getName(){
      return name;
   }
   
   // Ger the person's first name
   public String getFirstName(){
      return firstName;
   }
   
   // Get the person's address
   public String getAddress(){
      return address;
   }
       
           
   

}
