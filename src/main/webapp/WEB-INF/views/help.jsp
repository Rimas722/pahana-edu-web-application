<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Help Section</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background-color: #f4f4f4; color: #333; }
        .header { background-color: #0056b3; color: white; padding: 20px; text-align: center; }
        .header h1 { margin: 0; }
        .container { max-width: 900px; margin: 20px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #0056b3; border-bottom: 2px solid #eee; padding-bottom: 10px; }
        .help-section { margin-bottom: 20px; }
        .help-section h3 { color: #333; }
        .help-section p { line-height: 1.6; }
        .nav { padding: 10px 20px; background-color: #e9ecef; border-radius: 8px; margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
        .nav a:hover { background-color: #ddd; color: black; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Help & Guidelines</h1>
    </div>
    <div class="container">
        <div class="nav">
            <a href="system?action=showDashboard">Dashboard</a>
            <a href="system?action=listCustomers">Customer Management</a>
            <a href="system?action=listItems">Item Management</a>
            <a href="system?action=showBillPage">Generate Bill</a>
            <a href="logout">Logout</a>
        </div>

        <h2>Help & Guidelines</h2>

        <div class="help-section">
            <h3>Customer Management</h3>
            <p>This page allows you to view all registered customers. You can add a new customer by filling out the form at the bottom of the page. To modify or remove a customer, use the "Edit" and "Delete" links next to each customer in the list.</p>
        </div>

        <div class="help-section">
            <h3>Item Management</h3>
            <p>This page shows all available items in the bookshop. You can add a new item using the form. Use the "Edit" and "Delete" links to manage existing items.</p>
        </div>

        <div class="help-section">
            <h3>Generate Bill</h3>
            <p>On this page, you can create a new bill for a customer. First, select the customer from the dropdown menu. Then, check the boxes next to all the items they are purchasing. Click "Generate Bill" to see the final calculated bill, including any applicable discounts.</p>
        </div>
        
        <div class="help-section">
            <h3>Billing Reports</h3>
            <p>The reports page provides a summary of all bills that have been generated in the system. It displays key information for each bill, including the Bill ID, the customer's name and account number, the total amount, and the number of items on the bill.</p>
        </div>
        
        <div class="help-section">
            <h3>Logout</h3>
            <p>Click the "Logout" link in the navigation bar to securely end your session and return to the login page.</p>
        </div>
    </div>
</body>
</html>
