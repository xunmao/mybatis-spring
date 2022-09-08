package com.xunmao.demo.dao;

import java.math.BigDecimal;
import java.util.Map;

public interface PaymentMapper {

    public BigDecimal getCustomerBalance(Integer customerId);

    public void addPaymentWithMap(Map<String, Object> parameterMap);
}
