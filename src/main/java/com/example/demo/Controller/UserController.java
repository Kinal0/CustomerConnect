package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @GetMapping("/user")
    public String userlist(Model model){

       List<User> user = userRepository.findAll();
      model.addAttribute("userlist", user);

        return "defaultpages/user";
    }



}
