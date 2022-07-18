package day_08;

import ApiUtilities.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class Class01_DeleteRequest extends JsonPlaceHolderBaseUrl {
    /*
    delete_request

Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void deleteRequest(){
        //1. set the URL
        spec.pathParams("1", "todos", "2","198");
        //2. Step : set the expected data
        Map<String, Object> expectedDataMap = new HashMap<>();
        //3. Send delete Request and get the response
       Response response = given().spec(spec).when().delete("/{1}/{2}");
        response.prettyPrint();
        //4.do assertion
        response.then().assertThat().statusCode(200);
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap, actualDataMap);




        ////----------2. yol-------------\\\\\\
        Assert.assertTrue(actualDataMap.size()==0);
        // veya ;

        Assert.assertTrue(actualDataMap.isEmpty()); // tavsiye edilen bir yöntem

        // Delete request yapmadan önce "POST REQUESTé yapıp o datayi silmeliyiz.
    }


}
