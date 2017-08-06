package TestUtilities;

import Utilities.Utilities;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonParser;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author RichReo-Home-Laptop
 */
public class TestUtilities {
    /**
     * Array of json from parsed test file
     */
    private static JsonArray parsedArray;
    
    /**
     * parse the teting file and put in a json array for testing
     * @param fileName 
     */
    public static void JsonRequestBuilder(String fileName){
        String path = new File("").getAbsolutePath();
        String fileLocation = path +"\\Resources\\"+fileName;
        String jsonData = Utilities.FileReader(fileLocation);
        Logger.getLogger(Utilities.class.getName()).log(Level.INFO, jsonData);
        JsonParser jsonParser = new JsonParser();
        parsedArray =  (JsonArray)jsonParser.parse(jsonData);

    }
    
    /**
     * get the parsed json test dta from the testing file
     * @return 
     */
    public static JsonArray GetParsedArray(){
        return parsedArray;
    }
}
