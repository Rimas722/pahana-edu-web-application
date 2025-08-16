package com.pahanaedu.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.CustomerServiceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerDAO mockCustomerDAO;

    @Before
    public void setUp() throws Exception {
        mockCustomerDAO = mock(CustomerDAO.class);
        customerService = new CustomerServiceImpl();

        Field daoField = CustomerServiceImpl.class.getDeclaredField("customerDAO");
        daoField.setAccessible(true);
        daoField.set(customerService, mockCustomerDAO);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(1001, "Test User", "123 Test St", "0123456789", 50, "testuser", "password");

        customerService.addCustomer(customer);

        verify(mockCustomerDAO, times(1)).addCustomer(customer);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> sampleCustomers = new ArrayList<>();
        sampleCustomers.add(new Customer(1001, "Test User 1", "Address 1", "111", 50, "user1", "pass1"));
        sampleCustomers.add(new Customer(1002, "Test User 2", "Address 2", "222", 150, "user2", "pass2"));
        
        when(mockCustomerDAO.getAllCustomers()).thenReturn(sampleCustomers);

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull("The returned list should not be null", result);
        assertEquals("The list should contain 2 customers", 2, result.size());
    }
    
    @Test
    public void testDeleteCustomer() {
        int customerIdToDelete = 1001;

        customerService.deleteCustomer(customerIdToDelete);

        verify(mockCustomerDAO, times(1)).deleteCustomer(customerIdToDelete);
    }
}
