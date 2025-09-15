package com.example.message.repository;

import com.example.message.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
// Repository-klassen fungerer som et datalager i hukommelsen.
// Den opretter 3 beskeder ved opstart og returnerer dem via en metode
public class MessageRepository {
    private final List<Message> messages = new ArrayList<>();
    private int messageId = 1;

    public MessageRepository() {
        populateMessages();
    }

    private void populateMessages() {
        while (messageId <= 3) {
            messages.add(new Message(nextId(), "Velkommen til " + messageId + ".semester"));
        }
    }

    private int nextId(){
        return messageId++;
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public Message findMessageById(int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    public Message addMessage(Message message) {
        Message msg = message;
        msg.setId(nextId());
        messages.add(msg);
        return msg;
    }
}

