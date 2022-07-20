package day_10;

import ApiUtilities.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utilies.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class GetObjectMapper extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void getObjectMApper(){
        //1. set the URL
        spec.pathParams("1", "todos", "2", "198");

        //2. Set the expected Data
        String expectedData = "{\n" +
                "   \"userId\": 10,\n" +
                "   \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                " }";

        Map<String,Object> expectedDataMap = JsonUtil.convertJsontoJavaObject(expectedData, HashMap.class);
        System.out.println(expectedDataMap);

        //3. Send Request Get Response
        Response response = given().spec(spec).when().get("/{1}/{2}");
        Map<String, Object> actualDataMap = JsonUtil.convertJsontoJavaObject(response.asString(), HashMap.class);
        //4.Do assertion

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"), actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }
//===================================================2. YOL =============================
    @Test
    public void getObjectMapper02(){
        //1. set the URL
        spec.pathParams("1", "todos", "2", "198");

        //2. Set the expected Data
        String expectedData = "{\n" +
                "   \"userId\": 10,\n" +
                "   \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                " }";
        JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsontoJavaObject(expectedData, JsonPlaceHolderPojo.class);

        //3. Send the request and get the response
        Response response = given().spec(spec).when().get("/{1}/{2}");
        //4.Do assertion
        JsonPlaceHolderPojo actualDataPojo = JsonUtil.convertJsontoJavaObject(response.asString(),JsonPlaceHolderPojo.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedDataPojo.getUserID(), actualDataPojo.getUserID());
        Assert.assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        Assert.assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());
    }
}


