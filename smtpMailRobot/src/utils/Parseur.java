/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Pascal SEKLEY
 */
public class Parseur {
   private final ArrayList<String> adressList = new ArrayList<>();

   public void parse(String fileName) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader(fileName));
      String line;
      
      // Test if the list is empty
      if(!adressList.isEmpty())
         adressList.clear();
      
      while ((line = in.readLine()) != null) {
         adressList.add(line);

      }
      in.close();
   }
   
   public ArrayList<String> getAdressList(){
	return adressList;
   }
   
   public static String[] parser (String string, String delimiter, boolean isMessages){
      //ArrayList<String> tabPrenomNom = new ArrayList<>();
      String delims;
      if(isMessages){
         delims = "["+delimiter+"]+";
      }
      else{
         delims = "["+delimiter+"]";
      }
      String[] tokens = string.split(delims);
      
//      tabPrenomNom.add(0, tokens[0]);
//      tabPrenomNom.add(1, tokens[1]);
  
      return tokens;
   }
   
   
   public static String readFile(String fileName) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      try {
         StringBuilder sb = new StringBuilder();
         String line = br.readLine();

         while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
         }
         return sb.toString();
      } finally {
         br.close();
      }
   }

}
