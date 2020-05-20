package resourses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
    public static RequestSpecification req;
	ResponseSpecification res;
	
	 // This is request specifications
	public RequestSpecification requestSpecification() throws IOException 
	 {
		PrintStream log=new PrintStream(new FileOutputStream("loggingtxt"));	
		if (req==null)
		{
		 
		 req=new RequestSpecBuilder()
                 .setBaseUri(readerProperties("baseURI"))
                 .addQueryParam("key","qaclick123")
                 .addHeader("content-type","application/json")
                 .addFilter(RequestLoggingFilter.logRequestTo(log))
                 .addFilter(ResponseLoggingFilter.logResponseTo(log))
                 .setContentType(ContentType.JSON)
                 .build();
		  return req;
		}
		return req;
	 }
	 
	// this method is Response specification
	 public ResponseSpecification responseSpecification()
	 {
		res=new ResponseSpecBuilder()
	              .expectContentType(ContentType.JSON)
                 .build();
		return res;
	 }

	// this method is used to read the global.properties file
	 public String readerProperties(String key) throws IOException {
		 String currentDir = System.getProperty("user.dir");
		 System.out.println(currentDir);
		 Properties prop=new Properties();
		 FileInputStream fileInput=new FileInputStream(currentDir+"\\src\\test\\java\\resourses\\global.properties");
		 System.out.println(fileInput);
		 prop.load(fileInput);
		 return prop.getProperty(key);
	     
	 }
	 //this method with converts the reponse to JsonPath and fetch the key
	 public String getResponseKeyValue(Response response,String key) {
		 String resp=response.asString();
			JsonPath js=new JsonPath(resp);	
			 String pathValue=js.get(key);
			 return pathValue;
	 }
	 
	 //this method will return the APIResources
	 public String getAPIResources(String httpMethod)
	 {
		 APIResources apiResources= APIResources.valueOf(httpMethod);
			System.out.println(apiResources.getResources());
			return apiResources.getResources();
	 }
}
