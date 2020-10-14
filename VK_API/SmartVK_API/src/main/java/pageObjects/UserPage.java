package pageObjects;

import formsAndMenu.LeftSidebar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPage {

    private static final Logger logger = LoggerFactory.getLogger(UserPage.class);

    private LeftSidebar leftSidebar = new LeftSidebar();

    public void goToMyPage() {
        leftSidebar.clickMyPageLink();
    }
}
