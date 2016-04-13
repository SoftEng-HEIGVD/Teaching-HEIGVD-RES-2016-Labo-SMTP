/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smtp;

import java.io.IOException;
import java.io.PrintWriter;
import mail.Mail;

/**
 * 
 * @author Pascal SEKLEY
 */
public class SmtpClient implements ISmtpClient {
   
   
   
   
   
   
   @Override
   public void sendMessage (Mail message, PrintWriter out) throws IOException{
      
      // Start sending all the required commands to send the mail
      out.println("EHLO" + "Pascal");
      out.println("MAIL FROM: " + message.getFrom());
      out.println("RCPT TO: " + "pascal.sekley@heig-vd.ch");
      out.println("DATA");
      out.println(message.getBody());
      out.println(".");
      out.println("quit");
      
      System.out.println("Mail sent ...");
   }

}
