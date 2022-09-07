package com.xunmao.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RentalMapperTest {

    @Test
    public void shouldAddRentalWithMap() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");
        RentalMapper rentalMapper = context.getBean("rentalMapper", com.xunmao.demo.dao.RentalMapper.class);
        Map<String, Integer> parameterMap = new HashMap<>();
        parameterMap.put("inventoryId", 10);
        parameterMap.put("customerId", 3);
        parameterMap.put("staffId", 1);
        rentalMapper.addRentalWithMap(parameterMap);
    }
}
