package day_09;

import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class Get01Pojo extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/18
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
    "firstname": "omto",
    "lastname": "nena",
    "totalprice": 112,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
*/

    @Test
    public void getpojo01(){
        spec.pathParams("1","booking", "2","18" );

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01" );
        BookingPojo bookingPojo = new BookingPojo("omto", "nena", 112, true, "Breakfast", bookingDatesPojo );

        //3. Step:
        Response response = given().spec(spec).when().get("/{1}/{2}");


        //4. Do Assertion
        BookingPojo actualPojo = response.as(BookingPojo.class);
        Assert.assertEquals(bookingPojo.getFirstname(), actualPojo.getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(), actualPojo.getLastname());
        Assert.assertEquals(bookingPojo.getDepositpaid(), actualPojo.getDepositpaid());
        Assert.assertEquals(bookingPojo.getTotalprice(), actualPojo.getTotalprice());
        Assert.assertEquals(bookingPojo.getAdditionalneeds(), actualPojo.getAdditionalneeds());

        Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBookingdates().getCheckin());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBookingdates().getCheckout());
    }
}
