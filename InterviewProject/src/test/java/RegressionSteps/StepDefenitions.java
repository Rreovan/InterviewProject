package RegressionSteps;

import static TestUtilities.AssertionFunctions.PostAssertions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import Utilities.Utilities;
import TestUtilities.TestUtilities;
import gherkin.deps.com.google.gson.JsonArray;

/**
 *
 * @author RichReo-Home-Laptop
 */
public class StepDefenitions {
    
    /**
     * statement to set the enpoint an method type for the request to be sent
    *String methodType= POST,GET,or DELETE
    *String endPoint= users,comments,poss
     * @param methodType
     * @param endPoint
     * @throws java.lang.Throwable
    */
    @Given("^the Method is \"([^\"]*)\" for the endpoint \"([^\"]*)\"$")
    public void the_Method_is_for_the_endpoint(String methodType, String endPoint) throws Throwable {
        Utilities.SetUrl(endPoint);
        Utilities.SetMethodType(methodType);
    }
   
    /**
     * pulls the testing file from the resources folder
    *TODO:add section to pull files from database such as S3
    *String filename= name of the testing file
    * @param filename 
     * @throws java.lang.Throwable 
    */
    @When("^the \"([^\"]*)\" scenario file is loaded$")
    public void the_scenario_file_is_loaded(String filename) throws Throwable {
        TestUtilities.JsonRequestBuilder(filename);
    }
    
    /**
     * send pre loaded request
     * @throws Throwable 
     */
    @When("^request is sent$")
    public void request_is_sent() throws Throwable {
        JsonArray preRequestArray = TestUtilities.GetParsedArray();
        Utilities.SetPayload(preRequestArray.get(0).toString());
        Utilities.RequestSender();
        PostAssertions(preRequestArray.get(0));
    }

    /**
     * set new pre json for put
     * @param fileName
     * @throws Throwable 
     */
    @When("^the PUT file is set to \"([^\"]*)\"$")
    public void the_PUT_file_is_set_to(String fileName) throws Throwable {
        TestUtilities.JsonRequestBuilder(fileName);
    }
}
