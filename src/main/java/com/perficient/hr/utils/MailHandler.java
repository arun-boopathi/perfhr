package com.perficient.hr.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

public class MailHandler {

	@Autowired
	PerfProperties perfProperties;

	public Session configureMailSession() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.ssl.trust", perfProperties.getHost());
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", perfProperties.getUsername());
		props.put("mail.smtp.password", perfProperties.getPassword());
		props.put("mail.smtp.host", perfProperties.getHost());
		props.put("mail.smtp.port", perfProperties.getPort());
		props.setProperty("mail.debug", "true");
		return Session.getDefaultInstance(props, null);
	}

	public void sentMail(String to, String from, String body, String subject) {
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
			throw new RuntimeException(e);
		}
	}
}
