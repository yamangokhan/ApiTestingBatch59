package day_04;

import ApiUtilities.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

public class Class02 extends JsonPlaceHolderBaseUrl {

    /*
    Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void test01() {
        spec.pathParam("1", "todos");

        Response response = given().spec(spec).get("/{1}");

        //response.prettyPrint();

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

        //2)Print all ids greater than 190 on the console
        //Assert that there are 10 ids greater than 190
        JsonPath json = response.jsonPath();
        List<Integer> ids = json.getList("findAll{it.id>190}.id"); // Groovy Languange = Java temelli bir progralama dili

        System.out.println(ids);

        int id = 0;
        for (Integer w : ids) {
            if (w.equals("id")) {
                id++;
            }
        }
        System.out.println("Farklı bir yol" + ids);

        assertEquals(10, ids.size());

     //  3)Print all userIds whose ids are less than 5 on the console

        List<Integer> ids2 = json.getList("findAll{it.id<5}.userId");
        System.out.println("5 den kucuk olanlar : " + ids2);

     //  Assert that the number of userIds whose ids are less than 5 is 4
     assertEquals(ids2.size(), 4);

     // 4)Print all titles whose ids are less than 5
        List<String> title= json.getList("findAll{it.id<5}.title");
        System.out.println("idsi 5 ten kucuk olanların title : " + title);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(title.contains("delectus aut autem"));
        // farklı bir yol :
        assertThat(title, hasItems("delectus aut autem"));
    }
}


