package com.cheta.bank.service.impl;

import com.cheta.bank.dto.UserDto;
import com.cheta.bank.dto.response.UserResponseDto;
import com.cheta.bank.mysql.model.User;
import com.cheta.bank.mysql.model.UserCredential;
import com.cheta.bank.repository.UserCredentialRepository;
import com.cheta.bank.repository.UserRepository;
import com.cheta.bank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IUserService<UserDto, UserDto>{

    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDto getUserByUsername(String username) {
        // Get the user by username from database
        UserCredential userCredential = userCredentialRepository.findByUsername(username);
        Optional<User> user = userRepository.findById(userCredential.getId());
        // Create AdminUserDetailsDto and return
        return convertUserToAdminUserDetailsDto(user.get(),userCredential);
    }


    @Override
    public UserDto updateUserDetails(UserDto userDto) {
        // get the user from database with the given user id
        Optional<User> user = userRepository.findById(userDto.getId());
        if (user.isPresent()) {
            user.get().setFirstName(userDto.getFirstName() != null && !user.get().getFirstName().equals(userDto.getFirstName()) ? userDto.getFirstName().trim() : user.get().getFirstName());
            user.get().setMiddleName(userDto.getMiddleName() != null && (user.get().getMiddleName() == null ||!user.get().getMiddleName().equals(userDto.getMiddleName())) ? userDto.getMiddleName().trim() : user.get().getMiddleName());
            user.get().setLastName(userDto.getLastName() != null && !user.get().getLastName().equals(userDto.getLastName()) ? userDto.getLastName().trim() : user.get().getLastName());
            user.get().setMobileNumber(userDto.getMobileNumber() != null && !user.get().getMobileNumber().equals(userDto.getMobileNumber()) ? userDto.getMobileNumber().trim() : user.get().getMobileNumber());
            user.get().setEmail(userDto.getEmail() != null &&!user.get().getEmail().equals(userDto.getEmail())? userDto.getEmail().trim() : user.get().getEmail());
            user.get().setGender(userDto.getGender() != null && !user.get().getGender().equals(userDto.getGender())? userDto.getGender() : user.get().getGender());
            user.get().setDateOfBirth(userDto.getDateOfBirth() != null &&!user.get().getDateOfBirth().equals(userDto.getDateOfBirth())? userDto.getDateOfBirth() : user.get().getDateOfBirth());
            user.get().setAadhaarNumber(userDto.getAadhaarNumber() != null &&!user.get().getAadhaarNumber().equals(userDto.getAadhaarNumber())? userDto.getAadhaarNumber().trim() : user.get().getAadhaarNumber());
            user.get().setUpdatedAt(LocalDateTime.now());
            user.get().setUpdatedBy(2);
            userRepository.save(user.get());
            // retrieve the saved user return UserDto
            Optional<User> userReturn = userRepository.findById(userDto.getId());
            UserCredential userCredential = userCredentialRepository.findByUserId(userDto.getId());
            return convertUserToAdminUserDetailsDto(userReturn.get(),userCredential);
        }
        return null;
    }

    // ************************ Unused methods ************************
    @Override
    public UserResponseDto getByUserId(Integer userId) {
        throw new UnsupportedOperationException("Method not supported in UserService");

    }
    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }
    // ****************************************************************

    private UserDto convertUserToAdminUserDetailsDto(User user, UserCredential userCredential) {
        // Convert user and userCredential to AdminUserDetailsDto
        return UserDto.builder()
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

// *************************** end of the Service Layer ***************************
}
