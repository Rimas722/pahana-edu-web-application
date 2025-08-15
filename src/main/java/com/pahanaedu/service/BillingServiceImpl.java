package com.pahanaedu.service;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.BillDAOImpl;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.CustomerDAOImpl;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;

import java.util.List;

public class BillingServiceImpl implements BillingService {

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private BillDAO billDAO = new BillDAOImpl();

    @Override
    public Bill generateBill(int customerId, List<Item> items) {
        Customer customer = customerDAO.getCustomerByAccountNo(customerId);
        if (customer == null) {
            return null;
        }

        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setItems(items);

        bill.calculateTotal();

        billDAO.addBill(bill);

        return bill;
    }
}
