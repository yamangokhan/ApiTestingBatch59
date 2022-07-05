package day_01;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get02 {
    @Test
    public void get01() {

        /*
        Testing kodunun yazalım
            i) Set the URL
            ii) Set the expected data (PUT-POST-PATCH)
            iii)Type code to send request
            iv) Do Assertion
         */
        /*
         Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
         */

        //1. adım
        String url = "https://restful-booker.herokuapp.com/booking/555";

        //2. adım
        Response response = given().when().get(url);

        response.prettyPrint(); // yazdırma işlemlerini yapar
        //3. adım
       response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

       // status code nasıl yazdırılır?

        System.out.println("Status Code: " + response.statusCode());

        // Content Type nasıl yazdırılır?
        System.out.println("Content Type: " + response.contentType());

        // Status Line nasıl yazdırılır?
        System.out.println("Status Line: " + response.statusLine());

        // Json Path nasıl yazdırılır?
        System.out.println("json path : " + response.jsonPath());

        // Header nasıl yazdırılır?
        System.out.println(response.header("User-Agent"));


        // Headers nasıl yazdırılır?
        System.out.println(response.headers());

        // Time nasıl yazdırılır
        System.out.println("Time : " + response.getTime());


    }
}
