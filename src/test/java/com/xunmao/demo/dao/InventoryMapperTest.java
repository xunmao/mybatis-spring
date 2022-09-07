package com.xunmao.demo.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InventoryMapperTest {

    @Test
    public void shouldFindInventoryInStock() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");
        InventoryMapper inventoryMapper = context.getBean("inventoryMapper", com.xunmao.demo.dao.InventoryMapper.class);
        int inventoryInStock = inventoryMapper.findInventoryInStock(10);
        System.out.println("inventoryInStock: " + inventoryInStock);
    }
}
