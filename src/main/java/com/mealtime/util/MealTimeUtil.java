package com.mealtime.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.mealtime.bean.UserMaster;

@Service
public class MealTimeUtil {
	
	private static final Logger logger = Logger.getLogger(MealTimeUtil.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SendSMS sendSMS;
	
	public Integer generateOTP(){
		Random rnd = new Random();
		int otp = 100000 + rnd.nextInt(900000);
		return otp;
	}
	
	public void sendEmail(final String toAddress, final String fromAddress, final String subject, final String msgBody) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
            public void prepare(MimeMessage mimeMessage) throws Exception {  
               mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(toAddress));  
               mimeMessage.setFrom(new InternetAddress("info@mealtime.co.in"));  
               mimeMessage.setSubject(subject);  
               //mimeMessage.setText(msgBody);  
               mimeMessage.setContent(msgBody, "text/html");
            }  
		};  
		mailSender.send(messagePreparator);
	}
	
	public void sendEmail(final String toAddress, final String fromAddress, final String subject, final String msgBody, final String path) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
            public void prepare(MimeMessage mimeMessage) throws Exception {  
               mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(toAddress));  
               mimeMessage.setFrom(new InternetAddress("info@mealtime.co.in"));  
               mimeMessage.setSubject(subject); 
               //mimeMessage.setContent(msgBody, "text/html");
               //mimeMessage.setText(msgBody);  
              
               BodyPart messageBodyPart1 = new MimeBodyPart();  
               messageBodyPart1.setText(msgBody);  
               
               // Adding attachments
               MimeBodyPart messageBodyPart = new MimeBodyPart();
               DataSource source = new FileDataSource(path);
               messageBodyPart.setDataHandler(new DataHandler(source));
               messageBodyPart.setFileName(source.getName());
              // messageBodyPart.setText(msgBody);
             //  messageBodyPart.setContent(msgBody, "text/html");
            // Create a multipar message
               Multipart multipart = new MimeMultipart();
               multipart.addBodyPart(messageBodyPart);
               multipart.addBodyPart(messageBodyPart1);  
               mimeMessage.setContent(multipart);
               //mimeMessage.setContent(msgBody, "text/html");
            }  
		};  
		mailSender.send(messagePreparator);
	}
	
	public void sendSMS(String mobileNumber, String message){
		try {
			sendSMS.processSMS(mobileNumber, message);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public static void uploadProfilePic(MultipartFile file, UserMaster userMaster, String userId, WSResponseStatus wsResponseStatus){
		 logger.info("Uploading Profile Picture..");
       try {
           byte[] bytes = file.getBytes();
           logger.info("Content Type::"+file.getContentType());
           //String fileName = userMaster.getMobileNumber()+".jpg";
           String fileName = userId+".jpg";
           // Creating the directory to store file
           String rootPath = System.getProperty("catalina.home");
           logger.info("Catalina Home::"+rootPath);
           File dir = new File(rootPath+File.separator+"MealTime_User_Images");
           if (!dir.exists())
               dir.mkdirs();
           // Create the file on server
           File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
           BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
           stream.write(bytes);
           stream.close();
           logger.info("Server File Location=" + serverFile.getAbsolutePath());
           userMaster.setFilePath(serverFile.getAbsolutePath());
           logger.info("You successfully uploaded file=" + fileName);
       } catch (Exception e) {
           logger.error("You failed to upload " + userId + " => " + e.getMessage());
           MealTimeUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, e.getMessage());
       }
       logger.info("Uploaded successfully");
	}

	public static Date convertStrDate(String strDate){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aaa");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	 public static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		  
		  //create a new cell with the specified Text and Font
		  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		  //set the cell alignment
		  cell.setHorizontalAlignment(align);
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
		  
		 }
}
