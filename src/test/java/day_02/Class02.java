package day_02;

import ApiUtilities.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Class02 extends JsonPlaceHolderBaseUrl {
 /*
        Given
          https://jsonplaceholder.typicode.com/todos/23
        When
          User send GET Request to the URL
        Then
          HTTP Status Code should be 200
        And
    Response format should be "application/json"
        And
    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
        And
    "completed" is false
        And
    "userId" is 2
   */

    @Test
    public void test01() {
        spec.pathParams("first", "todos", "second", "23");

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));


        //2.yol
        response.
                then().
                assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));

    }
}

        /*
       ---> Assertion yaparken Java calısmayı durdurugunda hata sonrası kodlar calısmaz.Böylece hata sonrası assertions lar hakkında
        bilgi sahibi olamayız.Fakat hata öncesi assertionslar geçmiştir.

       ---> Eger kodumuzu hata noktasında duracak sekilde yazarsak "Hard Assertion" yapmış oluyoruz

        ---> Eger kodumuzu hata noktasında durmayacak sekilde yazarsak "Soft Assertion" yapmış oluyoruz

        ---> Eger coklu body() mrethodu içerisinde assert yaparsak Hard Assertion yapmıs oluruz

        ---> Eger tek body() mrethodu içerisinde assert yaparsak "Soft Assertion" yapmıs oluruz
        */