package day_05;

import ApiUtilities.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Class01_getReqest extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

// De- Serialization: Json formatından Java objesine çevirmektir
//Serialization: Java objesini JSON formatına çevirmek
//De-Serialization ve Serialization 2 türlü yapılır.
//Gson:(Google tarafından üretilmiştir, pek kullanmayacağız)
//Object Mapper: Daha popülerdir bunu kullanacağız
    @Test
    public void getRequest(){
        //1.step
        spec.pathParams("1", "todos", "2", "2");


        //2.step
    Map<String, Object> expectedData = new HashMap<>();
    expectedData.put("userId", 1);
    expectedData.put("title", "quis ut nam facilis et officia qui");
    expectedData.put("completed", false);
    expectedData.put("StatusCode", 200);
    expectedData.put("Via", "1.1 vegur");
    expectedData.put("Server", "cloudflare");

        //3.step
        Response response = given().spec(spec).when().get("/{1}/{2}");

        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);

        System.out.println(actualData);

        //4.step : Do Assertion

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        Assert.assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }

    @Test
    public void getRequestDiffentWayToSolve(){
        //1.step
        spec.pathParams("1", "todos", "2", "2");

        //2.step (Once classtan obje olusturduk ve parametreler üzerinden işlem gerceklestirdik)
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();  // olusturdugumuz obje
        Map<String, Object> expectedDataMap=
        jsonPlaceHolderTestData.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui", false );

        expectedDataMap.put("StatusCode", 200);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        System.out.println("expectedDataMap : " + expectedDataMap);


        //3.step
        Response response = given().spec(spec).when().get("/{1}/{2}");

        Map<String, Object> actualData = response.as(HashMap.class);

        System.out.println(actualData);

        //4.step : Do Assertion
        Assert.assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));
    }



}
