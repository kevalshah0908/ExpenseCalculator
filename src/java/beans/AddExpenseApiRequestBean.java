/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author CORPORATE\keval
 */
public class AddExpenseApiRequestBean {
    @JsonProperty("expenses")
    private List<ExpenseBean> expenses = null;

    public List<ExpenseBean> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseBean> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "AddExpenseApiRequestBean{" + "expenses=" + expenses + '}';
    }
}
