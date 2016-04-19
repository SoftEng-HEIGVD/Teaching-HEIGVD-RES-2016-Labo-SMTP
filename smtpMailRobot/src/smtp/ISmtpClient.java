/*
 -----------------------------------------------------------------------------------
 Laboratoire : SMTP
 Fichier     : ISmtpClient.java
 Auteur(s)   : Pascal SEKLEY & Ibrahim Ounon
 Date        : Debut: 06.04.16 et Fin: 20.04.16
 But         : Interface that gives a function to send a prank via an smtp server
 Remarque(s) :

 Compilateur : jdk 1.8.0_60
 -----------------------------------------------------------------------------------
 */
package smtp;

import java.io.IOException;
import java.io.PrintWriter;
import prank.Prank;

public interface ISmtpClient {
   
 /*
  ----------------------------------------------------------------------------------
  Description  : Function that helps to send a prank (mail) to a group of persons

  Paramètre(s) : prank: The prank to be sent
                 out  : The writer stream that helps to send informations to the smtp
                        server

  Exception(s) : I/O exception
  ----------------------------------------------------------------------------------
*/
   public void sendMessage (Prank prank, PrintWriter out) throws IOException;
   
}
