package com.cheta.bank.controller;

import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.impl.UserCredentialService;
import com.cheta.bank.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Controller
public class EmployeeController {

    @Autowired
    UserCredentialService userCredentialService;

    @Autowired
    UserService userService;

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


    @GetMapping("/employees/accounts")
    public String getAllCustomerAccounts() {
        return "/employee/accounts-table";
    }
}
