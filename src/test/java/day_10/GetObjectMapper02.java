package day_10;

import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utilies.JsonUtil;
import static io.restassured.RestAssured.*;


public class GetObjectMapper02 extends HerOkuAppBaseUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                      "firstname": "Oliver",
                    "lastname": "Smith",
                     "totalprice": 100,
                     "depositpaid": true,
                    "bookingdates": {
                             "checkin": "2022-07-18",
                               "checkout": "2022-07-19"
    },
                     "additionalneeds": "Breakfast"
                }


     */

    @Test
    public void get01(){
        //1.Step
        spec.pathParams("1","booking", "2","22");

        //2. step
        String expectedData =" \"firstname\": \"Oliver\",\n" +
                "                    \"lastname\": \"Smith\",\n" +
                "                     \"totalprice\": 100,\n" +
                "                     \"depositpaid\": true,\n" +
                "                    \"bookingdates\": {\n" +
                "                             \"checkin\": \"2022-07-18\",\n" +
                "                               \"checkout\": \"2022-07-19\"\n" +
                "    },\n" +
                "                     \"additionalneeds\": \"Breakfast\"";

        BookingPojo expectedDataPojo = JsonUtil.convertJsontoJavaObject(expectedData, BookingPojo.class);
        //3.Step
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();
        //4.Do Assertion
        BookingPojo actualData = JsonUtil.convertJsontoJavaObject(response.asString(), BookingPojo.class);
        Assert.assertEquals(200, response.getStatusCode());

 //     Assert.assertEquals(expectedDataPojo.getFirstname(), actualData.getFirstname());
 //     Assert.assertEquals(expectedDataPojo.getLastname(), actualData.getLastname());
 //     Assert.assertEquals(expectedDataPojo.getTotalprice(), actualData.getTotalprice());
 //     Assert.assertEquals(expectedDataPojo.getAdditionalneeds(), actualData.getAdditionalneeds());
 //     Assert.assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());

        // Soft Assertion
        //1. Adım : SoftAssert objesi olustur.
        SoftAssert softAssert = new SoftAssert();

        //2. Adım Assertion yap
        softAssert.assertEquals( expectedDataPojo.getFirstname(), actualData.getFirstname(), "Firstname uyusmadı");
        softAssert.assertEquals(actualData.getLastname(), expectedDataPojo.getLastname(), "Lastname uyusmadı");
        softAssert.assertEquals(actualData.getTotalprice(), expectedDataPojo.getTotalprice(), "Totalprice uyusmadı");
        softAssert.assertEquals(actualData.getDepositpaid(), expectedDataPojo.getDepositpaid(), "DepositPaid uyusmadı");
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), expectedDataPojo.getBookingdates().getCheckin(), "Checkin uyusşmadı");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), expectedDataPojo.getBookingdates().getCheckout(), "Checkout uyusşmadı");

        //3. assertAll methodu calıstır.
        softAssert.assertAll();
    }
}

