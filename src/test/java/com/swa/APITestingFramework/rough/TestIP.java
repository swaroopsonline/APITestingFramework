package com.swa.APITestingFramework.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIP {

	public static void main(String[] args) throws UnknownHostException {

		String inetAddress = InetAddress.getLocalHost().getHostAddress();
		System.out.println("http://"+inetAddress+":8080/job/APITestingFramework/Extent_20Reports/");

	}

}
