import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AuthorizationPage;
import pageObjects.UserMyPage;
import pageObjects.UserPage;
import vkAPI.VkApiUtils;
import utils.workWithFiles.JsonHandler;
import utils.workWithText.RandomTextConstructor;
import vkAPI.MethodAPI;
import vkAPI.ParameterAPI;

import java.io.UnsupportedEncodingException;

public class VKTest {

    private final String API_VERSION = JsonHandler.getValue("configuration.json", "VK_API_VERSION");

    private final String EMAIL = JsonHandler.getValue("testData.json", "email");
    private final String PASSWORD = JsonHandler.getValue("testData.json", "password");
    private final String ACCESS_TOKEN = JsonHandler.getValue("testData.json", "accessToken");
    private final String USER_ID = JsonHandler.getValue("testData.json", "userId");
    private final String USER_LINK = JsonHandler.getValue("testData.json", "userLink");
    private final String TEST_IMAGE = JsonHandler.getValue("testData.json", "testImagePath");

    private final String TEXT_FOR_PUBLICATION = RandomTextConstructor.getRandomText();
    private String postId;

    private VkApiUtils vkAPI;
    private AuthorizationPage vkAuthorizationPage;
    private UserMyPage userMyPage;

    public VKTest() {
    }

    public void openVKMainPage() {
        vkAuthorizationPage = new AuthorizationPage();
        vkAuthorizationPage.openPage();
    }

    public void doAuthorization() {
        vkAuthorizationPage.doAuthorization(EMAIL, PASSWORD);
    }

    public void goToMyPage() {
        UserPage userPage = new UserPage();
        userPage.goToMyPage();
    }

    public void createPostOnWall() {
        String methodAPI = MethodAPI.WALL_POST.getMethod();
        String parameterAPI = ParameterAPI.MESSAGE.getParameter();

        String parameterWithText = parameterAPI + TEXT_FOR_PUBLICATION;

        String apiResponse = vkAPI.sendGet(methodAPI, parameterWithText).getResponse();
        String postIdJsonPath = "$.response.post_id";
        postId = String.valueOf((int) JsonPath.read(apiResponse, postIdJsonPath));
    }

    public void checkPostOnWallAppear() {
        String messageUser = "The current author of the first post does not match what was expected.";
        String actualAuthor = userMyPage.getWall().getPost(postId).getAuthor();
        Assert.assertEquals(actualAuthor, USER_LINK, messageUser);

        String messageText = "The current text of the first post does not match what was expected.";
        String actualText = userMyPage.getWall().getPost(postId).getText();
        Assert.assertEquals(actualText, TEXT_FOR_PUBLICATION, messageText);
    }

