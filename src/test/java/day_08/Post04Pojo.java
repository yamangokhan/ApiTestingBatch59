package day_08;

import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;

public class Post04Pojo extends HerOkuAppBaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */


    @Test
    public void postPojoTest01(){
        //1. set the URL
        spec.pathParam("1", "booking");
        //2.Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21" );
        BookingPojo booking = new BookingPojo("Ali","Can",999, true, "Breakfast with white tea, Dragon juice", bookingDates   );
        //3. Send POST request and Get the Response
        Response response = given().spec(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/{1}");
        response.prettyPrint();

        //4.Do Assertion
        BookingResponseBodyPojo actualPojo =  response.as(BookingResponseBodyPojo.class);
        System.out.println(actualPojo);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(booking.getFirstname(), actualPojo.getBooking().getFirstname());
        Assert.assertEquals(booking.getTotalprice(), actualPojo.getBooking().getTotalprice());
        Assert.assertEquals(booking.getDepositpaid(), actualPojo.getBooking().getDepositpaid());
        Assert.assertEquals(booking.getLastname(), actualPojo.getBooking().getLastname());
        Assert.assertEquals(booking.getAdditionalneeds(), actualPojo.getBooking().getAdditionalneeds());

        Assert.assertEquals(booking.getBookingdates().getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(booking.getBookingdates().getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());


    }

}
