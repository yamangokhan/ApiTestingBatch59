package day_11;

import ApiUtilities.DummyRestApi;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyResponseBody;
import utilies.JsonUtil;

import static io.restassured.RestAssured.*;

public class GetClass01 extends DummyRestApi {
    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */


    /*
    Given users goes to https://dummy.restapiexample.com/api/v1/employee/1
    When user sends Get Request
    Then user verify Status code is 200
    And user verify "employee_name" is "Tiger Nixon"
    And user verify  employee_salary" is 320800
    And user verify  "employee_age" is 61
    And user verify  "status" is "success"
    And user verify  "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void getClass01() {
        //1.Step : Set The URL
        spec.pathParams("1", "employee", "2", "1");

        //2.Step : Set the expected Data
        DummyDataPojo dummyDataPojo = new DummyDataPojo(1,"Tiger Nixon", 320800, 61, ""  );
        DummyResponseBody dummyResponseBody = new DummyResponseBody("success",dummyDataPojo, "Successfully! Record has been fetched."  );
        //3. Set the Get Request adn get the response
        Response response = given().when().spec(spec).get("/{1}/{2}");
        response.prettyPrint();

        //DummyResponseBody actualDataPojo = response.as(DummyResponseBody.class);

        DummyResponseBody actualDataPojo = JsonUtil.convertJsontoJavaObject(response.asString(), DummyResponseBody.class); // bunu methodu kendimiz olusturmustuk

        //4.Do Assertion
        response.then().assertThat().statusCode(200);

        Assert.assertEquals(dummyDataPojo.getId(), actualDataPojo.getData().getId());
        Assert.assertEquals(dummyDataPojo.getEmployee_age(), actualDataPojo.getData().getEmployee_age());
        Assert.assertEquals(dummyDataPojo.getEmployee_name(), actualDataPojo.getData().getEmployee_name());
        Assert.assertEquals(dummyDataPojo.getEmployee_salary(), actualDataPojo.getData().getEmployee_salary());
        Assert.assertEquals(dummyDataPojo.getProfile_image(), actualDataPojo.getData().getProfile_image());
        Assert.assertEquals(dummyResponseBody.getStatus(), actualDataPojo.getStatus());
        Assert.assertEquals(dummyResponseBody.getMessage(), actualDataPojo.getMessage());

    }
}
