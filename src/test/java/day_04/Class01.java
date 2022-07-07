package day_04;

import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Class01 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            {
    "firstname": "Mateusz",
    "lastname": "Bratlie",
    "totalprice": 387,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-07",
        "checkout": "2022-07-12"
    },
    "additionalneeds": "Breakfast"
}
        }
     */

    @Test
    public void test01() {
        spec.pathParams("1", "booking", "2", "554");

        Response response = given().when().spec(spec).get("/{1}/{2}");

        response.prettyPrint();

        response.then().assertThat().contentType(ContentType.JSON).statusCode(200)
                .body("firstname", equalTo("Mateusz"), "lastname", equalTo("Bratlie"),
                        "totalprice", equalTo(387), "depositpaid", equalTo(true), "bookingdates.checkin", equalTo("2022-07-07"),
                        "bookingdates.checkout", equalTo("2022-07-12"));


        //2.Yol JsonPath yontemini kullanarak;

        String expectedName = response.jsonPath().getString("firstname");
        String actualName = "Mateusz";

        //
        String expectedLastName = response.jsonPath().getString("lastname");
        String actualLastName = "Bratlie";
        //
        int expectedTotalPrice = response.jsonPath().getInt("totalprice");
        int actualTotalPrice = 387;
        //
        boolean expecteddepositpaid = response.jsonPath().get("depositpaid");
        boolean actualdepositpaid = true;
        //
        String expectedCheckin = response.jsonPath().getString("bookingdates.checkin");
        String actualChekin="2022-07-07";
        //
        String expectedCheckout = response.jsonPath().getString("bookingdates.checkout");
        String actualChekout="2022-07-12";

        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedLastName, actualLastName);
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
        Assert.assertEquals(expecteddepositpaid, actualdepositpaid);
        Assert.assertEquals(expectedCheckin, actualChekin);
        Assert.assertEquals(expectedCheckout, actualChekout);

        //3.YOL Soft Assertion
        // Soft Asseriton için 3 adım izlenir

             // 1-->> SoftAssert objesi olusturulur.
        SoftAssert softAssert = new SoftAssert();
             //  2-->> Olusuturulan obje aracılıgı ıle assert yapılır
        softAssert.assertEquals(actualName, expectedName);
        softAssert.assertEquals(actualLastName,expectedLastName);
        softAssert.assertEquals(actualdepositpaid,expecteddepositpaid);
        softAssert.assertEquals(actualChekin,expectedCheckin);
        softAssert.assertEquals(actualChekout,expectedCheckout);

             //  3-->> assertAll kullanılır.Aksi takdirde kod her zaman PASS olur
        softAssert.assertAll();
    }
}
