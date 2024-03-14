package com.patel.LoanSystem.service;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class JavaMailer {
	public String sendEmail(String to, String firstName, String loanNumber) {
		final String username = "meetpatel12522@gmail.com";
		final String password = "Sweetu@12522";
		
		Properties props = new Properties();
		 props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session  = Session.getInstance(props,
        		new Authenticator() {
        			protected PasswordAuthentication getPasswordAuthentication() {
        				return new PasswordAuthentication(username, password);
        			}
        });
        
        try {
        	String subject = "Loan Application Confirmation";
        	String body = "Dear " + firstName + ",\n\n" +
                    "Thank you for applying for a loan with us. Your loan application with the loan number " +
                    loanNumber + " has been received.\n\n" +
                    "We will review your application and contact you shortly with further steps.\n\n" +
                    "Best regards,\nThe Loan Management System Team";
        	
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(username));
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        	message.setSubject(subject);
        	message.setText(body);
        	Transport.send(message);
        	
        	return "Mail has been send.";
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

}