    public void editPostOnWall() {
        // edit text
        String editTextMethodAPI = MethodAPI.WALL_POST_EDIT.getMethod();

        String ownerId = ParameterAPI.OWNER_ID.getParameter() + USER_ID;
        String postIdParam = ParameterAPI.POST_ID.getParameter() + postId;
        String newText = RandomTextConstructor.getRandomText();
        String message = ParameterAPI.MESSAGE.getParameter() + newText;

//        String parameters = ownerId + "&" + postIdParam + "&" + message;
//
//        vkAPI.sendGet(editTextMethodAPI, parameters);

        // upload image
        String uploadImageMethodAPI = MethodAPI.PHOTOS_UPLOAD_WALL.getMethod();

        String responseAPI = vkAPI.sendGet(uploadImageMethodAPI, null).getResponse();

        System.out.println("++++++++++++++++");
        System.out.println("++++++++++++++++");
        System.out.println(responseAPI);
        System.out.println("++++++++++++++++");
        System.out.println("++++++++++++++++");

        String uploadUrlJsonPath = "$.response.upload_url";
        String uploadUrl = JsonPath.read(responseAPI, uploadUrlJsonPath);


        // Отправляю POST
        vkAPI.sendPost(uploadUrl, TEST_IMAGE);

        // Получаю от POST ответ в виде JSON с полями server, photo, hash
        String response = vkAPI.getResponse();

        System.out.println("++++++++++++++++");
        System.out.println("++++++++++++++++");
        System.out.println(response);
        System.out.println("++++++++++++++++");
        System.out.println("++++++++++++++++");

        // Достаю из JSON значния полей server, photo, hash
        // пути к полям
        String serverJsonPath = "$.server";
        String photoJsonPath = "$.photo";
        String hashJsonPath = "$.hash";

        // Получение полей и формирование параметра в виде server=12345
        String serverParam = ParameterAPI.SERVER.getParameter() + JsonPath.read(response, serverJsonPath);
        String photoParamEncode = null;
        String hashParam = ParameterAPI.HASH.getParameter() + JsonPath.read(response, hashJsonPath);
        try {
            photoParamEncode =  java.net.URLEncoder.encode(JsonPath.read(response, photoJsonPath), "UTF-8"); // "UTF-8"
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String photoParam = ParameterAPI.PHOTO.getParameter() + photoParamEncode;
       // String photosList = "photos_list=" + "";


       // String photoParam = ParameterAPI.PHOTO.getParameter() + JsonPath.read(response, photoJsonPath);

        System.out.println("++++++++++++++++");
        System.out.println("++++++++++++++++");
        System.out.println(serverParam);
        System.out.println(photoParam);
        System.out.println(hashParam);
        System.out.println("++++++++++++++++");
        System.out.println("++++++++++++++++");

//        StringBuffer sb = new StringBuffer(photoJSON);
//        sb.delete(0, 1);
//        sb.delete(sb.length() - 1, sb.length());
//        String photo = JsonPath.read(sb.toString(), photoJsonPath);
//
//        String photoParam = ParameterAPI.PHOTO.getParameter() + sb;

        // Поместил все параметры в одну строку
        String saveParameters = serverParam + "&" + photoParam + "&" + hashParam;

        // Вызов photos.saveWallPhoto с полями server, photo, hash,
        vkAPI.sendGet(MethodAPI.PHOTOS_SAVE_WALL.getMethod(), saveParameters);


        //String param = saveParameters + "&access_token=" + ACCESS_TOKEN + "&v=" + API_VERSION;




//        APIUtils api = new APIUtils();
//        api.sendGet("https://api.vk.com/method/photos.saveWallPhoto?", param);



//        System.out.println("--------------------");
//        System.out.println(api.getStatusCode());
//        System.out.println("--------------------");
//        System.out.println("--------------------");
//        System.out.println(api.getResponse());
//        System.out.println("--------------------");
//        System.out.println("--------------------");



        System.out.println("--------------------");
        System.out.println(vkAPI.getStatusCode());
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println(vkAPI.getResponse());
        System.out.println("--------------------");
        System.out.println("--------------------");

        String r = vkAPI.getResponse();

        System.out.println("-------RRR--------");
        System.out.println(r);
        System.out.println("--------------------");
        String rj = "$.response";
        String rjJSON = JsonPath.read(r, rj).toString();

        StringBuffer sb = new StringBuffer(rjJSON);
        sb.delete(0, 1);
        sb.delete(sb.length() - 1, sb.length());
        System.out.println("-------jjjj--------");
        System.out.println(sb.toString());
        System.out.println("--------------------");
        String rjENDPath = "$.id";
        String rjEndJSON = JsonPath.read(sb.toString(), rjENDPath).toString();

        System.out.println("--------------------");
        System.out.println(rjEndJSON);
        System.out.println("--------------------");

        String parameters = ownerId + "&" + postIdParam + "&" + message + "&" + ParameterAPI.ATTACHMENTS + USER_ID + "_" + rjEndJSON;

        vkAPI.sendGet(editTextMethodAPI, parameters);

        //String p = postIdParam + "&attachments=photo381108928_" + rjEndJSON;

        //vkAPI.sendGet(editTextMethodAPI, p);

        System.out.println("--------------------");
        System.out.println(vkAPI.getResponse());
        System.out.println("--------------------");

//        String attachments = ParameterAPI.ATTACHMENTS.getParameter() + "photo381108928_" + rjEndJSON + "&wall=381108928_60190";
//
//        vkAPI.sendGet(MethodAPI.WALL_POST.getMethod(), attachments);
//
//        System.out.println("--------------------");
//        System.out.println(vkAPI.getResponse());
//        System.out.println("--------------------");
    }

    @BeforeClass
    public void preCondition() {
        vkAPI = new vkAPI.VkApiUtils(ACCESS_TOKEN, API_VERSION);
        userMyPage = new UserMyPage(USER_ID);
    }

    @Test
    public void vkTest() {
        openVKMainPage();
        doAuthorization();
        goToMyPage();
        createPostOnWall();
        checkPostOnWallAppear();
        editPostOnWall();
    }
}
