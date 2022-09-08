package com.xunmao.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RentalServiceTest {

    @Test
    public void shouldRentDvd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml", "services.xml");
        RentalService rentalService = context.getBean("rentalService", com.xunmao.demo.service.RentalService.class);
        Map<String, Integer> parameterMap = new HashMap<>();
        parameterMap.put("inventoryId", 11);
        parameterMap.put("customerId", 3);
        parameterMap.put("staffId", 1);
        rentalService.rentDvd(parameterMap);
    }
}
