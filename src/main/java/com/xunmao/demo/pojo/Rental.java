package com.xunmao.demo.pojo;

import java.util.Date;

// mysql> desc rental;
// +--------------+--------------------+------+-----+-------------------+-----------------------------------------------+
// | Field        | Type               | Null | Key | Default           | Extra                                         |
// +--------------+--------------------+------+-----+-------------------+-----------------------------------------------+
// | rental_id    | int                | NO   | PRI | NULL              | auto_increment                                |
// | rental_date  | datetime           | NO   | MUL | NULL              |                                               |
// | inventory_id | mediumint unsigned | NO   | MUL | NULL              |                                               |
// | customer_id  | smallint unsigned  | NO   | MUL | NULL              |                                               |
// | return_date  | datetime           | YES  |     | NULL              |                                               |
// | staff_id     | tinyint unsigned   | NO   | MUL | NULL              |                                               |
// | last_update  | timestamp          | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
// +--------------+--------------------+------+-----+-------------------+-----------------------------------------------+

public class Rental {

    private int rentalId;
    private Date rentalDate;
    private int inventoryId;
    private int customerId;
    private Date returnDate;
    private int staffId;
    private Date lastUpdate;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Rental [customerId=" + customerId + ", inventoryId=" + inventoryId + ", lastUpdate=" + lastUpdate
                + ", rentalDate=" + rentalDate + ", rentalId=" + rentalId + ", returnDate=" + returnDate + ", staffId="
                + staffId + "]";
    }
}
