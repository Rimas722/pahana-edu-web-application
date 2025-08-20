<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            background-color: #f0f2f5;
        }
        .wrapper { display: flex; flex-direction: column; min-height: 100%; }
        .header { background-color: #0056b3; color: white; padding: 20px; text-align: center; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .header h1 { margin: 0; font-size: 32px; font-weight: 600; }
        .container { flex: 1; max-width: 900px; margin: 30px auto; padding: 20px; }
        .welcome-message { text-align: center; font-size: 28px; font-weight: 300; margin-bottom: 40px; color: #333; }
        .dashboard-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 25px; }

        .card {
            position: relative;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
            border: 1px solid #dddfe2;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .card:hover { transform: translateY(-5px); box-shadow: 0 8px 16px rgba(0,0,0,0.1); }
        
        .card a {
            position: absolute;
            top: 0; left: 0;
            width: 100%; height: 100%;
            text-indent: -9999px;
            overflow: hidden;
        }
        .card h3 { margin-top: 0; color: #0056b3; font-size: 20px; }
        .card p { color: #606770; font-size: 16px; }

        .logout-card { background-color: #ffebee; border-color: #ef9a9a; }
        .logout-card h3 { color: #d32f2f; }

        .footer { text-align: center; padding: 20px; background-color: #ffffff; border-top: 1px solid #dddfe2; color: #606770; font-size: 14px; }
        .footer p { margin: 5px 0; }
    </style>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <h1>Pahana Edu - Admin Dashboard</h1>
        </div>

        <div class="container">
            <p class="welcome-message">
                Welcome, <strong><c:out value="${sessionScope.adminUser}" /></strong>!
            </p>

            <div class="dashboard-grid">
                <div class="card">
                    <h3>Manage Customers</h3>
                    <p>View, add, edit, and delete customer accounts.</p>
                    <a href="system?action=listCustomers">Go</a>
                </div>

                <div class="card">
                    <h3>Manage Items</h3>
                    <p>View, add, edit, and delete items from the bookshop.</p>
                    <a href="system?action=listItems">Go</a>
                </div>

                <div class="card">
                    <h3>Generate Bill</h3>
                    <p>Create and calculate a new bill for a customer.</p>
                    <a href="system?action=showBillPage">Go</a>
                </div>

                <div class="card">
                    <h3>View Reports</h3>
                    <p>See a summary of all generated bills.</p>
                    <a href="system?action=showReports">Go</a>
                </div>

                <div class="card">
                    <h3>Help Section</h3>
                    <p>View guidelines on how to use the system.</p>
                    <a href="system?action=showHelpPage">Go</a>
                </div>

                <div class="card logout-card">
                    <h3>Logout</h3>
                    <p>Securely sign out of your session.</p>
                    <a href="logout">Go</a>
                </div>
            </div>
        </div>
        
        <div class="footer">
            <p><strong>Pahana Edu Bookshop</strong></p>
            <p>Developed by: Mohamed Rimas | Student ID: KD/BSCSD/20/10</p>
        </div>
    </div>
</body>
</html>
