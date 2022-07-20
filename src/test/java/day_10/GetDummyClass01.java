package day_10;

import ApiUtilities.DummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetDummyClass01 extends DummyRestApi {
/*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void get01() {
        //1. Step Set the URL
        spec.pathParam("1", "employees");

        //2. Step Set the expected data

        //3. Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{1}");
        response.prettyPrint();

        //4.Step : Do Assertion
        //Assert that Status code is 200,
        //Assert that there are 24 employees,
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", hasSize(24), "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        //Assert that the greatest age is 66;
        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.id>0}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);
        Assert.assertEquals(66, (int) ageList.get(ageList.size() - 1));

        //Assert that the name of the lowest age is "Tatyana Fitzpatrick";
        String minAgeEmployeeName = json.getString("data.findAll{it.employee_age==19}.employee_name");
        System.out.println("min. age employee name : " + minAgeEmployeeName);

        //Asert that total salary of all employees is 6,644,770
        List<Integer> salaryList = json.getList("data.findAll{it.id>0}.employee_salary");

        //=========1. WAY================\\
        int sum = 0;
        for (Integer w : salaryList) {
            sum = sum + w;
        }
        System.out.println(sum);
        Assert.assertEquals(6644770, sum);

        //=========2. WAY (LAMBDA)================\\
        int sum2=salaryList.stream().reduce(0,(x,y)-> x+y);
        Assert.assertEquals(6644770, sum2);

        //=========3. WAY (METHOD REFERENCE)================\\
        int sum3 = salaryList.stream().reduce(0, Math::addExact);
        Assert.assertEquals(6644770, sum3);

    }

}
