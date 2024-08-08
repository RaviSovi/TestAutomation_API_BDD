package co.demo.apiautomation.stepdefinitions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.JsonObject;

import co.demo.apiautomation.pojo.AddBook;
import co.demo.apiautomation.pojo.RootViewBook;
import co.demo.apiautomation.pojo.ViewBookDetails;
import co.demo.apiautomation.utils.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;

public class BookFeaturesStepDefinition {
	
	private TestContext context;
	static AddBook addBook;
	//static RootViewBook rootViewBook;
	static ViewBookDetails[] viewBookDetails;
	
	public BookFeaturesStepDefinition(TestContext context) {
		this.context = context;
	}
	
	@Given("User have a valid endpoints {string}")
	public void userHaveAValidEndpoint(String endpoint) {
		System.out.println(endpoint);
		TestContext.session.put("endpoint", PropertiesFile.getProperty(endpoint));
		ER.Info("User has access to endpoint : "+TestContext.session.get("endpoint"));
	}
	
	@SuppressWarnings("unchecked")
	@When("User add new book using test data row {string} from Excel")
	public void userAddNewBookUsingTestDataRowFromExcel(String dataKey) throws Exception {
		Map<String,String> excelDataMap = ExcelUtils.getData(dataKey);
		context.response = context.requestSetup("app1").body(excelDataMap.get("requestBody")).when().post(TestContext.session.get("endpoint").toString());

		addBook = ResponseHandler.deserializedResponse(context.response, AddBook.class);
		assertNotNull("Book not created", addBook);
		ER.Info("Newly created booking ID: "+addBook.getID());
		TestContext.session.put("bookID", addBook.getID());
		TestContext.session.put("excelDataMap", excelDataMap);
		
	}
	
	
	@When("User add a book using request body data {string} from JSON file {string}")
	public void userAddNewBookUsingDataFromJSONFile(String dataKey, String JSONFile) {
		context.response = context.requestSetup("app1").body(JsonReader.getRequestBody(JSONFile,dataKey)).when().post(TestContext.session.get("endpoint").toString());
		String responseBody = context.response.getBody().asString();
		System.out.println("resonse bbbody as String ::::"+responseBody);
		TestContext.session.put("responseBody", responseBody);
		
		//Validation using rest assured JsonPath
		JsonPath jp = new JsonPath(context.response.getBody().asString());
		System.out.println("resonse bbbody as JsonPath ::::"+jp);
		String id = jp.getString("ID");
		String msg = jp.getString("Msg");
		System.out.println("ID ::::::"+id+" msg :::: "+msg);
		
		//Validation using JSONOBJECT
		JSONObject jsonObject = new JSONObject(responseBody);
		System.out.println("Response Body as JSON OBJECT :::: "+jsonObject);
		List<String > addBookData = TestUtils.getValuesInObject(jsonObject, "ID");
		for(String listdata : addBookData)
		{
			System.out.println("ID in list data :: "+listdata);
		}
		
		//Validation using JAVAX JSONPATH
		System.out.println(TestUtils.getValuesForKey(responseBody, "ID"));
		System.out.println(TestUtils.getValuesForKey(responseBody, "Msg"));
	}
	
