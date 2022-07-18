package day_08;

import ApiUtilities.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    @Test
    public void postPojo(){
        //1. Set the URL
        spec.pathParam("1", "todos");
        //2. Set the expected data
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3. Send a POST Request and the get the response
        Response response = given().spec(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/{1}");

        //4.Do Assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);

        System.out.println("bakalım ne cıkmıs :) : " + actualData);

        Assert.assertEquals(requestBody.getUserID(), actualData.getUserID());
        Assert.assertEquals(requestBody.getTitle(), actualData.getTitle());
        Assert.assertEquals(requestBody.getCompleted(), actualData.getCompleted());

        Assert.assertEquals(requestBody.toString(), actualData.toString());
    }

}
