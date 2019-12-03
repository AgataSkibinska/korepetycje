package com.inz.korepetycje.payload.opinion;

public class OpinionRequest {
    private int rating;
    private String content;
    private String userName;

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return userName;
    }
}
