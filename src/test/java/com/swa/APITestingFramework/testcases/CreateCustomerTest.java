package com.swa.APITestingFramework.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swa.APITestingFramework.APIs.CreateCustomerAPI;
import com.swa.APITestingFramework.listeners.ExtentListeners;
import com.swa.APITestingFramework.setUp.BaseTest;
import com.swa.APITestingFramework.utilities.DataUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {

		/*
		 * RestAssured.baseURI = "https://api.stripe.com"; RestAssured.basePath="/v1";
		 */
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();

		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) {

		/*
		 * RestAssured.baseURI = "https://api.stripe.com"; RestAssured.basePath="/v1";
		 */

		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();

		System.out.println(response.statusCode());

		Assert.assertEquals(response.statusCode(), 200);

	}

}
