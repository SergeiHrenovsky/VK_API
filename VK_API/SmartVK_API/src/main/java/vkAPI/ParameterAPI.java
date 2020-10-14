package vkAPI;

public enum ParameterAPI {
    MESSAGE("message="),
    OWNER_ID("owner_id="),
    POST_ID("post_id="),
    SERVER("server="),
    PHOTO("photo="),
    HASH("hash="),
    ATTACHMENTS("attachments=");

    private final String PARAMETER_NAME;

    ParameterAPI(String parameterName) {
        PARAMETER_NAME = parameterName;
    }

    public String getParameter() {
        return PARAMETER_NAME;
    }
}
