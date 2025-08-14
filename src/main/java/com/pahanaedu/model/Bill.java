package com.pahanaedu.model;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private int billId;
    private Customer customer;
    private List<Item> items;
    private double total;

    public Bill() {
        this.items = new ArrayList<>();
    }


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double calculateTotal() {
        this.total = 0;
        for (Item item : items) {
            this.total += item.getPrice();
        }

        if (customer != null && customer.getUnitsUsed() > 100) {
            this.total *= 0.90;
        }

        return this.total;
    }
}
