package com.pahanaedu.dao;

import com.pahanaedu.model.Item;
import java.util.List;

public interface ItemDAO {
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(int itemId);
    Item getItemById(int itemId);
    List<Item> getAllItems();
}
