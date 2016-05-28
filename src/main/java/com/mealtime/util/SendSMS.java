package com.mealtime.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:sms.properties")
public class SendSMS {
	
		@Value("${sms.apikey}")
        private String workingKey;
		
		@Value("${sms.sender}")
        private String senderID;
		
			public String processSMS(String mobileNumber,String message) throws IOException, KeyManagementException, NoSuchAlgorithmException
			{  
				RestTemplate restTemplate = new RestTemplate();
				System.out.println("API Key::"+workingKey);
				System.out.println("Sender ID::"+senderID);
				/*message=URLEncoder.encode(message, "UTF-8");*/
				String url = "http://instantalerts.co/api/web/send?apikey="+workingKey+"&sender="+senderID+"&to="+mobileNumber+"&message="+message+"&format=json";
		       // URL url = new URL("http://instantalerts.co/api/web/send?apikey="+workingKey+"&sender="+senderID+"&to="+mobileNumber+"&message="+message);
				System.out.println("url look like " + url );
				String result = restTemplate.getForObject(url, String.class);
				
			   /* HttpURLConnection con = (HttpURLConnection) url.openConnection();
			    con.addRequestProperty("User-Agent", "Mozilla/4.76");
			    con.setRequestMethod("GET");
			    con.setDoOutput(true);
			    con.getOutputStream();
			    //con.getInputStream();
			    BufferedReader rd;
			    String line;
	            String result = "";
	            rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	           while ((line = rd.readLine()) != null)
	            {
	               result += line;
	            }
		        rd.close(); 
		       */ 
				System.out.println("Result is" + result);
				return result;				
			}
	
	     
	    public  void sendSMS(String mobileNumber,String message) throws IOException, KeyManagementException, NoSuchAlgorithmException
		{
	    	 processSMS(mobileNumber, message);  										
		}
	    
	  //To resolve ${} in @Value
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
			return new PropertySourcesPlaceholderConfigurer();
		}
	     
}

