package com.example.demo.Controller;


import com.example.demo.Model.Vendor;
import com.example.demo.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/vendorsection")
public class VendorController {

    @Autowired
    VendorRepository vendorRepository;

    @GetMapping("/read")
    public String vendorlist(Model model){

        List<Vendor> vendorlist = vendorRepository.findAll();

        model.addAttribute("vendorlistforhtml", vendorlist);

        return "vendorpages/vendor";

    }

    @GetMapping("/show")
    public String vendorcontact(Model model){

        List<Vendor> listofvendor = vendorRepository.findAll();
        HashMap<String, Integer> vendornames = new HashMap<>();
        for(Vendor map : listofvendor){

            String value = map.getName();
            if(vendornames.containsKey(value)){
                vendornames.put(value, vendornames.get(value) + 1);
            }
            else {
                vendornames.put(value, 1);
            }
        }
        model.addAttribute("vendornamecount", vendornames);
        return "vendorpages/vendornamecount";
    }


    @GetMapping("/delete")
    public String deletevendor(@RequestParam Long id){

        vendorRepository.deleteById(id);
        return "redirect:/vendorsection/read";
    }

    @GetMapping("/add")
    public String addvendor(Model model){
        Vendor newvendor = new Vendor();
        model.addAttribute("vendor", newvendor);
        return "vendorpages/createvendor";

    }

    @PostMapping("/add")
    public String addvendor(@ModelAttribute Vendor vendor){
        Vendor newvendor = vendorRepository.save(vendor);
        return "redirect:/vendorsection/read";
    }

    @GetMapping("/update")
    public String updatevendor(@RequestParam Long id, Model model){
        Vendor updatevendor = vendorRepository.getById(id);
        model.addAttribute("vendorupdate", updatevendor);
        return "vendorpages/updatevendor";
    }

    @PostMapping("/update")
    public String updatevendor(@ModelAttribute Vendor vendor){
        Date updatedate = new Date(System.currentTimeMillis());
        vendor.setLastupdate(updatedate);
        vendorRepository.save(vendor);
        return "redirect:/vendorsection/read";
    }


}
