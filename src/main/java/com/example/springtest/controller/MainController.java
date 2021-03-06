package com.example.springtest.controller;

import com.example.springtest.domain.Message;
import com.example.springtest.domain.User;
import com.example.springtest.repos.MessageRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private MessageRepoJPA messageRepoJPA;

    @Value("${upload.path}")
        private String uploadPath;


    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Collection<Message> messageList = new ArrayList<>();
        messageList.addAll(messageRepoJPA.findAllByOrderByIdDesc());



        if (filter != null && !filter.isEmpty()) {
            messageList = messageRepoJPA.findByText(filter);
        } else {
            messageList = messageRepoJPA.findAllByOrderByIdDesc();
        }


        model.addAttribute("messages", messageList);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/main", params = "auth")
    public String add(
            @AuthenticationPrincipal User user, // передает текущего пользователя
            @RequestParam String text,
            @RequestParam("file") MultipartFile file) throws IOException {
        Message message = new Message(text, user);

        if (message.getText().length()<1){
            return "ErrorMessageIsEmpty";
        }

        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()){
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }

        messageRepoJPA.save(message);

        return "redirect:/main";
    }

    @PostMapping(value = "/main", params = "notAuth")
    public String addNotAuth(
            @RequestParam String text) throws IOException {
        Message message = new Message(text);

        if (message.getText().length() < 1) {
            return "ErrorMessageIsEmpty";
        }

        messageRepoJPA.save(message);

        return "redirect:/main";
    }

}