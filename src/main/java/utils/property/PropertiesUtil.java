package utils.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    // ---------------- Properties ----------------------------------

    public static Properties loadProperties(String inputFilePath) {
        Properties properties = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(inputFilePath);
            properties = new Properties();
            properties.load(fis);

        } catch (IOException e) {
            log.error("Error loading file" + inputFilePath);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return properties;
    }

    public static String setConfigPath() {
        String propFilePtah;
        String envName;

        try {
            envName = System.getProperty("test.env").toLowerCase();
        } catch (Exception e) {
            log.warn("No environment specified. Defaulting to QA");
            envName = "qa";
        }

        if ((envName == null) || envName.trim().equals("")) {
            log.warn("Invalid environment specified. Default to QA");
            envName = "qa";
        }

        log.info("Test environment set to " + envName);
        propFilePtah = "src" + File.separator + "main"
                + File.separator + "resources"
                + File.separator + envName + ".properties";
        return propFilePtah;
    }

    // ---------------- String ----------------------------------

    public static String getString(Properties properties, String key) {
        return properties.getProperty(key);
    }
}
