package com.example.springtest.controller;

import com.example.springtest.domain.Role;
import com.example.springtest.domain.User;
import com.example.springtest.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, String> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (user.getUsername().length()<=3){
            model.put("message", "Никнейм не менее 3 символов");
            return "registration";
        }

        if (userFromDb != null) {
            model.put("message", "Пользователь существует");
            return "registration";
        }

        user.setActive(true);

        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}