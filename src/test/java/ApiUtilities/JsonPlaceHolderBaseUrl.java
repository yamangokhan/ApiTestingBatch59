package ApiUtilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderBaseUrl {


        protected RequestSpecification spec;

        @Before
        public void setUp() {
            spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();

        }


}



