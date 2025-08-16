<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; color: #333; }
        .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h2 { color: #0056b3; }
        .form-section { background-color: #f9f9f9; padding: 20px; border-radius: 5px; margin-top: 20px; border: 1px solid #eee; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input { width: calc(100% - 22px); padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
        .form-group input[readonly] { background-color: #e9ecef; }
        .btn { background-color: #ffc107; color: black; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn:hover { opacity: 0.9; }
        .nav { margin-bottom: 20px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #007bff; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="system?action=listItems">Back to Item List</a>
        </div>

        <h2>Edit Item Details</h2>

        <div class="form-section">
            <form action="system?action=updateItem" method="post">
                <!-- Hidden input to send the item ID -->
                <input type="hidden" name="itemId" value="<c:out value='${item.itemId}' />">
                
                <div class="form-group">
                    <label>Item ID:</label>
                    <input type="number" value="<c:out value='${item.itemId}' />" readonly>
                </div>
                <div class="form-group">
                    <label>Item Name:</label>
                    <input type="text" name="itemName" value="<c:out value='${item.itemName}' />" required>
                </div>
                <div class="form-group">
                    <label>Price:</label>
                    <input type="number" step="0.01" name="price" value="<c:out value='${item.price}' />" required>
                </div>
                <button type="submit" class="btn">Update Item</button>
            </form>
        </div>
    </div>
</body>
</html>
