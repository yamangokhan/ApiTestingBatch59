package day_03;

import ApiUtilities.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class Class02 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"
     */

    @Test
    public void test01() {
        spec.pathParam("first", "booking").queryParams("firstname", "Aaron", "lastname", "Chen");

        Response response = given().spec(spec).when().get("/{first}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("1649"));





    }

}
