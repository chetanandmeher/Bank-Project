package com.cheta.bank.service.impl;

import com.cheta.bank.dto.AccountDto;
import com.cheta.bank.dto.ListAccountDto;
import com.cheta.bank.dto.request.UserRequestDto;
import com.cheta.bank.dto.response.UserResponseDto;

import com.cheta.bank.mysql.model.User;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.AddressRepository;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService<UserResponseDto, UserRequestDto> {

    // Inject the userRepository dependency
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        // Get all the users from the database
        List<User> users = (List<User>) userRepository.findAll();
        System.out.println("********************************************************");
        System.out.println(users);
        System.out.println("********************************************************");
        // Convert each user to a user response DTO and add it to a list
        // Return the list of user response DTOs
        return users.stream()
                    .map(user -> convertUserToUserResponseDto(user, userCredentialRepository.getUserCredentialByUserId(user.getId())))
                    .toList();
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        // Get the user by username from the database
        UserCredential userCredential = userCredentialRepository.findByUsername(username);
        Optional<User> user = userRepository.findById(userCredential.getUserId());
        // Convert user to userResponseDto and return it.
        return convertUserToUserResponseDto(user.get(), userCredential);
    }

    @Override
    public UserResponseDto getByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return convertUserToUserResponseDto(user.get(), userCredentialRepository.getUserCredentialByUserId(user.get().getId()));
    }


    // Update the user's details
    @Override
    @Transactional
    public UserResponseDto updateUserDetails(UserRequestDto userRequestDto) {
        // save all the data from userRequestDto to the database
        Optional<User> user = userRepository.findById(userRequestDto.getId());
        if (user.isPresent()) {
            // this code will save data in 'user' table
            user.get().setFirstName(userRequestDto.getFirstName() != null && !user.get().getFirstName().equals(userRequestDto.getFirstName()) ? userRequestDto.getFirstName() : user.get().getFirstName());
            user.get().setUpdatedAt(LocalDateTime.now());
            user.get().setUpdatedBy(2);
            userRepository.save(user.get());
            return convertUserToUserResponseDto(userRepository.findById(userRequestDto.getId()).get(),
                                                userCredentialRepository.findByUserId(userRequestDto.getId()));
        } else {
            System.out.println("User with Id: " + userRequestDto.getId() + " not found");
            return null;
        }
    }

    @Override
    public ListAccountDto updateUserAccounts(ListAccountDto listAccountDto) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccountsByUserId(Integer userId) {
        return List.of();
    }


    private UserResponseDto convertUserToUserResponseDto(User user, UserCredential userCredential) {
        return UserResponseDto.builder()
                .id(user.getId())
                .cin(user.getCin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .aadhaarNumber(user.getAadhaarNumber())
                .mobileNumber(user.getMobileNumber())
                .gender(user.getGender())
                .username(userCredential.getUsername())
                .build();
    }


}
