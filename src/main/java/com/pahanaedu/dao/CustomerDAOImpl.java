package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {

    private Connection connection;

    public CustomerDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (account_no, name, address, phone, units_consumed, username, password_hash) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customer.getAccountNo());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setInt(5, customer.getUnitsUsed()); 
            preparedStatement.setString(6, customer.getUsername());
            preparedStatement.setString(7, customer.getPassword()); 
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setAccountNo(resultSet.getInt("account_no"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setUnitsUsed(resultSet.getInt("units_consumed"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password_hash"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    

    @Override
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET name = ?, address = ?, phone = ?, units_consumed = ?, username = ?, password_hash = ? WHERE account_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setInt(4, customer.getUnitsUsed());
            preparedStatement.setString(5, customer.getUsername());
            preparedStatement.setString(6, customer.getPassword());
            preparedStatement.setInt(7, customer.getAccountNo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int accountNo) {
        String query = "DELETE FROM customers WHERE account_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerByAccountNo(int accountNo) {
        String query = "SELECT * FROM customers WHERE account_no = ?";
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setAccountNo(resultSet.getInt("account_no"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setUnitsUsed(resultSet.getInt("units_consumed"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password_hash"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
