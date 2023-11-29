/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "Room", urlPatterns = {"/room"})
public class Room extends HttpServlet {

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
        if (action.equals("add")) {
            add(request, response);
        } else if (action.equals("view")) {
            view(request, response);
        } else if (action.equals("delete")) {
            delete(request, response);
        }else if (action.equals("room-detail")) {
            roomDetail(request, response);
        }else if (action.equals("user-room-detail")) {
            userRoomDetail(request, response);
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
    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");

        request.setAttribute("name", name);
        request.setAttribute("type", type);

        String error = "";
        String url = "";
        RoomDAO roomDAO = new RoomDAO();
        if (roomDAO.kiemTraNameRoom(name,type)) {
            error += "Room name and type already exists, please choose another one!<br>";
            request.setAttribute("error", error);
            url = "/room?action=view";
        }
        if (error.length() == 0) {
            int price=0;
            if (type.equals("6 BEDS")) {
                price = 700000;
            }
            if (type.equals("4 BEDS")) {
                price = 850000;
            }
            Random rd = new Random();
            int roomId = (int)System.currentTimeMillis() + rd.nextInt(100);
            model.Room kh = new model.Room(roomId, name,type,price);
            roomDAO.insert(kh);
            url = "/room?action=view";
        }

        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
    protected void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<model.Room> roomList = roomDAO.getListRoom();
        request.setAttribute("data", roomList);
        request.getRequestDispatcher("admin-room.jsp").forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.delete(roomId);
        response.sendRedirect("room?action=view");
    }
    protected void roomDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        UserDAO userDAO = new UserDAO();
        ArrayList<model.Users> userList = userDAO.getListUserInRoom(roomId);
        request.setAttribute("data", userList);
        request.getRequestDispatcher("admin-room-detail.jsp").forward(request, response);
    }
    protected void userRoomDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        UserDAO userDAO = new UserDAO();
        ArrayList<model.Users> userList = userDAO.getListUserInRoom(roomId);
        request.setAttribute("data", userList);
        request.getRequestDispatcher("user-room-detail.jsp").forward(request, response);
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
