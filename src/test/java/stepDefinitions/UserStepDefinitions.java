package stepDefinitions;

import apiMethods.ApiInvocation;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.ConfigFileReader;
import static org.junit.Assert.assertEquals;

import dataProviders.ExcelReader;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class UserStepDefinitions  {

	private static String responseCode;
	private static Response response;

	ApiInvocation apiInvocation = new ApiInvocation();
	ConfigFileReader configFileReader= new ConfigFileReader();

	ArrayList<HashMap<String, String>> valuesFromExcel = new ArrayList<>();

	private final Logger LOGGER = LoggerFactory.getLogger(UserStepDefinitions.class);



	@When("^User hits get endpoint \"([^\"]*)\"$")
	public void userHitsGetEndpoint(String url){

		response = apiInvocation.responseForGet(url, configFileReader.getUsername(), configFileReader.getPassword());
		responseCode = String.valueOf(response.getStatusCode());
		assertEquals("200",responseCode);

	}


	@Then("^Product name in response should be \"([^\"]*)\"$")
	public void productNameInResponseShouldBe(String productName)  {

		String stringResponse = response.asString();
		XmlPath xmlPath = new XmlPath(stringResponse);
		String productNameRes = xmlPath.get("Product.Name");

		assertEquals(productName,productNameRes);

	}

	@When("^excel reader completed the job$")
	public void excelReaderCompletedTheJob() throws IOException {
		valuesFromExcel = new ExcelReader().read("testxl.xlsx");

	}

	@Then("^User hits the get endpoint \"([^\"]*)\" for all the value in excel and validate the results$")
	public void userHitsTheGetEndpointForAllTheValueInExcelAndValidateTheResults(String endPointUrl) throws Throwable {

		for (int i = 0; i < valuesFromExcel.size(); i++){

			HashMap<String, String> valueOfRow = valuesFromExcel.get(i);

			response = apiInvocation.responseForGet(endPointUrl.replace("<skuId>", valueOfRow.get("SKU_ID")), configFileReader.getUsername(), configFileReader.getPassword());
			responseCode = String.valueOf(response.getStatusCode());

			assertEquals("200",responseCode);

			String stringResponse = response.asString();
			XmlPath xmlPath = new XmlPath(stringResponse);
			String productNameRes = xmlPath.get("Product.Name");
			//String skuParentIdRes = xmlPath.get("Product.ParentID");

			assertEquals(valueOfRow.get("Product Name"),productNameRes);
			//assertEquals(valueOfRow.get("PARENT ID"),skuParentIdRes);



		}

	}

	@And("^Product Color in response should be \"([^\"]*)\"$")
	public void productColorInResponseShouldBe(String arg0) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@And("^MFG name in response should be \"([^\"]*)\"$")
	public void mfgNameInResponseShouldBe(String arg0) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}



