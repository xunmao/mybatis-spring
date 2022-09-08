package com.xunmao.demo.pojo;

import java.math.BigDecimal;
import java.util.Date;

// mysql> desc payment;
// +--------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | Field        | Type              | Null | Key | Default           | Extra                                         |
// +--------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | payment_id   | smallint unsigned | NO   | PRI | NULL              | auto_increment                                |
// | customer_id  | smallint unsigned | NO   | MUL | NULL              |                                               |
// | staff_id     | tinyint unsigned  | NO   | MUL | NULL              |                                               |
// | rental_id    | int               | YES  | MUL | NULL              |                                               |
// | amount       | decimal(5,2)      | NO   |     | NULL              |                                               |
// | payment_date | datetime          | NO   |     | NULL              |                                               |
// | last_update  | timestamp         | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
// +--------------+-------------------+------+-----+-------------------+-----------------------------------------------+

public class Payment {

    private int paymentId;
    private int customerId;
    private int staffId;
    private int rentalId;
    private BigDecimal amount;
    private Date paymentDate;
    private Date lastUpdate;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Payment [amount=" + amount + ", customerId=" + customerId + ", lastUpdate=" + lastUpdate
                + ", paymentDate=" + paymentDate + ", paymentId=" + paymentId + ", rentalId=" + rentalId + ", staffId="
                + staffId + "]";
    }
}
