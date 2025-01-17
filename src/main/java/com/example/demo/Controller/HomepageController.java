package com.example.demo.Controller;


import com.example.demo.Model.Customer;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class HomepageController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("")
    public String home(){
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String userlogin(){

        return "defaultpages/login";
    }


    @PostMapping("/login")
    public String userlogin(@RequestParam String name, String pass, Model model){

        List<User> user = userRepository.findAll();

//        User usertemp  = userRepository.findUserByName(name);
        for(User s : user){
            if(name.equals(s.getUsername())){
                if(pass.equals(s.getPassword())){
                    model.addAttribute("username", name);
                    return "defaultpages/homepage";
                }

            }
        }
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homepage(){
        return "defaultpages/homepage";
    }

    @GetMapping("/signup")
    public String adduser(Model model){
        User user = new User();
        model.addAttribute("newuser", user);
        return "defaultpages/signup";
    }

    @PostMapping("/signup")
    public String adduser(@ModelAttribute User user){

        Timestamp time = new Timestamp(System.currentTimeMillis());
        user.setCreateddate(time);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/customersection/search")
    public String searchpage(){

        return "customerpages/customersearch";
    }

    @PostMapping("/customersection/search")
    public String searchpage(@RequestParam String keyword, String type, Model model){

        List<Customer> customerlist = customerRepository.findAll();

        ArrayList<Customer> resultlist = new ArrayList<>();
        switch(type){
            case "lname":

                for(Customer s : customerlist){
                    if(s.getLname().toLowerCase().contains(keyword.toLowerCase())){
                        resultlist.add(s);
                    }
                }
                break;
            case "fname":

                for(Customer s : customerlist){
                    if(s.getFname().toLowerCase().contains(keyword.toLowerCase())){
                        resultlist.add(s);
                    }
                }
                break;

            case "city" :
            for(Customer s : customerlist){
                if(s.getCity().toLowerCase().contains(keyword.toLowerCase())){
                    resultlist.add(s);
                }
            }
            break;

            case "state" :
                for(Customer s : customerlist){
                    if(s.getState().toLowerCase().contains(keyword.toLowerCase())){
                        resultlist.add(s);
                    }
                }

                break;
        }
        model.addAttribute("customerlist",resultlist);
        return "customerpages/searchresults";
    }


}






















