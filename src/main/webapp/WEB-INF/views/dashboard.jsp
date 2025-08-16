<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background-color: #f4f4f4; color: #333; }
        .header { background-color: #0056b3; color: white; padding: 20px; text-align: center; }
        .header h1 { margin: 0; }
        .container { max-width: 1200px; margin: 20px auto; padding: 20px; }
        .welcome-message { text-align: center; font-size: 20px; margin-bottom: 30px; }
        .dashboard-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; }
        .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); text-align: center; text-decoration: none; color: #333; transition: transform 0.2s; }
        .card:hover { transform: translateY(-5px); }
        .card h3 { margin-top: 0; color: #007bff; }
        .card p { color: #666; }
        .logout-link { display: block; text-align: center; margin-top: 30px; font-weight: bold; color: #dc3545; text-decoration: none; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Pahana Edu - Admin Dashboard</h1>
    </div>

    <div class="container">
        <p class="welcome-message">Welcome, <strong><c:out value="${sessionScope.adminUser}" /></strong>!</p>

        <div class="dashboard-grid">
            <a href="system?action=listCustomers" class="card">
                <h3>Manage Customers</h3>
                <p>View, add, edit, and delete customer accounts.</p>
            </a>
            <a href="system?action=listItems" class="card">
                <h3>Manage Items</h3>
                <p>View, add, edit, and delete items from the bookshop.</p>
            </a>
            <a href="system?action=showBillPage" class="card">
                <h3>Generate Bill</h3>
                <p>Create and calculate a new bill for a customer.</p>
            </a>
            <a href="system?action=showHelpPage" class="card">
                <h3>Help Section</h3>
                <p>View guidelines on how to use the system.</p>
            </a>
        </div>

        <a href="logout" class="logout-link">Logout</a>
    </div>
</body>
</html>
