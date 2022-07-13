package day_05;
import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Class02_getRequest extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
         }
     */

    @Test
    public void getRquest(){
        //1. step
        spec.pathParams("1", "booking", "2", "91");

        //response.prettyPrint();

        //2.step
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");


        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "James");
        expectedDataMap.put("lastname", "Brown");
        expectedDataMap.put("totalprice", 111);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingdatesMap);
        expectedDataMap.put("additionalneeds", "Breakfast");

        System.out.println("bakalım expected data nasıl olmus:"  + expectedDataMap);

        //3.step
        Response response = given().spec(spec).when().get("/{1}/{2}"); // ek bilgi: her class bir data formatıdır aynı zamanda
        Map<String, Object> actualDataMap = response.as(HashMap.class); // bu olay deserialization'dir.
        System.out.println(actualDataMap);

        //4. step
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        Assert.assertEquals(bookingdatesMap.get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(bookingdatesMap.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));
        Assert.assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));
    }

}
