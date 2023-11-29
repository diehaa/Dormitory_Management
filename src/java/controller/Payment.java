/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.PaymentDAO;
import database.RoomDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "Payment", urlPatterns = {"/payment"})
public class Payment extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("pay-booking")) {
            payBooking(request, response);
        } else if (action.equals("reset-semester")) {
            resetSemester(request, response);
        } else if (action.equals("view-payment")) {
            viewPayment(request, response);
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

    protected void payBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId_raw = request.getParameter("paymentId");
        int paymentId = Integer.parseInt(paymentId_raw);
        String userId_raw = request.getParameter("userId");
        int userId = Integer.parseInt(userId_raw);
        String roomId_raw = request.getParameter("roomId");
        int roomId = Integer.parseInt(roomId_raw);
        String email = request.getParameter("email");
        PaymentDAO paymentDAO = new PaymentDAO();
        String status = "Thanh toán thành công";
        model.Payment p = new model.Payment(paymentId, status);
        paymentDAO.updatePay(p);
        
        
        model.Room r1 = new model.Room();
        r1.setRoomId(roomId);
        model.Users u = new model.Users(userId, r1);
        UserDAO udao = new UserDAO();
        udao.updateRoom(u);
        
        RoomDAO rdao = new RoomDAO();
        rdao.incrementSlot(roomId_raw);
        String message ="Hello%2C%0D%0AYou+have+successfully+paid+the+dormitory+deposit+on+the+system%0D%0ABest+regards%2C%0D%0AFPT+Education";
        response.sendRedirect("send?email="+email+"&message="+message);

    }

    protected void viewPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PaymentDAO paymentDAO = new PaymentDAO();
        ArrayList<model.Payment> list = paymentDAO.getList();
        request.setAttribute("data", list);
        request.getRequestDispatcher("admin-payment.jsp").forward(request, response);
    }

    protected void resetSemester(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserDAO udao = new UserDAO();
        udao.resetSemester();
        RoomDAO rdao = new RoomDAO();
        rdao.resetSemester();
        response.sendRedirect("room?action=view");
    }
}
