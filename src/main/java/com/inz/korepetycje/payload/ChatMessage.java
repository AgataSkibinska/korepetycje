package com.inz.korepetycje.payload;

import com.inz.korepetycje.model.User;
import com.inz.korepetycje.model.advertisement.Advertisement;

public class ChatMessage {
    private String content;
    private User sender;
    private Advertisement advertisement;

    public ChatMessage(String content, User sender, Advertisement advertisement) {
        this.content = content;
        this.sender = sender;
        this.advertisement = advertisement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
