<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bill Details</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 800px; margin: auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #0056b3; border-bottom: 2px solid #0056b3; padding-bottom: 10px; }
        .bill-header, .bill-section { margin-bottom: 20px; }
        .bill-header p, .bill-section p { margin: 5px 0; }
        .bill-header strong, .bill-section strong { display: inline-block; width: 150px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; }
        .total-row { font-weight: bold; background-color: #f2f2f2; }
        .nav { margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="system?action=listCustomers">Customer Management</a>
            <a href="system?action=listItems">Item Management</a>
            <a href="system?action=showBillPage">Generate Bill</a>
            <a href="logout">Logout</a>
            <a href="system?action=showDashboard" class="nav-link">&larr; Back to Dashboard</a>
        </div>

        <h2>Bill Details (Bill ID: #${bill.billId})</h2>

        <div class="bill-header">
            <h3>Customer Information</h3>
            <p><strong>Account No:</strong> <c:out value="${bill.customer.accountNo}" /></p>
            <p><strong>Name:</strong> <c:out value="${bill.customer.name}" /></p>
            <p><strong>Address:</strong> <c:out value="${bill.customer.address}" /></p>
        </div>

        <div class="bill-section">
            <h3>Purchased Items</h3>
            <table>
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="item" items="${bill.items}">
                    <tr>
                        <td><c:out value="${item.itemName}" /></td>
                        <td>Rs. <c:out value="${item.price}" /></td>
                    </tr>
                </c:forEach>
                <tr class="total-row">
                    <td><strong>Total</strong></td>
                    <td><strong>Rs. <c:out value="${bill.total}" /></strong></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
