package utilies;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

   private static ObjectMapper mapper;

   static {
       mapper = new ObjectMapper();

   }

   //1. Method : Json datasını Java onjesine cevirir (De-Serialization)

    public static  <T> T  convertJsontoJavaObject(String json, Class<T> cls){ //==>> Generic Method (Her türlü datada calısır)
         T javaResult = null;
        try {
            javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;

    }




    //2. Method : Java objesini Json dataya cevirir (Serialization)
}

