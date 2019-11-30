package com.inz.korepetycje.controller;

import com.inz.korepetycje.model.Message;
import com.inz.korepetycje.model.User;
import com.inz.korepetycje.payload.ChatMessage;
import com.inz.korepetycje.repository.PersistentMessageRepository;
import com.inz.korepetycje.repository.UserRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ChatController {

    private PersistentMessageRepository messageRepository;
    private UserRepository userRepository;

    public ChatController(PersistentMessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chat/{adId}/{studId}/sendMessage")
    @SendTo("/topic/chat/{adId}/{studId}")
    public Message sendMessage(
        @Payload ChatMessage chatMessage,
        @DestinationVariable("studId") String studentId
    ) {
        Optional<User> studentOptional = userRepository.findById(Long.parseLong(studentId));
        if (studentOptional.isEmpty()) {
            // todo
        }
        User student = studentOptional.get();
        Message message = new Message(chatMessage, student);
        return messageRepository.save(message);
    }

}
