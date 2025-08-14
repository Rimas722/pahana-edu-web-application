package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
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
    public int addBill(Bill bill) {
        String billQuery = "INSERT INTO bills (customerId, total) VALUES (?, ?)";
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
        String query = "SELECT * FROM bill_items WHERE billId = ?";
        Bill bill = new Bill();
        bill.setBillId(billId);
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int itemId = resultSet.getInt("itemId");
                Item item = itemDAO.getItemById(itemId);
                if (item != null) {
                    items.add(item);
                }
            }
            bill.setItems(items);
            bill.calculateTotal(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getBillsByCustomerId(int customerId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bills WHERE customerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("billId");
                Bill bill = getBillById(billId); 
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
