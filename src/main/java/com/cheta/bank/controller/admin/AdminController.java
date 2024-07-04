package com.cheta.bank.controller.admin;

import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    AccountService accountService;

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

    // User Details for admin
    @GetMapping("/users/{username}")
    public String userForm(Model model, @PathVariable String username) {
        // user details
        UserResponseDto userResponseDto = userService.getUserByUsername(username);

        // All accounts
        AccountResponseDto accountResponseDto1 = AccountResponseDto.builder()
                .accountNumber("123456789")
                .type("SA")
                .balance(10000.0)
                .rateOfInterest(5.9f)
                .branchName("SambalPur")
                .build();
        accountResponseDto1.setOpeningDate(LocalDate.now());

        AccountResponseDto accountResponseDto2 = AccountResponseDto.builder()
                .accountNumber("123456789")
                .type("SA")
                .balance(10000.0)
                .rateOfInterest(5.9f)
                .branchName("NYC")
                .build();
        accountResponseDto2.setOpeningDate(LocalDate.now());

        List<AccountResponseDto> accountResponseDtoList = List.of(accountResponseDto1, accountResponseDto2);

        // Add model attributes
        model.addAttribute("user", userResponseDto);
        model.addAttribute("accounts", accountResponseDtoList);

        return "/admin/user-details";

    }

    // Accounts Table for admin
    @GetMapping("/admins/accounts")
    public String accountsTable(Model model, HttpSession session) {
        // ge the login Request dto from session
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");

        // get all accounts in the database and add it to the model
        List<AccountResponseDto> accountResponseDtoList = accountService.getAllAccounts();
        model.addAttribute("accounts", accountResponseDtoList);
        // return the view name
        return "/admin/accounts-table";

    }


    @GetMapping("/rough")
    public String rough(Model model) {
        // user details
        UserResponseDto userResponseDto = userService.getUserByUsername("cheta");

        // All accounts
        AccountResponseDto accountResponseDto1 = AccountResponseDto.builder()
                .accountNumber("123456789")
                .type("SA")
                .balance(10000.0)
                .rateOfInterest(5.9f)
                .branchName("sambalpur")
                .build();
        accountResponseDto1.setOpeningDate(LocalDate.now());

        AccountResponseDto accountResponseDto2 = AccountResponseDto.builder()
                .accountNumber("1234")
                .type("FD")
                .balance(5467.0)
                .rateOfInterest(4.5f)
                .branchName("NYC")
                .build();
        accountResponseDto2.setOpeningDate(LocalDate.now());

        List<AccountResponseDto> accountResponseDtoList = List.of(accountResponseDto1, accountResponseDto2);

        // Account type
        List<String> accountTypes = List.of("FD(Fixed Deposit)", "CA(Current Account)", "SA(Saving Account)");

        // Branches
        List<String> branches = List.of("Branch 1", "Branch 2", "Branch 3");

        // Add model attributes
        model.addAttribute("user", userResponseDto);
        model.addAttribute("accounts", accountResponseDtoList);
        model.addAttribute("accountTypes", accountTypes);
        model.addAttribute("branches", branches);
        return "rough";
    }



}
