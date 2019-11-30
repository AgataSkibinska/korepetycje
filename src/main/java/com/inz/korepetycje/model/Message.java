package com.inz.korepetycje.model;

import com.inz.korepetycje.model.advertisement.Advertisement;
import com.inz.korepetycje.model.audit.DateAudit;
import com.inz.korepetycje.payload.ChatMessage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "messages")
public class Message extends DateAudit {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    Message(){

    }

    public Message(ChatMessage chatMessage, User student) {
        this.content = chatMessage.getContent();
        this.sender = chatMessage.getSender();
        this.advertisement = chatMessage.getAdvertisement();
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
