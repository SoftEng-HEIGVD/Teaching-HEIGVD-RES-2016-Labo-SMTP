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
public class Mail {
   
   private String from;
   private String[] to = new String[0];
   private String[] cc = new String[0];
   
   private String subject;
   private String body;
   
   
 
   public void setFrom(String from){
      this.from = from;
   }
   
   
   public void setTo(String[] to){
      this.to = to;
   }
   
   public void setCc(String[] cc){
      this.cc = cc;
   }
   
   
   public void setSubject(String subject){
      this.subject = subject;
   }
   
   
   public void setBody(String body){
      this.body = body;
   }
   
   
   
   public String getFrom(){
      return from;
   }
   
   
   public String[] getTo(){
      return to;
   }
   
   public String[] setCc(){
      return cc;
   }
   
   
   public String getSubject(){
      return subject;
   }
   
   
   public String getBody(){
      return body;
   }

}
