package com.pahanaedu.dao;

import com.pahanaedu.model.Admin;

public interface AdminDAO {
    Admin getAdminByUsername(String username);
}
