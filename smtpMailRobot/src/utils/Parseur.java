/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : Parseur.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Utils class that contains some
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */

package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Parseur {
   // List on each line that contain the file given in argument
   private final ArrayList<String> list = new ArrayList<>();

   public void parse(String fileName) throws IOException {
      try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
         String line;
         
         // Test if the list is empty
         if(!list.isEmpty())
            list.clear();
         
         while ((line = in.readLine()) != null) {
            list.add(line);
            
         }
      }
   }
   
   // This methode retruns an array containong elements of the parsed file
   public ArrayList<String> getAdressList(){
	return list;
   }
   
   /*
      Description: This fonction helps to split a string and store all the splited
                   elements of the string into an array of string. It takes the string
                   to parse in first argument, the delemiter as second and a boolean
                   showing that if we want to use consecutive delimiter as one or not
   */
   
   public static String[] parser (String string, String delimiter, boolean isMessages){
      // The delemiter use to parse the string
      String delims;
      
      // We use consecutive delimiter to be considered as one
      if(isMessages){
         delims = "["+delimiter+"]+";
      }
      // We use only one delimiter
      else{
         delims = "["+delimiter+"]";
      }
      // We the split the string and store all the components in an array of string
      String[] tokens = string.split(delims);
      
      return tokens;
   }
   
   /*
      Description: This fonction helps to read a file and store the content in a
                   string. It takes to file name as argument.
      Exception  : If it coud not open the file, an IO exception is thrown. 
   */
   public static String readFile(String fileName) throws IOException {
      BufferedReader buffreader = new BufferedReader(new FileReader(fileName));
      try {
         StringBuilder stringbuilder = new StringBuilder();
         String line = buffreader.readLine();
         
         // Whenever it encounter a line it reads it till the end of file.
         while (line != null) {
            stringbuilder.append(line);
            stringbuilder.append("\n");
            line = buffreader.readLine();
         }
         return stringbuilder.toString();
      } finally {
         buffreader.close();
      }
   }

}
