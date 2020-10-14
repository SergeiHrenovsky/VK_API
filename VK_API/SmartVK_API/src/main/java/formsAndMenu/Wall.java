package formsAndMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wall {
    private static final Logger logger = LoggerFactory.getLogger(Wall.class);

    private String ownerOfWallId;

    public Wall(String ownerOfWallId) {
        this.ownerOfWallId = ownerOfWallId;
    }

    public PostOnWall getPost(String postId) {
        logger.info("Trying to get post");
        return new PostOnWall(ownerOfWallId, postId);
    }
}
