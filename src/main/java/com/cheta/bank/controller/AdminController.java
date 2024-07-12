package com.cheta.bank.controller;

import com.cheta.bank.dto.admin.AdminUserDetailsDto;
import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.AdminService;
import com.cheta.bank.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    AccountService accountService;

    @Autowired
    AdminService adminService;

    // Dashboard for admin
    @GetMapping("/admins/dashboard")
    public String getAdminDashboard(Model model, HttpSession session) {

        // Get the login request details from the session
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");
        model.addAttribute("loginRequestDto", loginRequestDto);
        return "/admin/dashboard";
    }

    // User Table for admin
    @GetMapping("/admins/users")
    public String getUsers(Model model) {
        List<UserResponseDto> usersResponseDtoList = userService.getAllUsers();
        model.addAttribute("users", usersResponseDtoList);
        // Return the view name
        return "/admin/users-table";
    }

    // User Details for admin page
    @GetMapping("/admins/users/{username}")
    public String userDetails(Model model, @PathVariable("username") String username) {
        // user details
        UserResponseDto userResponseDto = userService.getUserByUsername(username);
        AdminUserDetailsDto adminUserDetailsDto = adminService.getUserByUsername(username);
        // get all the account related to th usedId
        List<AccountResponseDto> accountResponseDtoList = accountService.getAllByUserId(userResponseDto.getId());
        // Add model attributes
        model.addAttribute("user", adminUserDetailsDto);
        model.addAttribute("accounts", accountResponseDtoList);

        return "/admin/user-details";
    }
    @PostMapping("/admins/users/{username}")
    public String updateUserDetails(@PathVariable("username") String username,
                                    @ModelAttribute("user") AdminUserDetailsDto adminUserDetailsDto,
                                    Model model) {
        // update user details
        AdminUserDetailsDto adminUserDetailsDtoReturn = adminService.updateUserDetails(adminUserDetailsDto);
        // Add updated user details to redirect attributes
        model.addAttribute("user", adminUserDetailsDtoReturn);

        return "/admin/user-details";
    }



    // Accounts Table for admin
    @GetMapping("/admins/accounts")
    public String accountsTable(Model model, HttpSession session) {
        // ge the login Request dto from session
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");

        // get all accounts in the database and add it to the model
        List<AccountResponseDto> accountResponseDtoList = accountService.getAll();
        model.addAttribute("accounts", accountResponseDtoList);
        // return the view name
        return "/admin/accounts-table";
    }


}
