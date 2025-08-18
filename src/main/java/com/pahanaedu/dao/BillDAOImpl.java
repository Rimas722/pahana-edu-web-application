package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    private Connection connection;
    private ItemDAO itemDAO;

    public BillDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
            this.itemDAO = new ItemDAOImpl(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT b.bill_id, b.total_amount, c.* FROM bills b JOIN customers c ON b.customer_account_no = c.account_no ORDER BY b.bill_id DESC";
        
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                Bill bill = new Bill();
                Customer customer = new Customer();

                customer.setAccountNo(rs.getInt("account_no"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setUnitsUsed(rs.getInt("units_consumed"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password_hash"));

                bill.setBillId(rs.getInt("bill_id"));
                bill.setTotal(rs.getDouble("total_amount"));
                bill.setCustomer(customer);

                List<Item> items = getItemsForBill(bill.getBillId());
                bill.setItems(items);
                
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    
    private List<Item> getItemsForBill(int billId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT itemId FROM bill_items WHERE billId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = itemDAO.getItemById(rs.getInt("itemId"));
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public int addBill(Bill bill) {
        String billQuery = "INSERT INTO bills (customer_account_no, total_amount) VALUES (?, ?)";
        String billItemQuery = "INSERT INTO bill_items (billId, itemId) VALUES (?, ?)";
        int billId = 0;
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement billStmt = connection.prepareStatement(billQuery, Statement.RETURN_GENERATED_KEYS)) {
                billStmt.setInt(1, bill.getCustomer().getAccountNo());
                billStmt.setDouble(2, bill.getTotal());
                billStmt.executeUpdate();
                ResultSet generatedKeys = billStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    billId = generatedKeys.getInt(1);
                    bill.setBillId(billId);
                }
            }
            try (PreparedStatement billItemStmt = connection.prepareStatement(billItemQuery)) {
                for (Item item : bill.getItems()) {
                    billItemStmt.setInt(1, billId);
                    billItemStmt.setInt(2, item.getItemId());
                    billItemStmt.addBatch();
                }
                billItemStmt.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billId;
    }

    @Override
    public Bill getBillById(int billId) {
        List<Bill> allBills = getAllBills();
        for(Bill bill : allBills) {
            if(bill.getBillId() == billId) {
                return bill;
            }
        }
        return null;
    }

    @Override
    public List<Bill> getBillsByCustomerId(int customerId) {
        List<Bill> customerBills = new ArrayList<>();
        List<Bill> allBills = getAllBills();
        for (Bill bill : allBills) {
            if (bill.getCustomer().getAccountNo() == customerId) {
                customerBills.add(bill);
            }
        }
        return customerBills;
    }
}
