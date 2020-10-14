package vkAPI;

public enum MethodAPI {
    WALL_POST("wall.post"),
    WALL_POST_EDIT("wall.edit"),
    PHOTOS_UPLOAD_WALL("photos.getWallUploadServer"),
    PHOTOS_SAVE_WALL("photos.saveWallPhoto");

    private final String METHOD_NAME;

    MethodAPI(String methodName) {
        METHOD_NAME = methodName;
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
