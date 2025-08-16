<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Management</title>
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
        .btn:hover { opacity: 0.9; }
        .nav { margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
        .actions a { margin-right: 10px; text-decoration: none; }
        .success-message { padding: 15px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 4px; margin-bottom: 20px; }
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
        
        <!-- Display success message if it exists -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="success-message">
                <c:out value="${sessionScope.successMessage}" />
            </div>
            <c:remove var="successMessage" scope="session" />
        </c:if>

        <h2>Item List</h2>
        <table>
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><c:out value="${item.itemId}" /></td>
                    <td><c:out value="${item.itemName}" /></td>
                    <td><c:out value="${item.price}" /></td>
                    <td class="actions">
                        <a href="system?action=showEditItemForm&itemId=${item.itemId}">Edit</a>
                        <a href="system?action=deleteItem&itemId=${item.itemId}" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="form-section">
            <h2>Add New Item</h2>
            <form action="system?action=addItem" method="post">
                <div class="form-group">
                    <label>Item Name:</label>
                    <input type="text" name="itemName" required>
                </div>
                <div class="form-group">
                    <label>Price:</label>
                    <input type="number" step="0.01" name="price" required>
                </div>
                <button type="submit" class="btn">Add Item</button>
            </form>
        </div>
    </div>
</body>
</html>
