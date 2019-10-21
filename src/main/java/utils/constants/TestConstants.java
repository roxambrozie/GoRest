package utils.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.property.PropertiesUtil;

import java.util.Properties;

import static java.lang.System.getProperty;

public class TestConstants {

    private static Logger log = LoggerFactory.getLogger(TestConstants.class);

    //properties
    private static String propFilePath = PropertiesUtil.setConfigPath();
    public static final Properties properties = PropertiesUtil.loadProperties(propFilePath);

    //environment
    public static final String ENVIRONMENT = PropertiesUtil.getString(properties, "environment");
    public static final boolean CREATE_NEW_USER_FLAG = useExistingPayload();

    private static boolean useExistingPayload() {
        return System.getProperty("payload.flag") != null ? Boolean.parseBoolean(System.getProperty("payload.flag")) : false;
    }

    public static final String GOREST_URI = PropertiesUtil.getString(properties, "goRest.baseURI");

    //generic
    public static final String PROTOCOL = PropertiesUtil.getString(properties, "protocol");
    public static final String SECURE_PROTOCOL = PropertiesUtil.getString(properties, "secureProtocol");
    public static final String PORT = PropertiesUtil.getString(properties, "port");

    //authorization
    public static final String GO_REST_ACCESS_TOKEN = "swKNfWJMZ8C2rktBwrnpY6dBRAQEsaBdJao6";

    //paths
    public static final String PATH_TO_CREATE_USER_PAYLOAD = "D:\\GoRest\\src\\main\\resources\\tesdata\\profile\\createUserPayload.json";
    public static final String PATH_TO_EXISTING_USER = "D:\\GoRest\\src\\main\\resources\\tesdata\\profile\\existingusers\\johan_rempel.json";
    public static final String PATH_TO_EXISTING_POST = "D:\\GoRest\\src\\main\\resources\\tesdata\\profile\\existingposts\\postId1.json";
    public static final String PATH_TO_EXISTING_ALBUM = "D:\\GoRest\\src\\main\\resources\\tesdata\\profile\\existingposts\\albumId1.json";


}
