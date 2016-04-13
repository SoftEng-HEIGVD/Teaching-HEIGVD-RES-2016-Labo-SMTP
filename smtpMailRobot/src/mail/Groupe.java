/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package mail;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pascal SEKLEY
 */
public class Groupe {
   
   private List<Personne> membreGroupe = new ArrayList<>();
   
   public void ajouterMembre(Personne personne){
      membreGroupe.add(personne);
   }
   
   public List<Personne> getMembres(){
      return new ArrayList<>(membreGroupe);
   }

}
