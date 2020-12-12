/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiUtils;

import beans.UserBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import javax.servlet.ServletConfig;
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
@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {
    public HashMap<String, UserBean> userMaster = new HashMap<String, UserBean>();
    public HashMap <Integer, HashMap <String, String>> userWiseDetails = new HashMap<Integer, HashMap <String, String>>();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );
        getServletContext().setAttribute("userMaster", userMaster);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder jb = new StringBuilder();
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String line = null;
            JSONObject json = null;
            StringBuilder incomingRequest = new StringBuilder();
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                incomingRequest.append(line);
            }
            String requestString = incomingRequest.toString();
            json = new JSONObject(URLDecoder.decode(incomingRequest.toString(), "UTF-8")); 
            UserBean user = new UserBean();
            user.setUserId(userMaster.size() + 1);
            user.setFirstName(json.getString("FirstName"));
            user.setLastName(json.getString("LastName"));
            user.setContactNo(json.getString("ContactNumber"));
            if (userMaster.containsKey(json.getString("FirstName").toLowerCase())) {
                out.write("User already exists..!!");
            } else {
                userMaster.put(json.getString("FirstName").toLowerCase(), user);
            }
            out.write("User added successfully..!!");
            out.close();
        } catch (Exception e) {
            out.write("Failed to insert User..");
            System.out.println("Exception occured in adding user: " + e);
        }
        
        
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
