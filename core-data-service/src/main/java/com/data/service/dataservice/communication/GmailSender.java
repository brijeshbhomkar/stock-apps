package com.data.service.dataservice.communication;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.service.dataservice.json.StockWatch;

@Service
public class GmailSender {

	private final Logger logger = LoggerFactory.getLogger(GmailSender.class);

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public void init(final List<StockWatch> stock) {
		try {
			setMailServerProperties();
			createEmailMessage(stock);
			sendEmail();
		} catch (Exception e) {
			logger.error("Failed to send emmail ", e);
		}
	}

	private void sendEmail() throws MessagingException {
		String emailHost = "smtp.gmail.com";
		String fromUser = "brijesh.bhomkar@gmail.com";
		String fromUserEmailPassword = "@@@@@";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

	private void createEmailMessage(final List<StockWatch> stock) throws AddressException, MessagingException {
		String[] toEmails = { "brijesh.bhomkar@gmail.com"};
		String emailSubject = "Intraday Upstocks";
		String emailBody = "Sample email.. dont panic";

		mailSession = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("brijesh.bhomkar@gmail.com", "@@@@@");
			}
		});
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");
	}

	private void setMailServerProperties() {
		String emailPort = "587";// gmail's smtp port
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
	}
}
