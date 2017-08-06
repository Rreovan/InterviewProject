package Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author RichReo-Home-Laptop
 */
public class Utilities {
    /**
    *Global setting of what method is currently being tested
    */
    private static String methodType;
    
    /**
     * global setting of the url with the endpoint for testing.  Note the 
     * hostname is defaulted to :http://ec2-54-174-213-136.compute-1.amazonaws.com:3000
     */
    private static String url;
    
    /**
     * defaulted hostname
     */
    private static String hostName = "http://ec2-54-174-213-136.compute-1.amazonaws.com:3000";
    
    /**
     * json mainly used for push and post methods when sending the api request
     */
    private static String payload;
    
    /**
     * numeric id mainly used in get push and delete to identify a specific entry
     */
    private static String id;
    
    /**
     * response received after an api call is made
     */
    private static HttpResponse response;
    
    /**
     * status code pulled from the response for use in assertions
     */
    private static int status;
    
    /**
     * the payload pulled from the response for use in assertions
     */
    private static String content;
    
    /**
     * Takes any file and pulls the data to be sent to the calling function
     * @param fileLocation=location of the file within the the project
     * @return string value of all the data from the file
     */
    public static String FileReader( String fileLocation) {
        BufferedReader br = null;
        String line = "";
        String returnValue="";
        try {

            br = new BufferedReader(new FileReader(fileLocation));
            while ((line = br.readLine()) != null) {
                returnValue=returnValue+line;
            }
            br.close();
        } catch (FileNotFoundException fileReaderException) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, fileReaderException);
        } catch (IOException fileReaderException) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, fileReaderException);
        } 
        return returnValue;

    }
    
    /**
     * builds and sends an api request to expectd url.  Also parsed response for later use
     */
    public static void RequestSender( ){
        	HttpClient client= HttpClientBuilder.create().build();

    try {
            switch(methodType){
                case "POST": HttpPost httppost = new HttpPost(url);
                            httppost.setHeader("Content-Type", "application/json");
                            StringEntity postEntity = new StringEntity(payload);      
                            httppost.setEntity(postEntity);
                            response = client.execute(httppost);
                            break;
                            
                case "GET": if(id!=null){
                                url =url+"/"+id;
                            }
                            Logger.getLogger(Utilities.class.getName()).log(Level.INFO, "the passed url");
                            Logger.getLogger(Utilities.class.getName()).log(Level.INFO, url);
                            HttpGet httpGet = new HttpGet(url);
                            httpGet.setHeader("Content-Type", "application/json");
                            response = client.execute(httpGet);
                            break;
                            
                case "PUT": if(id!=null){
                                url =url+"/"+id;
                            }
                            HttpPut httpPut = new HttpPut(url);
                            httpPut.setHeader("Content-Type", "application/json");
                            StringEntity putEntity = new StringEntity(payload);
                            httpPut.setEntity(putEntity);
                            response = client.execute(httpPut);
                            break;
                            
                case "DELETE": if(id!=null){
                                url =url+"/"+id;
                            }
                Logger.getLogger(Utilities.class.getName()).log(Level.INFO,url);
                Logger.getLogger(Utilities.class.getName()).log(Level.INFO,id);
                            HttpDelete httpDelete = new HttpDelete(url);
                            httpDelete.setHeader("Content-Type", "application/json");
                            response = client.execute(httpDelete);
                            break;
            }
            
		status = response.getStatusLine().getStatusCode();			
		content = EntityUtils.toString(response.getEntity());
            } catch (IOException connectionException) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, connectionException);
        }
    }

    /**
     * set the method of the api request
     * @param recievedMethodType 
     */
    public static void SetMethodType(String recievedMethodType){
        methodType = recievedMethodType;
        Logger.getLogger(Utilities.class.getName()).log(Level.INFO, methodType);

    }
    
    /**
     * get the method type of the current request
     * @return the current method type
     */
    public static String GetMethodType(){
        return methodType;
    }
    
    /**
     * change the url endpoint of the request.  PLEASE NOTE the url hostname is hardcoded
     * @param endPoint 
     */
    public static void SetUrl(String endPoint){
        url = hostName +"/"+endPoint;
        Logger.getLogger(Utilities.class.getName()).log(Level.INFO,url);
 
    }
   
    /**
     * the raw string response from the request
     * @return 
     */
    public static String GetResponse(){
        return response.toString();
    }
    
    /**
     * the parsed string status code of the response
     * @return 
     */
    public static String GetResponseStatus(){
        return Integer.toString(status);
    }
    
    /**
     * the parsed string payload of the response
     * @return 
     */
    public static String GetResponseBody(){
        return content;
    }
    
    /**
     * set the String payload for the request
     * @param passedPayload 
     */
    public static void SetPayload(String passedPayload){
        payload = passedPayload;
    }

    public static void SetId(String passedId){
        id =passedId;
    }
    
    public static String GetId(){
        return id;
    }
}
