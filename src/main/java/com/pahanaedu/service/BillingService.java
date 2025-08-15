package com.pahanaedu.service;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Item;
import java.util.List;

public interface BillingService {
    Bill generateBill(int customerId, List<Item> items);
}
