package com.cheta.bank.service;

import com.cheta.bank.dto.admin.AdminUserDetailsDto;

public interface IAdminService {
    // Admin-specific methods go here.
    public AdminUserDetailsDto getUserByUsername(String username);
    public AdminUserDetailsDto updateUserDetails(AdminUserDetailsDto adminUserDetailsDto);
}
