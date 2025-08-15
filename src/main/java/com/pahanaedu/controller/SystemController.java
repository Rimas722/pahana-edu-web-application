package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.CustomerServiceImpl;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/system")
public class SystemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerService customerService;
    private ItemService itemService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.customerService = new CustomerServiceImpl();
        this.itemService = new ItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "listCustomers"; 
        }

        switch (action) {
            case "listCustomers":
                listCustomers(request, response);
                break;
            case "listItems":
                listItems(request, response);
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
            default:
                doGet(request, response);
                break;
        }
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
