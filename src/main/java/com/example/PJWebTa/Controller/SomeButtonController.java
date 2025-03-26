package com.example.PJWebTa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SomeButtonController {
    @GetMapping("/ContactUs")
    public String contactUs(){
        return "Public/ContactUs";
    }
    @GetMapping("/CourseMain")
    public String courseMain(){
        return "redirect:/Course";
    }

}
