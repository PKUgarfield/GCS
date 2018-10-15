package com.garfield.util;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	public static void sendActiveEmail(String emailTo, String url) {

			  String to = emailTo;
		      // sender email
		      String from = "abc@abcmint.com";
		      // email host ip
		      String host = "192.168.1.149";
		 
		      Properties properties = System.getProperties();
		      properties.setProperty("mail.smtp.host", host);
		      Session session = Session.getDefaultInstance(properties);		      

		      try{

		         MimeMessage message = new MimeMessage(session);
		         //From: header field of the header.
		         message.setFrom(new InternetAddress(from));
		         //To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         /*content*/
		         message.setSubject("ABCMint Account Active");
		         
		         StringBuffer sbf = new StringBuffer();
		         sbf.append("<p>Thanks for register ABCMint account. In order to use your account nornal, please click the url below to active your account.</p>");
		         sbf.append("<p><a href=\""+url+"\">");
		         sbf.append(url);
		         sbf.append("</a></p>");
		         
		         message.setContent(sbf.toString(), "text/html");

		         /*send*/
		         Transport.send(message);

		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
	}
	
	public static void sendValidateCode(String emailTo, String code) {

		  String to = emailTo;
	      // sender email
	      String from = "abc@abcmint.com";
	      // email host ip
	      String host = "192.168.1.149";
	 
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      Session session = Session.getDefaultInstance(properties);		      

	      try{

	         MimeMessage message = new MimeMessage(session);
	         //From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         //To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         /*content*/
	         message.setSubject("ABCMint Account Active");
	         
	         StringBuffer sbf = new StringBuffer();
	         sbf.append("<p>your code number is: </p>");
	         sbf.append("<p>"+code+"</p>");
	         
	         message.setContent(sbf.toString(), "text/html");

	         /*send*/
	         Transport.send(message);

	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
}
}
