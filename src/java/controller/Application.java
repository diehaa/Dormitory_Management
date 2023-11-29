/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.ApplicationDAO;
import database.NewsDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Users;

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "Application", urlPatterns = {"/application"})
public class Application extends HttpServlet {

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
        if (action.equals("view")) {
            view(request, response);
        }  else if (action.equals("view-to-add")) {
            viewToAdd(request, response);
        }  else if (action.equals("add")) {
            add(request, response);
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

    

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationId = request.getParameter("applicationId");
        ApplicationDAO adao = new ApplicationDAO();
        adao.delete(applicationId);
        response.sendRedirect("application?action=view");
    }
    protected void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userAuth");
        Users users = (Users) obj;
        int userId = users.getUsersId();

        ApplicationDAO applicationDAO = new ApplicationDAO();
        ArrayList<model.Application> list = applicationDAO.getListByUser(userId);
        request.setAttribute("data", list);
        request.getRequestDispatcher("user-application-list.jsp").forward(request, response);
    }
    protected void viewToAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-application.jsp").forward(request, response);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usersId_raw = (String) request.getParameter("userId");
        String title = request.getParameter("title");
        String reason = request.getParameter("reason");
        String file = request.getParameter("file");
        String status = request.getParameter("status");
        
        String comment = "";
        int usersId = Integer.parseInt(usersId_raw);
        String roomId_raw = request.getParameter("roomId");
        int roomId = Integer.parseInt(roomId_raw);
        String url = "";
        ApplicationDAO applicationDAO = new ApplicationDAO();

        Random rd = new Random();
        int applicationId = (int) System.currentTimeMillis() + rd.nextInt(100);
        model.Users u1 = new model.Users();
        u1.setUsersId(usersId);
        model.Room r1 = new model.Room();
        r1.setRoomId(roomId);

        //xử lý upload file
        String folderName = "img";
        String uploadPath = "/Users/phangiabao/NetBeansProjects/PRJ301_FPT/AssignmentPRJ301/web/img/img";//for netbeans use this code
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Part filePart = request.getPart("file");
        String fileName = applicationId + "-" + usersId;
        String path = folderName + File.separator + fileName;
        System.out.println("Path: " + uploadPath);
        InputStream is = filePart.getInputStream();
        Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);

        model.Application kh = new model.Application(applicationId, u1, r1, title, reason, path, status, comment);
        applicationDAO.insert(kh);

        response.sendRedirect("application?action=view");
    }
}
