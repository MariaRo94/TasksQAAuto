package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesConfigReader {
    private String filePath = System.getProperty("user.dir") + "/src/main/resources/credentials.properties";

    public Map<String, String> getCredentials() throws IOException {
        Properties properties = new Properties();

        properties.load(new FileInputStream(filePath));

        Map<String, String> resultCreds = new HashMap<>();
        for (String propertyName: properties.stringPropertyNames()){
            resultCreds.put(propertyName, properties.getProperty(propertyName));
        }

        return resultCreds;
    }
}
