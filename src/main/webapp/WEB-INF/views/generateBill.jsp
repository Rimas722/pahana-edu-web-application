<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generate Bill</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #0056b3; }
        .form-section { background-color: #f9f9f9; padding: 20px; border-radius: 5px; margin-top: 20px; border: 1px solid #eee; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group select, .form-group input { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .item-selection { display: flex; align-items: center; margin-bottom: 10px; }
        .item-selection label { margin-left: 10px; }
        .btn { background-color: #007bff; color: white; padding: 12px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin-top: 20px;}
        .btn:hover { background-color: #0056b3; }
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
            <a href="system?action=showHelpPage">Help</a>
            <a href="logout">Logout</a>
        </div>

        <h2>Generate a New Bill</h2>

        <div class="form-section">
            <form action="system?action=generateBill" method="post">
                <div class="form-group">
                    <label for="customerId">Select Customer:</label>
                    <select id="customerId" name="customerId" required>
                        <option value="">-- Select a Customer --</option>
                        <c:forEach var="customer" items="${customers}">
                            <option value="${customer.accountNo}">${customer.name} (ID: ${customer.accountNo})</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Select Items:</label>
                    <div class="item-list">
                        <c:forEach var="item" items="${items}">
                            <div class="item-selection">
                                <input type="checkbox" id="item-${item.itemId}" name="selectedItems" value="${item.itemId}">
                                <label for="item-${item.itemId}">${item.itemName} - Rs. ${item.price}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                
                <button type="submit" class="btn">Generate Bill</button>
            </form>
        </div>
    </div>
</body>
</html>
