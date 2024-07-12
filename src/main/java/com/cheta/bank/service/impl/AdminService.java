package com.cheta.bank.service.impl;

import com.cheta.bank.dto.admin.AdminUserDetailsDto;
import com.cheta.bank.mysql.model.User;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public AdminUserDetailsDto getUserByUsername(String username) {
        // Get the user by username from database
        UserCredential userCredential = userCredentialRepository.findByUsername(username);
        Optional<User> user = userRepository.findById(userCredential.getId());
        // Create AdminUserDetailsDto and return
        return getAdminUserDetailsDtoFromUserModel(user.get(),userCredential);
    }

    @Override
    public AdminUserDetailsDto updateUserDetails(AdminUserDetailsDto adminUserDetailsDto) {
        // get the user from database with the given user id
        Optional<User> user = userRepository.findById(adminUserDetailsDto.getId());
        if (user.isPresent()) {
            user.get().setFirstName(adminUserDetailsDto.getFirstName() != null && !user.get().getFirstName().equals(adminUserDetailsDto.getFirstName()) ? adminUserDetailsDto.getFirstName().trim() : user.get().getFirstName());
            user.get().setUpdatedAt(LocalDateTime.now());
            user.get().setUpdatedBy(2);
            userRepository.save(user.get());
            // retrieve the saved user return AdminUserDetailsDto
            Optional<User> userReturn = userRepository.findById(adminUserDetailsDto.getId());
            UserCredential userCredential = userCredentialRepository.findByUserId(adminUserDetailsDto.getId());
            return getAdminUserDetailsDtoFromUserModel(userReturn.get(),userCredential);
        }
        return null;
    }


    private AdminUserDetailsDto getAdminUserDetailsDtoFromUserModel(User user,UserCredential userCredential) {
        // Convert user and userCredential to AdminUserDetailsDto
        return AdminUserDetailsDto.builder()
                .id(user.getId())
                .username(userCredential.getUsername())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .email(user.getEmail())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .aadhaarNumber(user.getAadhaarNumber())
                .cin(user.getCin())
                .build();
    }
}
