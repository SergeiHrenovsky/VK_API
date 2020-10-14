package formsAndMenu;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeftSidebar {

    private static final Logger logger = LoggerFactory.getLogger(LeftSidebar.class);

    private IElementFactory elementFactory = AqualityServices.getElementFactory();

    private By myPageLinkXPath = By.xpath("//nav//li[1]/a");
    private ILink myPageLink = elementFactory.getLink(myPageLinkXPath, "myPageLink", ElementState.DISPLAYED);

    public void clickMyPageLink() {
        logger.info("Trying to click myPageLink");
        myPageLink.click();
    }
}
