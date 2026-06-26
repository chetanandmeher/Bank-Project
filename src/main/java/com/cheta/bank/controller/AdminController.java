package com.cheta.bank.controller;

import com.cheta.bank.dto.ListAccountDto;
import com.cheta.bank.dto.UserDto;
import com.cheta.bank.dto.request.LoginRequestDto;
import com.cheta.bank.dto.AccountDto;
import com.cheta.bank.dto.response.TransactionResponseDto;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    /** Dashboard for admin */
    @GetMapping("/admins/dashboard")
    public String getAdminDashboard(Model model, HttpSession session) {

        // Get the login request details from the session
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");
        model.addAttribute("loginRequestDto", loginRequestDto);
        return "/admin/dashboard";
    }

    /**
     * Users related mapping section
     * Contains all user management endpoints
     */

    /**
     * Displays the user table for admin view
     * @param model The Spring MVC Model object
     * @return The users table view
     */
    @GetMapping("/admins/users")
    public String getAllUsers(Model model) {
        List<UserResponseDto> usersResponseDtoList = userService.getAllUsers();
        model.addAttribute("users", usersResponseDtoList);
        // Return the view name
        return "/admin/users-table";
    }

    /**
     * Displays user details page for admin
     * @param model The Spring MVC Model object
     * @param username The username of the user to display
     * @return The user details view
     */
    @GetMapping("/admins/users/{username}")
    public String userDetails(Model model, @PathVariable("username") String username) {
        // Get user details
        UserDto userDto = adminService.getUserByUsername(username);

        // Get all accounts related to the userId
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

    /** Saving the edited user details in the database */
    @PostMapping("/admins/users/{username}/submit-user-details")
    public String updateUserDetails(@ModelAttribute("user") UserDto userDto,
                                    Model model) {
        // Update user details and add updated user details to model attribute
        UserDto userDtoReturn = adminService.updateUserDetails(userDto);
        model.addAttribute("user", userDtoReturn);

        return "redirect:/admins/users/"+userDto.getUsername();
    }

    /** Saving the edited user account details in the database */
    @PostMapping("/admins/users/{username}/submit-user-account-details")
    public String updateUserAccountDetails(@PathVariable("username") String username,
                                           @ModelAttribute("accountList") ListAccountDto listAccountDto,
                                           Model model) {
        // Update user account details and add updated user account details to model attribute
        ListAccountDto returnListAccountDto = adminService.updateUserAccounts(listAccountDto);
        model.addAttribute("accountList", returnListAccountDto);

        return "redirect:/admins/users/"+username;
    }
    /** ****************************** Users related mapping end ******************************* */

    /**
     * Displays the accounts table for admin view
     * @param model The Spring MVC Model object
     * @param session The HTTP session object
     * @return The accounts table view
     */
    @GetMapping("/admins/accounts")
    public String accountsTable(Model model, HttpSession session) {
        // Get the login Request dto from session
        LoginRequestDto loginRequestDto = (LoginRequestDto) session.getAttribute("loginRequestDto");

        // Get all accounts in the database and add it to the model
        List<AccountDto> accountDtoList = accountService.getAll();
        model.addAttribute("accounts", accountDtoList);
        // Return the view name
        return "/admin/accounts-table";
    }

    /**
     * Displays transaction details of all users
     * @param model The Spring MVC Model object
     * @param session The HTTP session object
     * @return The transactions table view
     */
    @GetMapping("/admins/transactions")
    public String getAllTransactions(Model model, HttpSession session) {
        // Get all the transactions from database and show
        model.addAttribute("fakeTransactions",fakeTransactions());
        return "/admin/transactions-table";
    }

    /** Creating fake transactions to show in the admin page */
    private List<TransactionResponseDto> fakeTransactions() {
        List<TransactionResponseDto> fakeTransactionsList = new ArrayList<TransactionResponseDto>();
        for(int i = 0; i <= 50;i++) {
            TransactionResponseDto transactionResponseDto = TransactionResponseDto.builder()
                    .id(i)
                    .transactionId(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                    .userId(1)
                    .accountNumber(String.valueOf(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)))
                    .remark("hotel")
                    .dateTime(LocalDateTime.now())
                    .type(Arrays.asList("Debit","Credit","Deposit").get(new Random().nextInt(3)))
                    .status(Arrays.asList("Successful","Pending","Failed").get(new Random().nextInt(3)))
                    .amount(ThreadLocalRandom.current().nextDouble(1000, 999999999999.99))
                    .build();
            fakeTransactionsList.add(transactionResponseDto);
        }
        return fakeTransactionsList;
    }

    /** *****************************Transactions end***************************** */


    @Autowired
    BranchRepository branchRepository;

    private List<String> getAllBranches() {
        List<Branch> branchesList = (List<Branch>) branchRepository.findAll();
        return branchesList.stream()
                .map(Branch::getName)
                .toList();
    }

}
