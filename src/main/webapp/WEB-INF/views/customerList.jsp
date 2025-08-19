<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Management</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background-color: #f4f4f4; color: #333; }
        .header { background-color: #0056b3; color: white; padding: 20px; text-align: center; }
        .header h1 { margin: 0; }
        .container { max-width: 1100px; margin: 20px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
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
        .btn:hover { opacity: 0.9; }
        .nav { padding: 10px 20px; background-color: #e9ecef; border-radius: 8px; margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
        .actions a { margin-right: 10px; text-decoration: none; }
        .success-message { padding: 15px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 4px; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Customer Management</h1>
    </div>
    <div class="container">
        <div class="nav">
            <a href="system?action=showDashboard">Dashboard</a>
            <a href="system?action=listItems">Item Management</a>
            <a href="system?action=showBillPage">Generate Bill</a>
            <a href="system?action=showHelpPage">Help</a>
            <a href="logout">Logout</a>
        </div>

        <!-- Display success message if it exists -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="success-message">
                <c:out value="${sessionScope.successMessage}" />
            </div>
            <c:remove var="successMessage" scope="session" />
        </c:if>

        <h2>Customer List</h2>
        <table>
            <thead>
                <tr>
                    <th>Account No</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Units Consumed</th>
                    <th>Username</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td><c:out value="${customer.accountNo}" /></td>
                        <td><c:out value="${customer.name}" /></td>
                        <td><c:out value="${customer.address}" /></td>
                        <td><c:out value="${customer.phone}" /></td>
                        <td><c:out value="${customer.unitsUsed}" /></td>
                        <td><c:out value="${customer.username}" /></td>
                        <td class="actions">
                            <a href="system?action=showEditCustomerForm&accountNo=${customer.accountNo}">Edit</a>
                            <a href="system?action=deleteCustomer&accountNo=${customer.accountNo}" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
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
                    <label>Units Consumed:</label>
                    <input type="number" name="unitsConsumed" required>
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
