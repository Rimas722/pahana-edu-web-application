package com.pahanaedu.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.ItemServiceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceTest {

    private ItemService itemService;
    private ItemDAO mockItemDAO;

    @Before
    public void setUp() throws Exception {
        mockItemDAO = mock(ItemDAO.class);

        itemService = new ItemServiceImpl();

        Field daoField = ItemServiceImpl.class.getDeclaredField("itemDAO");
        daoField.setAccessible(true);
        daoField.set(itemService, mockItemDAO);
    }

    @Test
    public void testAddItem() {
        Item item = new Item(1, "Test Book", 250.00);

        itemService.addItem(item);

        verify(mockItemDAO, times(1)).addItem(item);
    }

    @Test
    public void testGetAllItems() {
        List<Item> sampleItems = new ArrayList<>();
        sampleItems.add(new Item(1, "Book A", 250.00));
        sampleItems.add(new Item(2, "Pen B", 20.00));
        
        when(mockItemDAO.getAllItems()).thenReturn(sampleItems);
                List<Item> result = itemService.getAllItems();

        assertNotNull("The returned list should not be null", result);
        assertEquals("The list should contain 2 items", 2, result.size());
    }
}
