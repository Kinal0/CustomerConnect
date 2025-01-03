package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/customersection")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/read")

    public String customerlist(Model model){

        List<Customer> customerlist = customerRepository.findAll();
        model.addAttribute("customerlistforhtml", customerlist);

        return "customerpages/customer";

    }

    @GetMapping("/show")
    public String statecount(Model model){

        List<Customer> customerlist = customerRepository.findAll();

        HashMap<String, Integer> statemap = new HashMap<String, Integer>();
        for(Customer s : customerlist){
            if(statemap.containsKey(s.getState())){
                statemap.put(s.getState(), statemap.get(s.getState()) + 1);
            }
            else {
                statemap.put(s.getState(), 1);
            }
        }

        model.addAttribute("customerbystate", statemap);
        return  "customerpages/customerstatecount";
    }
    @GetMapping("/delete")
    public String deletevendor(@RequestParam Long id){

        customerRepository.deleteById(id);
        return "redirect:/customersection/read";
    }

    @GetMapping("/add")
    public String addcustomer(Model model){
        Customer newcustomer = new Customer();
        model.addAttribute("customer", newcustomer);
        return "customerpages/createcustomer";
    }

    @PostMapping("/add")
    public String addcustomer(@ModelAttribute Customer customer){

        Date date = new Date(System.currentTimeMillis());
        customer.setRegistrationdate(date);
        customer.setLast_updated(date);

        customerRepository.save(customer);
        return "redirect:/customersection/read";
    }





    @GetMapping("/update")
    public String customerupdate(@RequestParam Long id, Model model){


        Customer updatecustomer = customerRepository.getById(id);

        model.addAttribute("customer",updatecustomer);

        return "/customerpages/updatecustomer";
    }

    @PostMapping("/update")
    public String customerupdate(@ModelAttribute Customer customer){

        System.out.println(customer);
        Date updatedate = new Date(System.currentTimeMillis());
        customer.setLast_updated(updatedate);

        customerRepository.save(customer);

        return "redirect:/customersection/read";
    }


























}
