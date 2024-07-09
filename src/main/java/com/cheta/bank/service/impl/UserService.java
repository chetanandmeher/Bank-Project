package com.cheta.bank.service.impl;

import com.cheta.bank.dto.response.UserCredentialResponseDto;
import com.cheta.bank.dto.response.UserResponseDto;

import com.cheta.bank.mysql.model.User;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.AddressRepository;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

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
