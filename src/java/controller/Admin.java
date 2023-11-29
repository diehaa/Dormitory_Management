/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.AdminDAO;
import database.RoomDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import model.Users;
import util.MaHoa;

/**
 *
 * @author diemhang
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

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
         if (action.equals("view-account")) {
            viewAccount(request, response);
        } else if (action.equals("view-account-detail")) {
            viewAccountDetail(request, response);
        } else if (action.equals("add-account")) {
            addAccount(request, response);
        } else if (action.equals("edit-account")) {
            editAccount(request, response);
        } else if (action.equals("delete-account")) {
            deleteAccount(request, response);
        } else if (action.equals("view-users")) {
            viewUserAccount(request, response);
        } else if (action.equals("view-users-detail")) {
            viewUserAccountDetail(request, response);
        } else if (action.equals("add-users")) {
            addUserAccount(request, response);
        } else if (action.equals("edit-users")) {
            editUser(request, response);
        } else if (action.equals("delete-users")) {
            deleteUser(request, response);
        } else if (action.equals("change-account-password")) {
            changeAccountPassword(request, response);
        }else if (action.equals("view-profile")) {
            viewProfile(request, response);
        }else if (action.equals("change-password")) {
            changePassword(request, response);
        }else if (action.equals("check-out")) {
            checkOut(request, response);
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

    

    protected void viewAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminDAO admin = new AdminDAO();
        ArrayList<model.Admin> list = admin.getListTaiKhoanAdmin();
        request.setAttribute("data", list);
        request.getRequestDispatcher("admin-account.jsp").forward(request, response);
    }

    protected void addAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");

        request.setAttribute("username", username);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("role", role);
        request.setAttribute("phone", phone);

        String url = "";
        String error = "";
        AdminDAO adminDAO = new AdminDAO();
        if (adminDAO.kiemTraTenDangNhap(username)) {
            error += "Username already exists, please choose another username! <br>";
        }

        if (!password.equals(repassword)) {
            error += "Password does not match!<br>";
        } else {
            password = MaHoa.toSHA1(password);
        }

        request.setAttribute("error", error);

        if (error.length() > 0) {
            url = "/admin?action=view-account";
        } else {
            Random rd = new Random();
            int adminId = (int) System.currentTimeMillis() + rd.nextInt(100);
            model.Admin admin = new model.Admin(adminId, username, password, name, email, role, phone);
            adminDAO.insert(admin);
            url = "/admin?action=view-account";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void viewAccountDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String adminId = request.getParameter("adminId");
        AdminDAO admin = new AdminDAO();
        model.Admin list = admin.getListTaiKhoanAdminByIdString(adminId);
        request.setAttribute("data", list);
        request.getRequestDispatcher("admin-account-edit.jsp").forward(request, response);
    }

    protected void editAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String adminId_raw = request.getParameter("adminId");
        int adminId = Integer.parseInt(adminId_raw);
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");

        AdminDAO adminDAO = new AdminDAO();
        model.Admin admin = new model.Admin(adminId, name, email, role, phone);
        adminDAO.update(admin);
        String url = "/admin?action=view-account";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminId = request.getParameter("adminId");
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.delete(adminId);
        response.sendRedirect("admin?action=view-account");
    }

    private void viewUserAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        ArrayList<Users> userList = userDAO.getListUser();
        request.setAttribute("data", userList);
        request.getRequestDispatcher("admin-user-account.jsp").forward(request, response);
    }

    protected void addUserAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String parentName = request.getParameter("parentName");
        String parentPhone = request.getParameter("parentPhone");

        request.setAttribute("username", username);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("parentName", parentName);
        request.setAttribute("parentPhone", parentPhone);

        String url = "";
        String error = "";
        UserDAO userDAO = new UserDAO();
        if (userDAO.kiemTraTenDangNhap(username)) {
            error += "Username already exists, please choose another username! <br>";
        }

        if (!password.equals(repassword)) {
            error += "Password does not match!<br>";
        } else {
            password = MaHoa.toSHA1(password);
        }

        if (error.length() > 0) {
            request.setAttribute("error", error);
            url = "/admin?action=view-users";
        } else {
            Random rd = new Random();
            int usersId = (int) System.currentTimeMillis() + rd.nextInt(100);
            model.Users user = new model.Users(usersId, username, password, name, email, phone, parentName, parentPhone);
            userDAO.insert(user);
            url = "/admin?action=view-users";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void viewUserAccountDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("usersId"));
        UserDAO userDAO = new UserDAO();
        Users user = new Users();
        user.setUsersId(userId);
        Users userAccount = userDAO.getListTaiKhoanUsersById(user);
        request.setAttribute("data", userAccount);
        request.getRequestDispatcher("admin-user-account-edit.jsp").forward(request, response);
    }

    protected void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usersId_raw = request.getParameter("usersId");
        int userId = Integer.parseInt(usersId_raw);
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String parentName = request.getParameter("parentName");
        String parentPhone = request.getParameter("parentPhone");

        UserDAO userDAO = new UserDAO();
        Users user = new Users(userId, username, name, phone, parentName, parentPhone);
        userDAO.update(user);
        String url = "/admin?action=view-users";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usersId = request.getParameter("usersId");
        UserDAO userDAO = new UserDAO();
        userDAO.delete(usersId);
        response.sendRedirect("admin?action=view-users");
    }

    private void changeAccountPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminId_raw = request.getParameter("adminId");
        int adminId = Integer.parseInt(adminId_raw);
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String renewpassword = request.getParameter("renewpassword");
        oldpassword = MaHoa.toSHA1(oldpassword);

        model.Admin admin = new model.Admin();
        admin.setUsername(username);
        admin.setPassword(oldpassword);
        AdminDAO tkad = new AdminDAO();
        model.Admin xacThucMK = tkad.selectByUserAndPassword(admin);

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
            
            url = "/admin-change-password.jsp";
        } else {
            
            model.Admin admin1 = new model.Admin(adminId, renewpassword);
            tkad.changePassword(admin1);
            error += "Change password successfully!<br>";
            url = "/admin-change-password.jsp";
        }
        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
    protected void viewProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin-profile.jsp").forward(request, response);
    }
    protected void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin-change-password.jsp").forward(request, response);
    }
    protected void checkOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usersId = request.getParameter("usersId");
        String roomId = request.getParameter("roomId");
        UserDAO udao = new UserDAO();
        udao.checkOut(usersId);
        RoomDAO rdao = new RoomDAO();
        rdao.checkOut(roomId);
        response.sendRedirect("admin?action=view-users");
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

