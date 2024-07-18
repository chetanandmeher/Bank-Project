package com.cheta.bank.controller;

import com.cheta.bank.dto.ListAccountDto;
import com.cheta.bank.dto.UserDto;
import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.dto.AccountDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.mysql.model.Branch;
import com.cheta.bank.repository.BranchRepository;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.AdminService;
import com.cheta.bank.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        UserResponseDto userResponseDto = userService.getUserByUsername(username);
        UserDto userDto = adminService.getUserByUsername(username);

        // get all the account related to the usedId
        List<AccountDto> accountDtoList = adminService.getAllAccountsByUserId(userDto.getId());
        ListAccountDto listAccountDto = new ListAccountDto();
        listAccountDto.setAccounts(accountDtoList);

        // Add model attributes
        model.addAttribute("user", userDto);
        model.addAttribute("accountList", listAccountDto);
        model.addAttribute("accountTypes",List.of("FD","SA","CA"));
        model.addAttribute("branches",getAllBranches());
        return "/admin/user-details";
    }
    // Saving the edited user details in the database
    @PostMapping("/admins/users/{username}/submit-user-details")
    public String updateUserDetails(@ModelAttribute("user") UserDto userDto,
                                    Model model) {
        // update user details and add updated user details to model Attribute
        UserDto userDtoReturn = adminService.updateUserDetails(userDto);
        model.addAttribute("user", userDtoReturn);

        return "redirect:/admins/users/"+userDto.getUsername();
    }
    // Saving the edited user account details in the database
    @PostMapping("/admins/users/{username}/submit-user-account-details")
    public String updateUserAccountDetails(@PathVariable("username") String username,
                                           @ModelAttribute("accountList") ListAccountDto listAccountDto,
                                           Model model) {
        // update user account details and add updated user account details to model Attribute
        ListAccountDto returnListAccountDto = adminService.updateUserAccounts(listAccountDto);
        model.addAttribute("accountList", returnListAccountDto);

        return "redirect:/admins/users/"+username;
    }




    // Accounts Table for admin
    @GetMapping("/admins/accounts")
    public String accountsTable(Model model, HttpSession session) {
        // ge the login Request dto from session
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");

        // get all accounts in the database and add it to the model
        List<AccountDto> accountDtoList = accountService.getAll();
        model.addAttribute("accounts", accountDtoList);
        // return the view name
        return "/admin/accounts-table";
    }

    @Autowired
    BranchRepository branchRepository;

    private List<String> getAllBranches() {
        List<Branch> branchesList = (List<Branch>) branchRepository.findAll();
        return branchesList.stream()
                .map(Branch::getName)
                .toList();
    }

}
