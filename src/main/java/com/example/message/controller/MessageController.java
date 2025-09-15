package com.example.message.controller;

import com.example.message.model.Message;
import com.example.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("message")
// Controller-klassen håndterer HTTP-anmodninger fra klienten.
// Den bruger @Controller i stedet for @RestController og returnerer data via ResponseEntity.
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {

        this.messageService = messageService;
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = messageService.getMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id, @RequestParam(required = false) String caps){
        Message m = messageService.findMessageById(id, caps);
        if (m != null){
            return new ResponseEntity<>(m,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        Message msg = messageService.addMessage(message);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }


}