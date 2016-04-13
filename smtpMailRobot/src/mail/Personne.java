/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mail;

/**
 * 
 * @author Pascal SEKLEY
 */
public class Personne {
   
   private final String nom;
   private final String prenom;
   private final String address;
   
   
   
   public Personne(String nom, String prenom, String address){
      this.nom = nom;
      this.prenom = prenom;
      this.address = address;
   }
   
   public String getNom(){
      return nom;
   }
   
   public String getPrenom(){
      return prenom;
   }
   
   public String getAddress(){
      return address;
   }
       
           
   

}
