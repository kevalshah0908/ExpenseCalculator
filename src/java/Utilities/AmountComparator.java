/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import beans.UserBean;
import java.util.Comparator;

/**
 *
 * @author CORPORATE\keval
 */
public class AmountComparator implements Comparator<UserBean>  {

    @Override
    public int compare(UserBean o1, UserBean o2) {
        return Double.compare(o1.getAmountPaid(), o2.getAmountPaid());
    }
    
}
