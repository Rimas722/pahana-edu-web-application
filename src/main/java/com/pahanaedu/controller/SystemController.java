package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/system")
public class SystemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerService customerService;
    private ItemService itemService;
    private BillingService billingService;

    @Override
    public void init() {
        this.customerService = new CustomerServiceImpl();
        this.itemService = new ItemServiceImpl();
        this.billingService = new BillingServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listCustomers";
        }

        switch (action) {
            case "listItems":
                listItems(request, response);
                break;
            case "showBillPage":
                showBillPage(request, response);
                break;
            case "deleteCustomer":
                deleteCustomer(request, response);
                break;
            case "deleteItem":
                deleteItem(request, response);
                break;
            case "showEditCustomerForm":
                showEditCustomerForm(request, response);
                break;
            case "showEditItemForm":
                showEditItemForm(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "addCustomer":
                addCustomer(request, response);
                break;
            case "addItem":
                addItem(request, response);
                break;
            case "generateBill":
                generateBill(request, response);
                break;
            case "updateCustomer":
                updateCustomer(request, response);
                break;
            case "updateItem": 
                updateItem(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void showEditItemForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Item existingItem = itemService.getItem(itemId);
        request.setAttribute("item", existingItem);
        request.getRequestDispatcher("/WEB-INF/views/editItem.jsp").forward(request, response);
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String itemName = request.getParameter("itemName");
        double price = Double.parseDouble(request.getParameter("price"));

        Item item = new Item(itemId, itemName, price);
        itemService.updateItem(item);
        response.sendRedirect("system?action=listItems");
    }


    private void showEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        Customer existingCustomer = customerService.getCustomer(accountNo);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("/WEB-INF/views/editCustomer.jsp").forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer = new Customer(accountNo, name, address, phone, 0, username, password);
        customerService.updateCustomer(customer);
        response.sendRedirect("system?action=listCustomers");
    }
    
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        customerService.deleteCustomer(accountNo);
        response.sendRedirect("system?action=listCustomers");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        itemService.deleteItem(itemId);
        response.sendRedirect("system?action=listItems");
    }

    private void showBillPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.getAllCustomers();
        List<Item> items = itemService.getAllItems();
        request.setAttribute("customers", customers);
        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/generateBill.jsp").forward(request, response);
    }
    
    private void generateBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String[] selectedItemsIds = request.getParameterValues("selectedItems");

        List<Item> selectedItems = new ArrayList<>();
        if (selectedItemsIds != null) {
            for (String itemIdStr : selectedItemsIds) {
                int itemId = Integer.parseInt(itemIdStr);
                Item item = itemService.getItem(itemId);
                if (item != null) {
                    selectedItems.add(item);
                }
            }
        }

        Bill generatedBill = billingService.generateBill(customerId, selectedItems);

        request.setAttribute("bill", generatedBill);
        request.getRequestDispatcher("/WEB-INF/views/billDetails.jsp").forward(request, response);
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/views/customerList.jsp").forward(request, response);
    }

    private void listItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemService.getAllItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/itemList.jsp").forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer = new Customer(accountNo, name, address, phone, 0, username, password);
        customerService.addCustomer(customer);
        response.sendRedirect("system?action=listCustomers");
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemName = request.getParameter("itemName");
        double price = Double.parseDouble(request.getParameter("price"));

        Item item = new Item(0, itemName, price);
        itemService.addItem(item);
        response.sendRedirect("system?action=listItems");
    }
}
