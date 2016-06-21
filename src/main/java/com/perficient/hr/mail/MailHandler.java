package com.perficient.hr.mail;

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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.perficient.hr.form.NotificationMail;
import com.perficient.hr.utils.PerfProperties;

@Component
public class MailHandler {
	
	@Autowired
	private Environment env;
	
	@Autowired
	public PerfProperties perfProperties;
	
	private NotificationMail notificationMail;
	
	/*public MailHandler(NotificationMail notificationMail) {
		this.notificationMail = notificationMail;
	}*/

	public Session configureMailSession() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.user", PerfProperties.getInstance());
		props.put("mail.smtp.starttls.enable", perfProperties);
		/*props.put("mail.smtp.user", PerfProperties.getInstance().getUsername());
		props.put("mail.smtp.password", perfProperties.getPassword());
		props.put("mail.smtp.host", perfProperties.getHost());
		props.put("mail.smtp.port", perfProperties.getPort());*/
		props.setProperty("mail.debug", "true");
		return Session.getDefaultInstance(props, null);
	}

	public void sendCalNotificationMail() throws Exception {
		try {
			 //register the text/calendar mime type
	         MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
	         mimetypes.addMimeTypes("text/calendar ics ICS");
	         
	         //register the handling of text/calendar mime type
	         MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
	         mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	         
	         Session session = configureMailSession();
	         
	         MimeMessage message = new MimeMessage(session);
//	         message.setFrom(new InternetAddress(perfProperties.getUsername()));
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
//	         transport.close();
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
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:s.ellappan@perficient.com\n" +
                "ORGANIZER:MAILTO:no-reply@perficient.com\n" +
                "DTSTAMP:"+new Date()+"\n" +
                "DTSTART:"+iCalendarDateFormat.format(notificationMail.getDT_START())+"\n" +
                "DTEND:"+iCalendarDateFormat.format(notificationMail.getDT_END())+"\n" +
                "LOCATION:\n" +
                "TRANSP:OPAQUE\n" +
                "SEQUENCE:0\n" +
                "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n" +
                "000004377FE5C37984842BF9440448399EB02\n" +
                "CATEGORIES:Meeting\n" +
                "DESCRIPTION:"+notificationMail.getDescription()+"\n\n" +
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
                new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important
 
        return calendarPart;
    }
}
