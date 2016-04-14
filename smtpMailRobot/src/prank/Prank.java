/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prank;

//import java.util.ArrayList;
import java.util.List;
import mail.Groupe;
import mail.Personne;

/**
 * 
 * @author Pascal SEKLEY
 */
public class Prank {
   private Personne envoyeur;
   //private List<Personne> destinataires = new ArrayList<>();
   private Groupe groupeDestinataires;
   private String message;
   
   
   public Personne getEnvoyeur(){
      return envoyeur;
   }
   
   public String getMessage(){
      return message;
   }
   
   public void setMessage(String message){
      this.message = message;
   }
   
   public void ajoutervictimes(List<Personne>destinataires){
      groupeDestinataires.ajouterMembre(destinataires);
      //this.destinataires = destinataires;
   }
   
   
}
