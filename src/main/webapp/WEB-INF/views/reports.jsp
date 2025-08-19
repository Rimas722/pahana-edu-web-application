<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Billing Reports</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background-color: #f4f4f4; color: #333; }
        .header { background-color: #0056b3; color: white; padding: 20px; text-align: center; }
        .header h1 { margin: 0; }
        .container { max-width: 1200px; margin: 20px auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #0056b3; border-bottom: 2px solid #eee; padding-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .nav { padding: 10px 20px; background-color: #e9ecef; border-radius: 8px; margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
        .nav a:hover { background-color: #ddd; color: black; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Pahana Edu - Billing Reports</h1>
    </div>

    <div class="container">
        <div class="nav">
            <a href="system?action=showDashboard">Dashboard</a>
            <a href="system?action=listCustomers">Customer Management</a>
            <a href="system?action=listItems">Item Management</a>
            <a href="system?action=showBillPage">Generate Bill</a>
            <a href="system?action=showHelpPage">Help</a>
            <a href="logout">Logout</a>
        </div>

        <h2>All Generated Bills</h2>
        
        <table>
            <thead>
                <tr>
                    <th>Bill ID</th>
                    <th>Customer Name</th>
                    <th>Customer Account No</th>
                    <th>Total Amount</th>
                    <th>Number of Items</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bill" items="${allBills}">
                    <tr>
                        <td><c:out value="${bill.billId}" /></td>
                        <td><c:out value="${bill.customer.name}" /></td>
                        <td><c:out value="${bill.customer.accountNo}" /></td>
                        <td>Rs. <c:out value="${bill.total}" /></td>
                        <td><c:out value="${bill.items.size()}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
