package com.toyproject.hello.dev.admin.service;

import com.toyproject.hello.dev.admin.repository.AdminRepository;
import com.toyproject.hello.dev.admin.vo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminId(String adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public String getAdminPassword(String adminId) {
        return adminRepository.findByAdminId(adminId)
                .map(Admin::getAdminPassword)
                .orElse("Password not found");
    }
}