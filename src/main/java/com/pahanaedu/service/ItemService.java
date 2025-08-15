package com.pahanaedu.service;

import com.pahanaedu.model.Item;
import java.util.List;

public interface ItemService {
    void addItem(Item item);
    Item getItem(int itemId);
    List<Item> getAllItems();
    void updateItem(Item item);
    void deleteItem(int itemId);
}
