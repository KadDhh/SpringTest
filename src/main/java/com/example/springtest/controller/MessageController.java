package com.example.springtest.controller;

import com.example.springtest.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;


    @GetMapping("/messageControl")
    public String MessageDeletePage()
    {
        return "messageControl";
    }


    @PostMapping(value = "/messageControl", params = "messageDelete")
    public String MessageDelete(@RequestParam ("messageId") Long messageId,
                                Map<String, Object> model){

        messageRepo.deleteById(messageId);
        model.put("message", "Успешно");

        return "messageControl";
    }
}
