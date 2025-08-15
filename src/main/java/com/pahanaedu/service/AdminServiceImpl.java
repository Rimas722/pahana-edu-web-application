package com.pahanaedu.service;

import com.pahanaedu.dao.AdminDAO;
import com.pahanaedu.dao.AdminDAOImpl;
import com.pahanaedu.model.Admin;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean validateAdmin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
