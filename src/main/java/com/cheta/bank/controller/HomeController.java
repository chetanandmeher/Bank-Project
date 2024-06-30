package com.cheta.bank.controller;

import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

//    @Autowired
//    UserService userService;

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }


    @GetMapping("/accounts-table")
    public String accountsTable() {
        return "accounts-table";
    }

    @GetMapping("/state-table")
    public String stateTable() {
        return "state-table";
    }

    @GetMapping("/district-table")
    public String districtTable() {
        return "district-table";
    }

    @GetMapping("/city-table")
    public String cityTable() {
        return "city-table";
    }



}
