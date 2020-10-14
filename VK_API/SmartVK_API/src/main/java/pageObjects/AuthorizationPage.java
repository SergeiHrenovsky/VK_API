package pageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import formsAndMenu.AuthorizationForm;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.workWithFiles.JsonHandler;

public class AuthorizationPage {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationPage.class);

    private final String URL = JsonHandler.getValue("configuration.json", "VK_URL");

    private final By authorizationFormXPath = By.xpath("//form[@name='login']");
    private AuthorizationForm authorizationForm = new AuthorizationForm(authorizationFormXPath, "authenticationForm");

    public void openPage() {
        logger.info("Trying to open VK welcome web page");
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(URL);
    }

    public void doAuthorization(String email, String password) {
        authorizationForm.doAuthorization(email, password);
    }
}
