package com.example.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.Optional;
import com.example.entity.Message;
import java.util.List;
import java.util.stream.*;


@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountService accountService;
    
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService){
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Message createMessage(Message message){
        boolean postedByIsNotValid = (accountService.getAccountForId(message.getPostedBy()) == null);
        boolean messageTooLong = (message.getMessageText().length() > 255);
        boolean messageIsBlank = (message.getMessageText() == "");
        
        if(postedByIsNotValid || messageTooLong || messageIsBlank){
            return null;
        }

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){ 
        return messageRepository.findAll();
    }

    public Message getMessageById(int Id){
        Optional<Message> foundMsg = messageRepository.findById(Id);
        if (foundMsg.isPresent()){
            return foundMsg.get();
        }
        else{
            return null;
        }
    }
    public boolean deleteMessage(int Id){
        if(messageRepository.findById(Id).isPresent()){
            messageRepository.deleteById(Id);
            return true;
        }
        else{
            return false;
        }
    }
    public Message updateMessage(int Id, String newText){
        Optional<Message> updateOpt = messageRepository.findById(Id);

        boolean messageDoesNotExist = updateOpt.isEmpty();
        boolean messageTooLong = (newText.length() > 255);
        boolean messageIsBlank = (newText == "");

        if(messageTooLong || messageIsBlank || messageDoesNotExist ){
            return null;
        }
        
        Message update = updateOpt.get();
        return messageRepository.save(update);
    }

    public List<Message> getAllMessagesForAccount(int accountId){ 
        return getAllMessages().stream()
                                .filter(msg -> (msg.getPostedBy() == accountId))
                                .collect(Collectors.toList());
    }
}
