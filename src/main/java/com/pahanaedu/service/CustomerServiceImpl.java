package com.pahanaedu.service;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.CustomerDAOImpl;
import com.pahanaedu.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public Customer getCustomer(int accountNo) {
        return customerDAO.getCustomerByAccountNo(accountNo);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int accountNo) {
        customerDAO.deleteCustomer(accountNo);
    }
}
