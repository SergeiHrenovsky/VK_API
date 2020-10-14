package utils.workWithFiles;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;

public class JsonHandler {
    private static final Logger logger = LoggerFactory.getLogger(JsonHandler.class);

    public static String getValue(String filePath, String key) {
        logger.info("Trying to get value from JSON file");
        File settingsFile = new File(filePath);
        ISettingsFile environment = null;
        try {
            environment = new JsonSettingsFile(settingsFile);
        } catch (IOException e) {
            logger.error("Can't to create an object of type JsonSettingsFile");
            return null;
        }
        return environment.getValue("/" + key).toString();
    }
}
