package com.jasper_reports.demo.model;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaidEmploye {

    @JsonProperty("voucher_id")
    private String voucherId;
    @JsonProperty("Amount_Paid")
    private BigDecimal amountPaid;
    @JsonProperty("Payment_method")
    private String paymentMethod;
    @JsonProperty("parent_name")
    private String parentName;
    @JsonProperty("child_name")
    private String childName;

    public String getVoucher_id() {
        return voucherId;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucherId = voucher_id;
    }

    public BigDecimal getAmount_Paid() {
        return amountPaid;
    }

    public void setAmount_Paid(BigDecimal amount_Paid) {
        amountPaid = amount_Paid;
    }

    public String getPayment_method() {
        return paymentMethod;
    }

    public void setPayment_method(String payment_method) {
        paymentMethod = payment_method;
    }

    public String getParent_name() {
        return parentName;
    }

    public void setParent_name(String parent_name) {
        this.parentName = parent_name;
    }

    public String getChild_name() {
        return childName;
    }

    public void setChild_name(String child_name) {
        this.childName = child_name;
    }
}
