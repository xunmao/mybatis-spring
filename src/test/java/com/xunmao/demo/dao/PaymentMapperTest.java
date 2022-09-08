package com.xunmao.demo.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PaymentMapperTest {

    @Test
    public void shouldGetCustomerBalance() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");
        PaymentMapper paymentMapper = context.getBean("paymentMapper", com.xunmao.demo.dao.PaymentMapper.class);
        BigDecimal customerBalance = paymentMapper.getCustomerBalance(3);
        System.out.println("customerBalance: " + customerBalance);
    }

    @Test
    public void shouldAddPaymentWithMap() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");
        PaymentMapper paymentMapper = context.getBean("paymentMapper", com.xunmao.demo.dao.PaymentMapper.class);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("customerId", 3);
        parameterMap.put("staffId", 1);
        parameterMap.put("rentalId", 16054);
        parameterMap.put("amount", 14.97);

        paymentMapper.addPaymentWithMap(parameterMap);
        System.out.println("paymentId: " + parameterMap.get("paymentId"));
    }
}
