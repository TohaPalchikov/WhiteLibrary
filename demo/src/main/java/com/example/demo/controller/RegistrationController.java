package com.example.demo.controller;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return"registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> object){

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb!=null){
            object.put("message","User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRole(Role.USER);
        userRepo.save(user);
        return "redirect:/login";
    }
}
