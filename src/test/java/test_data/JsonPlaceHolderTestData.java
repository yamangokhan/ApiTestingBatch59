package test_data;
import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> expectedDataWithAllKeys(Integer userId, String title, Boolean completed){
       Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        return expectedData;

    }

    public Map<String,Object> expectedDataWithMissingKeys(Integer userId, String title, Boolean completed){
        Map<String, Object> expectedData = new HashMap<>();
        if(userId!=null){
            expectedData.put("userId",userId);
        }
        if(title!=null){
            expectedData.put("title",title);
        }
        if(completed!=null){
            expectedData.put("completed",completed);
        }

        return expectedData;

    }

    public String jsonPlaceHolderjsonToString(Integer userId,String title,Boolean completed){
        String expectedData="{\n" +
                "\"userId\": "+userId+",\n" +
                "\"title\": \""+title+"\",\n" +
                "\"completed\": "+completed+"\n" +
                " }";
        return expectedData;
    }

}
