package vkAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

import static io.restassured.RestAssured.given;

public class VkApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(VkApiUtils.class);

    private static final String URL_APL = "https://api.vk.com/method/";
    private final String API_VERSION;
    private final String ACCESS_TOKEN;
    private Response response;

    public VkApiUtils(String accessToken, String apiVersion) {
        ACCESS_TOKEN = accessToken;
        API_VERSION = apiVersion;
        RestAssured.urlEncodingEnabled = false;
    }

    public VkApiUtils sendMessage(String methodName, String parameters) {
        return sendGet("wall." + methodName, parameters);
    }

    public VkApiUtils sendPhotos(String methodName, String parameters) {
        return sendGet("photos." + methodName, parameters);
    }

    private VkApiUtils sendGet(String methodName, String parameters) {
        logger.info("Trying to send GET request to API");
        String url = URL_APL + methodName + "?" + parameters + "&access_token=" + ACCESS_TOKEN + "&v=" + API_VERSION;
        response = given()
                .get(url);
        return this;
    }

    public VkApiUtils sendPost(String url, String filePath) {
        logger.info("Trying to send GET request to API");
        File file = null;
        try {
            file = new File(filePath);
        } catch(Exception e) {
            logger.warn("Can't find file on path " + filePath);
        }
        response = given()
                .contentType("multipart/form-data")
                .multiPart("photo", file)
                .post(url);
        return this;
    }

    public String getResponse() {
        logger.info("Trying to get response text");
        return response.asString();
    }

    public int getStatusCode() {
        logger.info("Trying to get statusCode");
        return response.statusCode();
    }
}

