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
public interface ISmtpClient {
   
   public void sendMessage (Mail message, PrintWriter out) throws IOException;
   
}
