package com.pahanaedu.service;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.dao.ItemDAOImpl;
import com.pahanaedu.model.Item;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    @Override
    public Item getItem(int itemId) {
        return itemDAO.getItemById(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Override
    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    @Override
    public void deleteItem(int itemId) {
        itemDAO.deleteItem(itemId);
    }
}
