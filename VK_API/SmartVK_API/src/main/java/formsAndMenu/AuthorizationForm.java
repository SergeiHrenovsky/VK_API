package formsAndMenu;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorizationForm extends Form {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationForm.class);

    private By emailBoxXPath = By.xpath("//input[@name='email']");
    private ITextBox emailBox = getElementFactory().getTextBox(emailBoxXPath, "emailBox", ElementState.DISPLAYED);

    private By passwordBoxXPath = By.xpath("//input[@name='pass']");
    private ITextBox passwordBox = getElementFactory().getTextBox(passwordBoxXPath, "passwordBox", ElementState.DISPLAYED);

    private By loginButtonXPath = By.xpath("//button[@id='index_login_button']");
    private IButton loginButton = getElementFactory().getButton(loginButtonXPath, "loginButton", ElementState.DISPLAYED);


    public AuthorizationForm(By locator, String name) {
        super(locator, name);
    }

    public void doAuthorization(String login, String password) {
        logger.info("Trying to login VK in service");
        enterEmail(login);
        enterPassword(password);
        clickLoginButton();
    }

    private void enterEmail(String login) {
        logger.info("Trying to enter email");
        emailBox.clearAndType(login);
    }

    private void enterPassword(String password) {
        logger.info("Trying to enter password");
        passwordBox.clearAndType(password);
    }

    private void clickLoginButton() {
        logger.info("Trying to click login button");
        loginButton.click();
    }
}
