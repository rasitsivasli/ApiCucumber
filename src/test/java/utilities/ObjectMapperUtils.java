package utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

    public static <T> T jsonToJava(String json, Class<T> tClass) {

        //Bu method ile json datayı java objesine çeviriyoruz.
        //Object mapper kullanırken atılan exception'ı burada try-catch ile hallediyoruz.
        try {
            return  new ObjectMapper().readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
