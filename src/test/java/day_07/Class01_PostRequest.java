package day_07;

import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Class01_PostRequest extends HerOkuAppBaseUrl {
/*
Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
 */

    @Test
    public void postRequest01() {
        //1.set the url :
        spec.pathParam("1", "booking");

        //2.set the expected data
        HerOkuAppTestData herOkuApp = new HerOkuAppTestData();
        Map<String, String> bookingdatesMap = herOkuApp.bookingDateSetUp("2021-09-09", "2021-09-21");

        Map<String, Object> expectedDataMap = herOkuApp.expectedDataSetUp("John", "Doe", 11111, true, bookingdatesMap);

        //3. step send the post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{1}");

        response.prettyPrint();

        //4.Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("firstname"), ((Map) actualDataMap.get("booking")).get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), ((Map) actualDataMap.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));
        Assert.assertEquals(bookingdatesMap.get("checkin"), ((Map)((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(bookingdatesMap.get("checkout"), ((Map)((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

        Assert.assertEquals(200, response.getStatusCode());
    }
}