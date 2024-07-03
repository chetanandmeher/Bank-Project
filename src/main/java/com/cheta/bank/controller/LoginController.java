package com.cheta.bank.controller;


import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @GetMapping("/")
    public String login(Model model) {

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        model.addAttribute("loginRequestDto", loginRequestDto);
        return "index";
    }

    @PostMapping("/dashboard")
    public String dashboard(@ModelAttribute("loginRequestDto") LoginRequestDto loginRequestDto) {
        // get the user's role from the database and check it it is admin
        UserCredential userCredential = userCredentialRepository.findByUsername(loginRequestDto.getUsername());
        if (userCredential != null && userCredential.getUserRole().equals("Admin")) {
            return "admin-dashboard";
        } else if (userCredential != null && userCredential.getUserRole().equals("Customer")) {
            return "forgot-password";
        } else {
            return "index";
        }
    }
}
