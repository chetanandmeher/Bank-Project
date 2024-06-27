package com.cheta.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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

    @GetMapping("/users-table")
    public String UsersTable() {
        return "users-table";
    }

    @GetMapping("/user-form")
    public String UserForm() {
        return "user-form";
    }

    @GetMapping("/accounts-table")
    public String AccountsTable() {
        return "accounts-table";
    }

    @GetMapping("/state-table")
    public String StateTable() {
        return "state-table";
    }

    @GetMapping("/district-table")
    public String DistrictTable() {
        return "district-table";
    }

    @GetMapping("/rough")
    public String AccountForm() {
        return "rough";
    }
}
