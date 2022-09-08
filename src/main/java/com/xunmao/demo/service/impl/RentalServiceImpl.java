package com.xunmao.demo.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.xunmao.demo.dao.InventoryMapper;
import com.xunmao.demo.dao.PaymentMapper;
import com.xunmao.demo.dao.RentalMapper;
import com.xunmao.demo.service.RentalService;

public class RentalServiceImpl implements RentalService {

    private InventoryMapper inventoryMapper;
    private RentalMapper rentalMapper;
    private PaymentMapper paymentMapper;

    public void setInventoryMapper(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    public void setRentalMapper(RentalMapper rentalMapper) {
        this.rentalMapper = rentalMapper;
    }

    public void setPaymentMapper(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @Override
    public void rentDvd(Map<String, Integer> parameterMap) {
        // 1. 检查库存
        int inventoryInStock = inventoryMapper.findInventoryInStock(parameterMap.get("inventoryId"));
        System.out.println("inventoryInStock: " + inventoryInStock);
        if (inventoryInStock == 0) {
            System.out.println("inventoryInStock: 0");
            return;
        }

        // 2. 更新 rental 表
        Map<String, Integer> rentalMap = new HashMap<>();
        rentalMap.put("inventoryId", parameterMap.get("inventoryId"));
        rentalMap.put("customerId", parameterMap.get("customerId"));
        rentalMap.put("staffId", parameterMap.get("staffId"));

        rentalMapper.addRentalWithMap(rentalMap);
        Integer rentalId = rentalMap.get("rentalId");
        System.out.println("rentalId: " + rentalId);

        // 3. 获得费用
        BigDecimal customerBalance = paymentMapper.getCustomerBalance(parameterMap.get("customerId"));
        System.out.println("customerBalance: " + customerBalance);

        // 4. 更新 payment 表
        Map<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("customerId", parameterMap.get("customerId"));
        paymentMap.put("staffId", parameterMap.get("staffId"));
        paymentMap.put("rentalId", rentalId);
        paymentMap.put("amount", customerBalance);
        paymentMapper.addPaymentWithMap(paymentMap);

        Integer paymentId = (Integer) paymentMap.get("paymentId");
        System.out.println("paymentId: " + paymentId);
    }
}
