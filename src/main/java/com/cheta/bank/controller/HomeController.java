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

    @GetMapping("/customer-dashboard")
    public String customerDashboard(){
        return "/customer/customer-dashboard";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }


    @GetMapping("/state-table")
    public String stateTable() {
        return "/admin/state-table";
    }

    @GetMapping("/district-table")
    public String districtTable() {
        return "/admin/district-table";
    }

    @GetMapping("/city-table")
    public String cityTable() {
        return "/admin/city-table";
    }



}
