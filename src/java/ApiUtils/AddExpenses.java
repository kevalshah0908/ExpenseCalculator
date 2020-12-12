/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiUtils;

import Utilities.AmountComparator;
import beans.AddExpenseApiRequestBean;
import beans.ExpenseBean;
import beans.UserBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author CORPORATE\keval
 */
@WebServlet(name = "AddExpenses", urlPatterns = {"/AddExpenses"})
public class AddExpenses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ServletContext servletContext = getServletContext();
            HashMap<String, UserBean> userMaster = (HashMap<String, UserBean>) servletContext.getAttribute("userMaster");
            String line = null;
            StringBuilder incomingRequest = new StringBuilder();
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                incomingRequest.append(line);
            }
            String requestString = incomingRequest.toString();
            System.out.println("requestString : " + requestString);
            ObjectMapper objMapper = new ObjectMapper();
            AddExpenseApiRequestBean apiRequest = objMapper.readValue(requestString, AddExpenseApiRequestBean.class);
            double totalAmt = 0.00;
            for (ExpenseBean expenseBean : apiRequest.getExpenses()) {
                String splitType = expenseBean.getSplitBy();
                switch (splitType.toUpperCase()) {
                    case "EQUAL":
                        totalAmt += expenseBean.getAmount();
                        UserBean user = userMaster.get(expenseBean.getPaidBy().toLowerCase());
                        user.setAmountPaid(user.getAmountPaid() + expenseBean.getAmount());
                        System.out.println("totalAmt : " + totalAmt +  ", expenseBean.getAmount() : " + expenseBean.getAmount() +", userMaster.size() : " + userMaster.size());
                        
                }
            }
            double perHead = totalAmt / userMaster.size();
            List<UserBean> users = new ArrayList<>();
            for (String userName : userMaster.keySet()) {
                UserBean user = userMaster.get(userName);
                users.add(user);
                user.setAmountPayable(user.getAmountPayable() + perHead);
                System.out.println("User : " + userName + ", " + user.getAmountPaid() + ", " + user.getAmountPayable());
            }
            String result = printResult(userMaster, users, perHead);
            out.write(result);
            
            
        } catch (Exception e) {
            System.out.println("Exception occured in AddExpense : " + e);
        }
    }
    
    private String printResult(HashMap<String, UserBean> userMaster, List<UserBean> users, double perHead) {
        String result = "";
        Collections.sort(users, new AmountComparator());
        for (UserBean user : users) {
            System.out.println("User: " + user);
            if (user.getAmountPaid() > user.getAmountPayable()) {
                result += user.getFirstName() + " gets back " + (user.getAmountPaid() - user.getAmountPayable()) + "\n";
            } else if (user.getAmountPaid() == 0.00 && user.getAmountPayable() != 0.00) {
                result += user.getFirstName() + " owes " + user.getAmountPayable() + "\n";
            } else {
                result += user.getFirstName() + " owes " + (user.getAmountPayable() - user.getAmountPaid()) + "\n";
            }
        }
        return result;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