	@And("User validate {string} data {string} from JSON file {string}")
	public void userValidateNewBookUsingDataFromJSONFile(String responseBody, String dataKey, String JSONFile) {
		responseBody = (String) TestContext.session.get("responseBody");
		System.out.println(responseBody);
		
		try {
	          List<String> differences = JsonComparator.getJsonDifferences(JsonReader.getRequestBody(JSONFile,dataKey), responseBody);
	          if (differences.isEmpty()) {
	              System.out.println("The JSON objects are equal.");
	          } else {
	              System.out.println("Differences found:");
	              for (String difference : differences) {
	                  System.out.println(difference);
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}
	
	
	@Then("User should get a response code as {int}")
	public void userShouldGetAResponseCodeAs(Integer responseStatusCode) throws Exception
	{
		try {
			assertEquals(Long.valueOf(responseStatusCode), Long.valueOf(context.response.getStatusCode()));
			ER.Info("User get a response code : "+Long.valueOf(context.response.getStatusCode()));
			}
			catch(Exception e)
			{
				ER.Fail("User get a response code : "+Long.valueOf(context.response.getStatusCode()));
			}
	}
	
	
	@SuppressWarnings("unchecked")
	@And("User validates the add book api json reponse properties from Excel {string}")
	public void userValidatesTheAddBookAPIJASONResponseProperties(String dataKey) throws Exception
	{
		Map<String,String> excelDataMap = ExcelUtils.getData(dataKey);
		JSONObject requestData = new JSONObject(excelDataMap.get("responseBody"));
		assertEquals("Msg property verfied in response ",requestData.get("Msg") , addBook.getMsg());
		assertEquals("ID property verfied in response ",requestData.get("ID") , addBook.getID());
	}
	
	@SuppressWarnings("unchecked")
	@And("User validates the response with Json Schema from Excel")
	public void userValidatesTheResponseWithJSONSchemeFromExcel()
	{
		context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(((Map<String,String>) TestContext.session.get("excelDataMap")).get("responseSchema")));
		ER.Info("Successfully Validated Jason schema from Excel");
	}
	
	@SuppressWarnings("unchecked")
	@When("User view existing book details using test data row {string} from Excel")
	public void userViewExistingBookDetailsUsingTestDataRowFromExcel(String dataKey) throws Exception {
		Map<String,String> excelDataMap = ExcelUtils.getData(dataKey);
		context.response = context.requestSetup("app1").queryParam("ID", TestContext.session.get("bookID")).when().get(TestContext.session.get("endpoint").toString());

		viewBookDetails = ResponseHandler.deserializedResponse(context.response, ViewBookDetails[].class);
		//assertNotNull("Book not available", rootViewBook.getViewBookDetails());
		ER.Info("ISBN DISPLAYED AS :::: "+viewBookDetails[0].getIsbn());
		TestContext.session.put("excelDataMap", excelDataMap);
		String dataFromExcel = excelDataMap.get("responseBody");
		System.out.println(context.response.asString());
		//boolean flag = JsonComparator.areJsonObjectsEqual(dataFromExcel, context.response.asString());
		//System.out.println("***********"+flag);
		
		try {
          List<String> differences = JsonComparator.getJsonDifferences(dataFromExcel, context.response.asString());
          if (differences.isEmpty()) {
              System.out.println("The JSON objects are equal.");
          } else {
              System.out.println("Differences found:");
              for (String difference : differences) {
                  System.out.println(difference);
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
		
		
//		// Compare request response using map
//		
//		 //JSONObject request = new JSONObject(dataFromExcel);
//		 JSONObject response = new JSONObject(context.response.asString());
//		
//		//Map<String, Object> mapRequest = TestUtils.convertJsonToMap(request);
//		//Map<String, Object> mapResponse = TestUtils.convertJsonToMap(response);
//		
//		 // Convert JSON Object to Map and print
//		
//		//JSONObject jsonObject = new JSONObject(jsonObjectString);
//        JSONArray jsonArrayRequest = new JSONArray(dataFromExcel.toString());
//        JSONArray jsonArrayResponse = new JSONArray(context.response.asString());
//		
//		
////        Object resultObject = convertJson(jsonObject);
////        
////        if (resultObject instanceof Map) {
////        	TestUtils.printMap((Map<String, Object>) resultObject);
////        }
//
//        // Convert JSON Array to List and print
//        Object resultArray = TestUtils.convertJson(jsonArrayRequest);
//        Object responseArray = TestUtils.convertJson(jsonArrayResponse);
//        if (resultArray instanceof List) {
//        	//TestUtils.printList((List<Object>) resultArray);
//        	//TestUtils.printList((List<Object>) responseArray);
//        }
//       
//        //boolean isEqual = areMapsEqual(resultArray, responseArray);
//       // ComparisonResult result = TestUtils.compareLists((List<Object>)resultArray, (List<Object>)responseArray);
//        System.out.println("Are lists equal? " + result.areEqual());
//        if (!result.areEqual()) {
//            System.out.println("Differences: " + result.getDifferences());
//        }
//	}
//	
//	@SuppressWarnings("unchecked")
//	@And("User validates the view book api json reponse properties from Excel {string}")
//	public void userValidatesTheViewBookAPIJASONResponseProperties(String dataKey) throws Exception
//	{
////		Map<String,String> excelDataMap = ExcelUtils.getData(dataKey);
////		JSONObject requestData = new JSONObject(excelDataMap.get("responseBody"));
////		
////		JsonPath jp = new JsonPath(requestData.String);
////		jp.get("isbn").toString();
////		
////		if(jp.get("[0].isbn").toString().contains(viewBookDetails[0].getIsbn()))
////		{
////		//assertEquals("ISBN property not verfied in response ",jp.get("isbn").toString() , viewBookDetails[0].getIsbn());
////		ER.Info(viewBookDetails[0].getIsbn()+" verified successfully as :"+jp.get("[0].isbn").toString());
////		}
//		
//		
//		
//		
//		Map<String,String> excelDataMap = ExcelUtils.getData(dataKey);
//		String dataFromExcel = excelDataMap.get("responseBody");
		
		
		
	}
	
	@And("user validates the response with JSON schema {string}")
	public void userValidatesResponseWithJSONSchema(String schemaFileName) {
		context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+schemaFileName));
		ER.Info("Successfully Validated schema from "+schemaFileName);
	}
	

}
