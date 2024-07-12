package com.cheta.bank.controller;

import com.cheta.bank.dto.response.AccountResponseDto;
import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.service.impl.AccountService;
import com.cheta.bank.service.impl.UserCredentialService;
import com.cheta.bank.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@Controller
public class EmployeeController {

    @Autowired
    UserCredentialService userCredentialService;

    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;

    @GetMapping("/employees/dashboard")
    public String getEmployeeDashboard() {
        return "/employee/dashboard";
    }

    @GetMapping("/employees/customers")
    public String getAllCustomers(Model model) {

        // get all customer from database
        // get all userCredentials
        List<UserCredentialResponseDto> userCredentialResponseDtoList = userCredentialService.getAllByUserRole("Customer");
        // get teh userid from each userCredentials
        List<Integer> customerUserIdlist = userCredentialResponseDtoList.stream()
                .map(UserCredentialResponseDto::getUserId)
                .toList();
        // get the users with userId in the userIdList
        List<UserResponseDto> userResponseDtoList = customerUserIdlist.stream()
                .map(userService::getByUserId)
                .filter(Objects::nonNull)
                .toList();
        model.addAttribute("customers", userResponseDtoList);

        return "/employee/customers-table";
    }


    // get the customer details when clicked on the username in the customer table
    @GetMapping("/employees/customers/{username}")
    public String getCustomerDetails(Model model, @PathVariable("username") String username) {
        // add the customer details to model
        UserResponseDto customer = userService.getUserByUsername(username);
        model.addAttribute("customer",customer);
        // add all th4 account related to the customer's userId
        List<AccountResponseDto> accountResponseDtoList = accountService.getAllByUserId(customer.getId());
        model.addAttribute("accounts",accountResponseDtoList);
        return "employee/customer-details";
    }



    @GetMapping("/employees/accounts")
    public String getAllCustomerAccounts(Model model) {
        // get all the customer accounts from database and show
        model.addAttribute("accounts",accountService.getAllByUserRole("Customer"));
        return "/employee/accounts-table";
    }

    @GetMapping("/employees/transactions")
    public String getCustomerTransactions(Model model) {
        // get all the transactions from database and show
//        model.addAttribute("transactions", accountService.getAllTransactions());
        return "/employee/transactions-table";
    }
}
