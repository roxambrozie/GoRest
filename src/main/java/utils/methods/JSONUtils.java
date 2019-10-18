package utils.methods;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class JSONUtils {

    private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    public static <T> T createPojoFromJSON(String jsonFile, Class<T> tClass) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(jsonFile), tClass);
        } catch (FileNotFoundException exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        } catch (IOException exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        } catch (NullPointerException exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }

        return null;
    }
}
