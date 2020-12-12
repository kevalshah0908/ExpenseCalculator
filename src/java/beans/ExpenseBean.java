/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author CORPORATE\keval
 */
public class ExpenseBean {
    @JsonProperty("name")
    private String description;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("paid_by")
    private String paidBy;
    @JsonProperty("split_by")
    private String splitBy;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getSplitBy() {
        return splitBy;
    }

    public void setSplitBy(String splitBy) {
        this.splitBy = splitBy;
    }

    @Override
    public String toString() {
        return "ExpenseBean{" + "description=" + description + ", amount=" + amount + ", paidBy=" + paidBy + ", splitBy=" + splitBy + '}';
    }
}
