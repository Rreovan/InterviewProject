/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegressionSteps;

import static TestUtilities.AssertionFunctions.GetAssertions;
import static TestUtilities.AssertionFunctions.PostAssertions;
import TestUtilities.TestUtilities;
import Utilities.Utilities;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import gherkin.deps.com.google.gson.JsonArray;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author RichReo-Home-Laptop
 */
public class VerificationSteps {
    
    /**
     * verify that deleted item was actually deleted
     * @param arg1
     * @throws Throwable 
     */
    @Then("^a second DELETE request gets a \"([^\"]*)\" response code$")
    public void a_second_DELETE_request_gets_a_response_code(String arg1) throws Throwable {
         Utilities.RequestSender();
         assertTrue("404".equals(Utilities.GetResponseStatus()));
    }

    /**
     * sends the request and asserts the response
     * @param arg1
     * @throws Throwable 
     */
    @Then("^the \"([^\"]*)\" request is sent and the response passes assertions$")
    public void the_request_is_sent_and_the_response_passes_assertions(String assertionType) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JsonArray preRequestArray = TestUtilities.GetParsedArray();
        for(int i =0;i<preRequestArray.size();i++){
            Utilities.SetPayload(preRequestArray.get(i).toString());
            Utilities.RequestSender();
            Logger.getLogger(Utilities.class.getName()).log(Level.INFO, Utilities.GetResponseStatus());
            switch(assertionType){
                case("POST"):PostAssertions(preRequestArray.get(i));
                             assertTrue("201".equals(Utilities.GetResponseStatus()));
                             break;
                
                case("GET"):GetAssertions(preRequestArray.get(i));
                            assertTrue("200".equals(Utilities.GetResponseStatus()));
                            break;
                
                case("DELETE"):assertTrue("200".equals(Utilities.GetResponseStatus()));
                               break;
                
                case("PUT"):PostAssertions(preRequestArray.get(i));
                            assertTrue("200".equals(Utilities.GetResponseStatus()));
                            break;
            }
    }
    }
}
