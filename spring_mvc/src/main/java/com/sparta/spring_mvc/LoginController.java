package com.sparta.spring_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginfile(){
        return "redirect:/login-form.html";
    }

    @PostMapping("/login")
    public String loginprocess(@ModelAttribute Login login, Model model){
        if(login.getId().equals(login.getPassword())){
            model.addAttribute("loginId",login.getId());
        }
        return "login-result";
    }
}

