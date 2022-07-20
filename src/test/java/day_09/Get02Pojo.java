package day_09;


import ApiUtilities.GoRestBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.given;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Get02Pojo extends GoRestBaseUrl {

    /*
        Given
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        Andhttps://gorest.co.in/public/v1/users/2508
            Response body should be like
           {
                "meta": null,
                "data": {
                    "id": 100,
                    "name": "Kamla Desai",
                     "email": "kamla_desai@hoeger-prosacco.co",
                     "gender": "male",
                     "status": "inactive"
                }
            }
    */
    @Test
    public void getPojo01(){
        //1. Step: Set the Url
        spec.pathParams("1", "users", "2", 100);


        //2. Step: Set the Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(100,"Kamla Desai","kamla_desai@hoeger-prosacco.co","male","inactive");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //3. Step: Send the Get Request get the Response

        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        //4. Step: Do Assertion
        GoRestResponseBodyPojo actualData = response.as(GoRestResponseBodyPojo.class);
        Assert.assertEquals(200, response.getStatusCode());

        Assert.assertEquals(goRestResponseBodyPojo.getMeta(), actualData.getMeta());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getId(), actualData.getData().getId());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getName(), actualData.getData().getName());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getEmail(), actualData.getData().getEmail());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getGender(), actualData.getData().getGender());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getStatus(), actualData.getData().getStatus());

    }

}





