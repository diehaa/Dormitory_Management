/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.AdminDAO;
import database.PaymentDAO;
import database.RoomDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Payment;
import model.Users;
import util.MaHoa;

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "User", urlPatterns = {"/user"})
public class User extends HttpServlet {

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
        if (action.equals("view-profile")) {
            viewProfile(request, response);
        } else if (action.equals("change-password")) {
            changePassword(request, response);
        } else if (action.equals("change-user-password")) {
            changeUserPassword(request, response);
        } else if (action.equals("view-payment")) {
            viewPayment(request, response);
        }else if (action.equals("confirm-payment")) {
            confirmPayment(request, response);
        }else if (action.equals("view-room")) {
            viewRoom(request, response);
        }else if (action.equals("add-booking")) {
            addBooking(request, response);
        }else if (action.equals("delete-payment")) {
            deletePayment(request, response);
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

    protected void viewProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-profile.jsp").forward(request, response);
    }

    protected void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-change-password.jsp").forward(request, response);
    }

    protected void changeUserPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usersId_raw = request.getParameter("usersId");
        int usersId = Integer.parseInt(usersId_raw);
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String renewpassword = request.getParameter("renewpassword");
        oldpassword = MaHoa.toSHA1(oldpassword);

        model.Users users = new model.Users();
        users.setUsername(username);
        users.setPassword(oldpassword);
        UserDAO tkad = new UserDAO();
        model.Users xacThucMK = tkad.selectByUserAndPassword(users);

        String error = "";
        String url = "";
        if (xacThucMK == null) {
            error += "Old Password is incorect!<br>";
        }
        if (!newpassword.equals(renewpassword)) {
            error += "New Password does not match!<br>";
        } else {
            renewpassword = MaHoa.toSHA1(renewpassword);
        }
        if (error.length() > 0) {

            url = "/user-change-password.jsp";
        } else {

            model.Users users1 = new model.Users(usersId, renewpassword);
            tkad.changePassword(users1);
            error += "Change password successfully!<br>";
            url = "/user-change-password.jsp";
        }
        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void viewPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userAuth");
        Users users = (Users) obj;
        int userId = users.getUsersId();

        PaymentDAO paymentDAO = new PaymentDAO();
        ArrayList<model.Payment> list = paymentDAO.getListByUser(userId);
        request.setAttribute("data", list);
        request.getRequestDispatcher("user-payment.jsp").forward(request, response);
    }
    protected void confirmPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        RoomDAO roomDAO = new RoomDAO();
        model.Room roomDetail = roomDAO.getListRoomByIdString(roomId);
        
        request.setAttribute("data", roomDetail);
        request.getRequestDispatcher("user-confirm-payment.jsp").forward(request, response);
    }
    protected void viewRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<model.Room> roomList = roomDAO.getListRoom();
        request.setAttribute("data", roomList);
        request.getRequestDispatcher("user-booking.jsp").forward(request, response);
    }
    protected void addBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usersId_raw = request.getParameter("usersId");
        int usersId = Integer.parseInt(usersId_raw);
        String roomId_raw = request.getParameter("roomId");
        int roomId = Integer.parseInt(roomId_raw);
        String semester = request.getParameter("semester");
        String total_raw = request.getParameter("total");
        int total = Integer.parseInt(total_raw);
        Random rd = new Random();
        int paymentId = (int) System.currentTimeMillis() + rd.nextInt(100);
        model.Users u1 = new model.Users();
        u1.setUsersId(usersId);
        model.Room r1 = new model.Room();
        r1.setRoomId(roomId);
        String status ="Chưa thanh toán";
        model.Payment payment = new Payment(paymentId, u1, r1, semester, total, status);
        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.insert(payment);
        response.sendRedirect("user?action=view-payment");
    }
    protected void deletePayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.delete(paymentId);
        response.sendRedirect("user?action=view-payment");
    }
}
