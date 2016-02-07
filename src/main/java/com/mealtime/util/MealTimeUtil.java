package com.mealtime.util;

import java.util.HashMap;
import java.util.Map;
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
	
	public static void populateWSResponseStatusSuccessResponse(
			WSResponseStatus wsResponseStatus) {
		wsResponseStatus.setStatusCode("200");
		wsResponseStatus.setStatusMessage("success");
	
	}
	
	public static void populateWSResponseStatusFailsureStatusResponse(
			WSResponseStatus wsResponseStatus, String errorMsg) {
		if(wsResponseStatus==null){
			wsResponseStatus = new WSResponseStatus();
			Map<String,Object> dataMap = new HashMap<String, Object>(0);
			wsResponseStatus.setDataMap(dataMap);
		}
		wsResponseStatus.setStatusCode("500");
		wsResponseStatus.setStatusMessage("fail");
		wsResponseStatus.setErrorMsg(errorMsg);
		
	}

}
