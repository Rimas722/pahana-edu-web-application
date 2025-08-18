package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import java.util.List;

public interface BillDAO {
    int addBill(Bill bill);
    Bill getBillById(int billId);
    List<Bill> getBillsByCustomerId(int customerId);
    List<Bill> getAllBills();
}
