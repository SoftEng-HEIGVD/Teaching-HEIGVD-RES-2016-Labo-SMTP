/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prank;

import amorce.SmtpMailRobot;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import mail.Groupe;
import mail.Personne;
import utils.Parseur;


/**
 * 
 * @author Pascal SEKLEY
 */
public class PrankGenerator {
   
   Prank myPrank;
   private final int nbrGroupe;
   private final int nbrAdresses;
   private final int tailleParGroup;
   private int index;
   
   private final ArrayList<Integer> tabPersonneChoisie;
   private ArrayList<String> adressList;
   private ArrayList<Groupe> tabGroupe;
   private ArrayList<Personne> tabPersonne;
   
   
   public PrankGenerator(){
      myPrank=new Prank();
      adressList = new ArrayList<>();
      tabGroupe = new ArrayList<>();
      tabPersonneChoisie = new ArrayList<>();
      adressList = SmtpMailRobot.getAddressList();
      nbrGroupe = SmtpMailRobot.getNbrDeGroupes();
      tailleParGroup = SmtpMailRobot.getTailleParGroup();
      nbrAdresses = adressList.size();
     
//      for(int i = 0; i<10; i++){
//         tabPersonneChoisie.add(ThreadLocalRandom.current().nextInt(0, 11));
//         System.out.println(tabPersonneChoisie.get(i));
//      }
     
   }
   
   
   
   public void generatePersonne(){
      
//      for(int i=0; i<nbrAdresses; i++){
//         tabPersonne.add(new Personne(adressList.get(i)));
//      }
      
      Personne personne = new Personne(adressList.get(0));
      
      System.out.println(personne.getNom());
      System.out.println(personne.getPrenom());
      System.out.println(personne.getAddress());
      
      
   }
   
   
   public void generateMessage(){
      String[] message;
      //message = Parseur.parser(null, null);
      
   }
   
   public Prank generatePrank(){
      
      // Pour chaque groupe
      for (int i = 0; i < nbrGroupe; i++) {
         // Création du groupe
         tabGroupe.add(new Groupe());

         // Génération d'un nombre aléatoire correspondant à un individu
         index = ThreadLocalRandom.current().nextInt(0, nbrAdresses + 1);

         // Vérification si cet individu a déjà été choisi ou pas
         for (int j = 0; i < tabPersonneChoisie.size(); j++) {
            // élément existant
            if (tabPersonneChoisie.get(j) == index) {
               // On gérère un autre nombre et on reprends
               index = ThreadLocalRandom.current().nextInt(0, nbrAdresses + 1);
            } else {
               tabPersonneChoisie.add(index);
            }
         }

      }
      return myPrank;
   }
  
}
