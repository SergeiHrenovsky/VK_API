package formsAndMenu;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostOnWall {
    private static final Logger logger = LoggerFactory.getLogger(PostOnWall.class);

    private String ownerOfWallId;
    private String postId;

    private IElementFactory elementFactory = AqualityServices.getElementFactory();

    private By postXPath;
    private ILabel post;

    private By linkToAuthorXPath = By.xpath("//a[@class='author']");
    private By postTextXPath = By.xpath("//div[@class='wall_post_text zoom_text']");

    public PostOnWall(String ownerOfWallId, String postId) {
        this.ownerOfWallId = ownerOfWallId;
        this.postId = postId;
        postXPath = By.xpath(String.format("//div[@id='post%s_%s']", ownerOfWallId, postId));
        post = elementFactory.getLabel(postXPath, "post", ElementState.DISPLAYED);
    }

    public String getAuthor() {
        logger.info("Trying to get the author of the post");
        return post.findChildElement(linkToAuthorXPath, ElementType.LINK).getAttribute("href");
    }

    public String getText() {
        logger.info("Trying to get the text of the post");
        return post.findChildElement(postTextXPath, ElementType.LABEL).getText();
    }
}
