package com.prestigecab.prestige_cab.controller;

import com.prestigecab.prestige_cab.service.JpaUserService;
import com.prestigecab.prestige_cab.service.PrestigeCabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private PrestigeCabService prestigeCabService;
    private JpaUserService userService;
    @Autowired
    public LoginController(PrestigeCabService prestigeCabService, JpaUserService userService) {
        this.prestigeCabService = prestigeCabService;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    //    @GetMapping("/home")
//    public String home() {
//        return "home";
//    }



    @GetMapping("/admin/form")
    public String admin() {
        return "inscription";
    }

    @PostMapping("admin/form")
    public String postAdmin(Model model, @RequestParam String login, @RequestParam String email, @RequestParam String pass, @RequestParam String passConfirm, @RequestParam Long role) {
        this.userService.addUser(login,email,pass,role);
        return "redirect:/";
    }
}
