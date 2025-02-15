package com.example.PJWebTa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ContactController {
    @GetMapping("/ContactUs")
    public String contactUs(){
        return "Public/ContactUs";
    }

}
