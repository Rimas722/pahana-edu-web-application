package com.pahanaedu.service;

import org.mindrot.jbcrypt.BCrypt; 
import com.pahanaedu.dao.AdminDAO;
import com.pahanaedu.dao.AdminDAOImpl;
import com.pahanaedu.model.Admin;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean validateAdmin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        
        if (admin != null && admin.getPassword() != null) {
            if (BCrypt.checkpw(password, admin.getPassword())) {
                return true; 
            }
        }
        
        return false;
    }
}