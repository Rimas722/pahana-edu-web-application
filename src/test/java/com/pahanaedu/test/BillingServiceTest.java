package com.pahanaedu.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.BillingService;
import com.pahanaedu.service.BillingServiceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BillingServiceTest {

    private BillingService billingService;
    private CustomerDAO mockCustomerDAO;
    private BillDAO mockBillDAO;

    @Before
    public void setUp() throws Exception {
        mockCustomerDAO = mock(CustomerDAO.class);
        mockBillDAO = mock(BillDAO.class);

        billingService = new BillingServiceImpl();

        Field customerDaoField = BillingServiceImpl.class.getDeclaredField("customerDAO");
        customerDaoField.setAccessible(true);
        customerDaoField.set(billingService, mockCustomerDAO);

        Field billDaoField = BillingServiceImpl.class.getDeclaredField("billDAO");
        billDaoField.setAccessible(true);
        billDaoField.set(billingService, mockBillDAO);
    }

    @Test
    public void testGenerateBill_NoDiscount() {
        Customer customer = new Customer(1001, "Test User", "Address", "123", 50, "user", "pass");

        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Book A", 200.00));
        items.add(new Item(2, "Pen B", 50.00));

        when(mockCustomerDAO.getCustomerByAccountNo(1001)).thenReturn(customer);

        Bill resultBill = billingService.generateBill(1001, items);

        assertNotNull(resultBill);
        assertEquals("Total should be 250.00 without a discount", 250.00, resultBill.getTotal(), 0.001);

        verify(mockBillDAO, times(1)).addBill(any(Bill.class));
    }

    @Test
    public void testGenerateBill_WithDiscount() {
        Customer customer = new Customer(1002, "Discount User", "Address", "456", 150, "user2", "pass");

        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Book A", 200.00));
        items.add(new Item(2, "Pen B", 50.00));

        when(mockCustomerDAO.getCustomerByAccountNo(1002)).thenReturn(customer);

        Bill resultBill = billingService.generateBill(1002, items);

        assertNotNull(resultBill);
        assertEquals("Total should be 225.00 with a 10% discount", 225.00, resultBill.getTotal(), 0.001);

        verify(mockBillDAO, times(1)).addBill(any(Bill.class));
    }
}
