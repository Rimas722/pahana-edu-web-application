package com.pahanaedu.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.pahanaedu.dao.AdminDAO;
import com.pahanaedu.model.Admin;
import com.pahanaedu.service.AdminService;
import com.pahanaedu.service.AdminServiceImpl;

import java.lang.reflect.Field;

public class AdminServiceTest {

    private AdminService adminService;
    private AdminDAO mockAdminDAO;

    private String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());

    @Before
    public void setUp() throws Exception {

        mockAdminDAO = new AdminDAO() {
            @Override
            public Admin getAdminByUsername(String username) {
                if ("admin".equals(username)) {
                    Admin admin = new Admin();
                    admin.setUsername("admin");
                    admin.setPassword(hashedPassword);
                    return admin;
                }
                return null;
            }
        };

        adminService = new AdminServiceImpl();

        Field daoField = AdminServiceImpl.class.getDeclaredField("adminDAO");
        daoField.setAccessible(true);
        daoField.set(adminService, mockAdminDAO);
    }

    @Test
    public void testValidateAdmin_CorrectCredentials() {
        // Test Case 1: Use the correct username and password
        // Expected result: true (login should be successful)
        boolean result = adminService.validateAdmin("admin", "admin123");
        assertTrue("Login should succeed with correct credentials", result);
    }

    @Test
    public void testValidateAdmin_IncorrectPassword() {
        // Test Case 2: Use the correct username but a wrong password
        // Expected result: false (login should fail)
        boolean result = adminService.validateAdmin("admin", "wrongpassword");
        assertFalse("Login should fail with an incorrect password", result);
    }

    @Test
    public void testValidateAdmin_IncorrectUsername() {
        // Test Case 3: Use a username that does not exist
        // Expected result: false (login should fail)
        boolean result = adminService.validateAdmin("unknownuser", "admin123");
        assertFalse("Login should fail with a non-existent username", result);
    }
}
