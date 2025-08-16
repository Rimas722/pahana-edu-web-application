<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Management</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 1000px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #0056b3; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .form-section { background-color: #f9f9f9; padding: 20px; border-radius: 5px; margin-top: 30px; border: 1px solid #eee; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input { width: calc(100% - 22px); padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
        .btn { background-color: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn:hover { background-color: #218838; }
        .nav { margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="system?action=listCustomers">Customer Management</a>
            <a href="system?action=listItems">Item Management</a>
            <a href="logout">Logout</a>
        </div>

        <h2>Customer List</h2>
        <table>
            <tr>
                <th>Account No</th>
                <th>Name</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Username</th>
            </tr>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td><c:out value="${customer.accountNo}" /></td>
                    <td><c:out value="${customer.name}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.phone}" /></td>
                    <td><c:out value="${customer.username}" /></td>
                </tr>
            </c:forEach>
        </table>

        <div class="form-section">
            <h2>Add New Customer</h2>
            <form action="system?action=addCustomer" method="post">
                <div class="form-group">
                    <label>Account Number:</label>
                    <input type="number" name="accountNo" required>
                </div>
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" name="name" required>
                </div>
                <div class="form-group">
                    <label>Address:</label>
                    <input type="text" name="address" required>
                </div>
                <div class="form-group">
                    <label>Phone:</label>
                    <input type="text" name="phone" required>
                </div>
                <div class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" required>
                </div>
                <button type="submit" class="btn">Add Customer</button>
            </form>
        </div>
    </div>
</body>
</html>
