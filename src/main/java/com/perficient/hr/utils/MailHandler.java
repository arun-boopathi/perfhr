package com.perficient.hr.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.perficient.hr.model.EmailConstants;

public class MailHandler {

	@Autowired
	EmailConstants emailConstants;
	
/*	 private static Properties prop;
	 private static final String EMAIL_HOST=
	 private static final String EMAIL_PORT=prop.getProperty("");
	 private static final String EMAIL_USERNAME=prop.getProperty("");
	 private static final String EMAIL_PASSWORD=prop.getProperty("");
	       
	static{
		 InputStream is = null;
	        try {
	            prop =	 new Properties();
	            is = ClassLoader.class.getResourceAsStream("/sample.properties");
	            prop.load(is);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	*/
	
	 public Session configureMailSession(){
	    Properties props = new Properties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.ssl.trust", emailConstants.getHost());
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.user",emailConstants.getUsername());
	    props.put("mail.smtp.password", emailConstants.getPassword());
	    props.put("mail.smtp.host", emailConstants.getHost());
	    props.put("mail.smtp.port", emailConstants.getPort());
	    props.setProperty("mail.debug", "true");

		return Session.getDefaultInstance(props,null); 
	}
	
	public void sentMail(String to,String from,String body,String subject){	

	    Session session = configureMailSession();
	    try {
	       Message message = new MimeMessage(session);
	       message.setFrom(new InternetAddress(from));
	       message.setRecipients(Message.RecipientType.TO,
	       InternetAddress.parse(to));
	       message.setSubject(subject);
	       message.setText(body);
	       Transport.send(message);
	
	    } catch (MessagingException e) {
	    	  e.printStackTrace();
	          throw new RuntimeException(e);
	    }
	 }

}
