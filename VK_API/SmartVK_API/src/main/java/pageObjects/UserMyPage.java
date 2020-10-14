package pageObjects;

import formsAndMenu.LeftSidebar;
import formsAndMenu.Wall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMyPage {

    private static final Logger logger = LoggerFactory.getLogger(UserMyPage.class);

    private String userId;
    private LeftSidebar leftSidebar = new LeftSidebar();
    private Wall wall;

    public UserMyPage(String userId) {
        this.userId = userId;
        wall = new Wall(userId);
    }

    public Wall getWall() {
        logger.info("Trying to get wall");
        return wall;
    }
}
