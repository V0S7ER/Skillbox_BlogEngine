package main.service;

public enum PostMode {

    POPULAR("popular"),
    BEST("best"),
    EARLY("early"),
    RECENT("recent");


    private final String value;
    PostMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
