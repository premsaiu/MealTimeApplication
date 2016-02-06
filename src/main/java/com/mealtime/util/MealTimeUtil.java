package com.mealtime.util;

import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MealTimeUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public Integer generateOTP(){
		Random rnd = new Random();
		int otp = 100000 + rnd.nextInt(900000);
		return otp;
	}
	
	public void sendEmail(final String toAddress, final String fromAddress, final String subject, final String msgBody) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
            public void prepare(MimeMessage mimeMessage) throws Exception {  
               mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(toAddress));  
               mimeMessage.setFrom(new InternetAddress(fromAddress));  
               mimeMessage.setSubject(subject);  
               //mimeMessage.setText(msgBody);  
               mimeMessage.setContent(msgBody, "text/html");
            }  
		};  
		mailSender.send(messagePreparator);
	}

}
