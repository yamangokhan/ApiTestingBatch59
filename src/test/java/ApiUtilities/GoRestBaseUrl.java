package ApiUtilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUo(){
        spec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();
    }
}
