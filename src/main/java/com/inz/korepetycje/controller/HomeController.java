package com.inz.korepetycje.controller;

import com.inz.korepetycje.entity.User;
import com.inz.korepetycje.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/users")
    public @ResponseBody Iterable<User> users() {
        return userRepository.findAll();
    }

//    @PostMapping(value = "/register")
//    public long register(@RequestBody UserDto user){
//        userRepository.save(new)
//    }

}