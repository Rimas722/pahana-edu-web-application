package com.pahanaedu.service;

import com.pahanaedu.model.Customer;
import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    Customer getCustomer(int accountNo);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int accountNo);
}
