package com.swa.APITestingFramework.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.swa.APITestingFramework.utilities.MonitoringMail;
import com.swa.APITestingFramework.utilities.TestConfig;

public class TestMail {
	
	static String messageBody;
	
	public static void main(String[] args) throws AddressException, MessagingException, UnknownHostException {
		
		String messageBody = InetAddress.getLocalHost().getHostAddress();
		System.out.println("http://"+messageBody+":8080/job/APITestingFramework/Extent_20Reports/");
		
		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	}

}
