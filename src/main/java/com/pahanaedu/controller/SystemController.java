package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.*;
import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.BillDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/system")
public class SystemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerService customerService;
    private ItemService itemService;
    private BillingService billingService;
    private BillDAO billDAO;

    @Override
    public void init() {
        this.customerService = new CustomerServiceImpl();
        this.itemService = new ItemServiceImpl();
        this.billingService = new BillingServiceImpl();
        this.billDAO = new BillDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "showDashboard";
        }

        switch (action) {
            case "showDashboard":
                showDashboard(request, response);
                break;
            case "showReports":
                showReports(request, response);
                break;
            case "listCustomers":
                listCustomers(request, response);
                break;
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
            case "showHelpPage":
                showHelpPage(request, response);
                break;
            default:
                showDashboard(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        switch (action) {
            case "addCustomer":
                addCustomer(request, response);
                session.setAttribute("successMessage", "Customer added successfully!");
                response.sendRedirect("system?action=listCustomers");
                break;
            case "updateCustomer":
                updateCustomer(request, response);
                session.setAttribute("successMessage", "Customer updated successfully!");
                response.sendRedirect("system?action=listCustomers");
                break;
            case "addItem":
                addItem(request, response);
                session.setAttribute("successMessage", "Item added successfully!");
                response.sendRedirect("system?action=listItems");
                break;
            case "updateItem":
                updateItem(request, response);
                session.setAttribute("successMessage", "Item updated successfully!");
                response.sendRedirect("system?action=listItems");
                break;
            case "generateBill":
                generateBill(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }
    
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        customerService.deleteCustomer(accountNo);
        HttpSession session = request.getSession();
        session.setAttribute("successMessage", "Customer with Account No " + accountNo + " was deleted successfully!");
        response.sendRedirect("system?action=listCustomers");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        itemService.deleteItem(itemId);
        HttpSession session = request.getSession();
        session.setAttribute("successMessage", "Item with ID " + itemId + " was deleted successfully!");
        response.sendRedirect("system?action=listItems");
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }
    
    private void showReports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bill> allBills = billDAO.getAllBills();
        request.setAttribute("allBills", allBills);
        request.getRequestDispatcher("/WEB-INF/views/reports.jsp").forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = new Customer(accountNo, name, address, phone, unitsConsumed, username, password);
        customerService.addCustomer(customer);
    }
    
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = new Customer(accountNo, name, address, phone, unitsConsumed, username, password);
        customerService.updateCustomer(customer);
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemName = request.getParameter("itemName");
        double price = Double.parseDouble(request.getParameter("price"));
        Item item = new Item(0, itemName, price);
        itemService.addItem(item);
    }
    
    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String itemName = request.getParameter("itemName");
        double price = Double.parseDouble(request.getParameter("price"));
        Item item = new Item(itemId, itemName, price);
        itemService.updateItem(item);
    }

    private void showHelpPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/help.jsp").forward(request, response);
    }

    private void showEditItemForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Item existingItem = itemService.getItem(itemId);
        request.setAttribute("item", existingItem);
        request.getRequestDispatcher("/WEB-INF/views/editItem.jsp").forward(request, response);
    }

    private void showEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        Customer existingCustomer = customerService.getCustomer(accountNo);
        request.setAttribute("customer", existingCustomer);
        request.getRequestDispatcher("/WEB-INF/views/editCustomer.jsp").forward(request, response);
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
}
