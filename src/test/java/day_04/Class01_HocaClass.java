package day_04;
import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class Class01_HocaClass {


    public class Get06 extends HerOkuAppBaseUrl {

        /*
            Given
                https://restful-booker.herokuapp.com/booking/101
            When
                User send a GET request to the URL
            Then
                HTTP Status Code should be 200
            And
                Response content type is “application/json”
            And
                Response body should be like;
              {
                "firstname": "GGS",
                "lastname": "FINCH",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                }

            }
         */
        @Test
        public void get01(){

            //1. Step: Set the Url

            spec.pathParams("first","booking", "second", 101);
            //2. Set the expected data

            //3. Step: Send the request and get the response
            Response response = given().spec(spec).when().get("/{first}/{second}");
            //  response.prettyPrint();

            //4. Step: Do Assertion
            //1. Yol
            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("firstname",equalTo("GGS"),
                            "lastname",equalTo("FINCH"),
                            "totalprice", equalTo(111),
                            "depositpaid", equalTo(true),
                            "bookingdates.checkin",equalTo("2018-01-01"),
                            "bookingdates.checkout",equalTo("2019-01-01"));

            //2. Yol: JsonPath Class kullanılır

            JsonPath json = response.jsonPath();
            assertEquals("GGS", json.getString("firstname"));
            assertEquals("FINCH", json.getString("lastname"));
            assertEquals(111, json.getInt("totalprice"));
            assertEquals(true, json.getBoolean("depositpaid"));
            assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
            assertEquals("2019-01-01",json.getString("bookingdates.checkout"));

            //3. Yol: Soft Assertion
            //Soft Assertion için 3 adım izlenir;

            //1) SoftAssert Objesi oluşturulur.
            SoftAssert softAssert = new SoftAssert();

            //2) Obje aracılığı ile assert yapılır.

            softAssert.assertEquals(json.getString("firstname"), "GGS","firstname uyuşmadı");
            softAssert.assertEquals(json.getString("lastname"),"FINCHW","lastname uyuşmadı");


            //3) assertAll() methodu kullanılır. Aksi taktirde kod her zaman pass olur.
            softAssert.assertAll();

        }
    }

}
