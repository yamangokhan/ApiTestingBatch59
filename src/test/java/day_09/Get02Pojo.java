package day_09;


import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;

import static io.restassured.RestAssured.given;

public class Get02Pojo extends {
    @Test
    public void getPojo01(){
        //1. Step: Set the Url
        spec.pathParams("first", "users", "second", 2508);


        //2. Step: Set the Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2508,"Akshita Nehru","nehru_akshita@jast.info","female","active");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //3. Step: Send the Get Request get the Response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion









    }



}

}



