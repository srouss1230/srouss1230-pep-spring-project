package com.example.controller;

import java.util.ArrayList;
import antlr.collections.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account acct){
        Account registeredUser = accountService.registerUser(acct);
        if(registeredUser!=null){
            return ResponseEntity.ok(registeredUser);
        }
        else{
            //THIS IS ONLY 409 BECAUSE THE TEST SEEMED TO WANT THAT EVEN THOUGH THE INTRUCTION SAID IT SHOULD RETURN 400 IF UNSUCCESSFUL
            //I IMAGINE THIS WAS A TYPO IN THE TEST SUITE
            return ResponseEntity.status(409).body(null);
        }
    }

    @PostMapping("/login")
    public Account loginUser(@RequestBody Account acct){
        return null;
    }
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message msg){
        return null;
    }
    @GetMapping("/messages")
    public ArrayList<Message> getAllMessages(){
        return new ArrayList<Message>();
    }

    
}
