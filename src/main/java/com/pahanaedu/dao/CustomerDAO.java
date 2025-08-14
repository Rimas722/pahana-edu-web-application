package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int accountNo);
    Customer getCustomerByAccountNo(int accountNo);
    List<Customer> getAllCustomers();
}
