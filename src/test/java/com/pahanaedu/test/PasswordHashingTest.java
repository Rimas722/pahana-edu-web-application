package com.pahanaedu.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;


public class PasswordHashingTest {

    @Test
    public void testPasswordHashingAndVerification() {
        String plainPassword = "admin123";

        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        assertNotNull(hashedPassword);
        System.out.println("Generated Hash: " + hashedPassword);

        assertTrue("Password should match the generated hash", BCrypt.checkpw(plainPassword, hashedPassword));

        assertFalse("A wrong password should not match the hash", BCrypt.checkpw("wrongpassword", hashedPassword));
    }
}
