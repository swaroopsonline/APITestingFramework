package com.swa.APITestingFramework.testcases;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swa.APITestingFramework.APIs.CreateCustomerAPI;
import com.swa.APITestingFramework.APIs.DeleteCustomerAPI;
import com.swa.APITestingFramework.listeners.ExtentListeners;
import com.swa.APITestingFramework.setUp.BaseTest;
import com.swa.APITestingFramework.utilities.DataUtil;
import com.swa.APITestingFramework.utilities.TestUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void deleteCustomer(Hashtable<String, String> data) {

		/*
		 * RestAssured.baseURI = "https://api.stripe.com"; RestAssured.basePath="/v1";
		 */
		Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();

		System.out.println(response.statusCode());
		
//		String actual_id = response.jsonPath().get("id").toString();
//		
//		Object objectId = response.jsonPath().get("id");
//		
//		System.out.println("Id without string conversion is : "+objectId);
//		
//		System.out.println("Getting id from Json path : "+actual_id);
//		
//		//Assert.assertEquals(actual_id, data.get("id"),"ID not matching");
//		Assert.assertEquals(actual_id, objectId,"ID not matching");
//		
//		Assert.assertEquals(response.statusCode(), 200);
		
//		JSONObject jsonObject = new JSONObject(response.asString());
//		
//		System.out.println(jsonObject.has("id"));
//		Assert.assertTrue(jsonObject.has("id"), "ID key is not present");
		
		System.out.println("Presence check for Object Key : "+TestUtil.jsonHasKey(response.asString(), "object"));
		System.out.println("Presence check for Deleted Key : "+TestUtil.jsonHasKey(response.asString(), "deleted"));
				
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"ID key is not present in the JSON response");
		
		String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
		System.out.println(actual_id);
		Assert.assertEquals(actual_id, data.get("id"), "ID is not matching");
		
		System.out.println("Object key value is : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Deleted key value is : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
		
		Assert.assertEquals(response.statusCode(), 200);

	}

	

}
