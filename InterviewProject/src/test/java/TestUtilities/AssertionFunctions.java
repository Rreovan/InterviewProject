/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUtilities;

import Utilities.Utilities;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.*;


/**
 *
 * @author RichReo-Home-Laptop
 */
public class AssertionFunctions {
    
    public static JsonParser jsonParser;
    
    
    public static void PostAssertions(JsonElement parsedJsonObject){
        jsonParser = new JsonParser();
        JsonObject parsedResponseObject = (JsonObject) jsonParser.parse(Utilities.GetResponseBody());
        Set<Map.Entry<String, JsonElement>> preRequestMap= ((JsonObject) parsedJsonObject).entrySet();
        for (Map.Entry<String, JsonElement> jsonElements: preRequestMap) {
            String key = jsonElements.getKey();
            Logger.getLogger(Utilities.class.getName()).log(Level.INFO, key);
            assertTrue((jsonElements.getValue().toString()).equals(parsedResponseObject.get(key).toString()));
        }
        Logger.getLogger(Utilities.class.getName()).log(Level.INFO, parsedResponseObject.get("id").toString());

        assertTrue((parsedResponseObject.get("id").toString()).matches("^\\d+$"));
        Utilities.SetId(parsedResponseObject.get("id").toString());
          
    }
    
    public static void GetAssertions(JsonElement parsedJsonElement){
        jsonParser = new JsonParser();
        JsonObject parsedResponseObject = (JsonObject) jsonParser.parse(Utilities.GetResponseBody());
        Set<Map.Entry<String, JsonElement>> preRequestMap= ((JsonObject) parsedJsonElement).entrySet();
        for(Map.Entry<String, JsonElement> jsonElements: preRequestMap){
            String key = jsonElements.getKey();
            Logger.getLogger(Utilities.class.getName()).log(Level.INFO, key);
            assertTrue((jsonElements.getValue().toString()).equals(parsedResponseObject.get(key).toString()));
        }
    }
}
