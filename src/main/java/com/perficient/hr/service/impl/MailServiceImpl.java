package com.perficient.hr.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.hr.form.NotificationMail;
import com.perficient.hr.service.MailService;
import com.perficient.hr.utils.PerfProperties;

@Service("mailService")
public class MailServiceImpl implements MailService{

	@Autowired
	public PerfProperties perfProperties;
	
	private Session configureMailSession() {
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
	
	@Override
	public void sendNotifcationMail(NotificationMail notificationMail) throws Exception {
		try {
			 //register the text/calendar mime type
	         MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
	         mimetypes.addMimeTypes("text/calendar ics ICS");
	         
	         //register the handling of text/calendar mime type
	         MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
	         mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	         
	         Session session = configureMailSession();
	         
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(perfProperties.getUsername()));
	         message.setSubject(notificationMail.getSubject());
	         InternetAddress[] mailAddress_TO = new InternetAddress [notificationMail.getRecipientsList().size()];
	         for(int i=0;i<mailAddress_TO.length;i++){
	             mailAddress_TO[i] = new InternetAddress(notificationMail.getRecipientsList().get(i));
	         }
	         message.addRecipients(Message.RecipientType.TO, mailAddress_TO);
	         
	         // Create an alternative Multipart
	         Multipart multipart = new MimeMultipart("alternative");

	         //part 1, html text
	         MimeBodyPart descriptionPart = new MimeBodyPart();
	         //Note: even if the content is spcified as being text/html, outlook won't read correctly tables at all
	         // and only some properties from div:s. Thus, try to avoid too fancy content
	         String content = "<font size=\"2\">"+notificationMail.getDescription()+"</font>";
	         descriptionPart.setContent(content, "text/html; charset=utf-8");
	         multipart.addBodyPart(descriptionPart);
	         
	         // Add part two, the calendar
	         BodyPart calendarPart = buildCalendarPart(notificationMail);
	         multipart.addBodyPart(calendarPart);
	         
	         //Put the multipart in message 
	         message.setContent(multipart);
	      
	         // send the message
	         Transport transport = session.getTransport("smtp");
	         transport.connect();
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
	
	private static BodyPart buildCalendarPart(NotificationMail notificationMail) throws Exception {
       BodyPart calendarPart = new MimeBodyPart();
       StringBuffer sb = new StringBuffer();
       //check the icalendar spec in order to build a more complicated meeting request
       StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
               "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
               "VERSION:2.0\n" +
               "METHOD:"+notificationMail.getRequestType()+"\n" +
               "BEGIN:VEVENT\n" +
               "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:s.ellappan@perficient.com\n" +
               "ORGANIZER:MAILTO:no-reply@perficient.com\n" +
               "DTSTAMP:"+new Date()+"\n" +
               "DTSTART:"+iCalendarDateFormat.format(notificationMail.getDT_START())+"\n" +
               "DTEND:"+iCalendarDateFormat.format(notificationMail.getDT_END())+"\n" +
               "LOCATION:\n" +
               "TRANSP:OPAQUE\n" +
               "SEQUENCE:"+notificationMail.getSequence()+"\n" +
               "STATUS:"+notificationMail.getStatusType()+"\n" +
               "UID:123\n" +
               "CATEGORIES:Meeting\n" +
               "SUMMARY:"+notificationMail.getSummary()+"\n" +
               "PRIORITY:5\n" +
               "CLASS:PUBLIC\n" +
               "BEGIN:VALARM\n" +
               "TRIGGER:PT1440M\n" +
               "ACTION:DISPLAY\n" +
               "DESCRIPTION:Reminder\n" +
               "END:VALARM\n" +
               "END:VEVENT\n" +
               "END:VCALENDAR");

       calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
       calendarPart.setDataHandler(new DataHandler(
               new ByteArrayDataSource(buffer.toString(), "text/calendar;method="+notificationMail.getRequestType()+"")));// very important

       return calendarPart;
   }
}
