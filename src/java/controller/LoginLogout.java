/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.AdminDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.MaHoa;

/**
 *
 * @author phangiabao
 */
@WebServlet(name = "LoginLogout", urlPatterns = {"/login"})
public class LoginLogout extends HttpServlet {

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
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
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
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = MaHoa.toSHA1(password);

        final model.Admin admin = new model.Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        final AdminDAO tkad = new AdminDAO();
        final model.Admin adminAuth = tkad.selectByUserAndPassword(admin);

        final model.Users user = new model.Users();
        user.setUsername(username);
        user.setPassword(password);
        final UserDAO tkuser = new UserDAO();
        final model.Users userAuth = tkuser.selectByUserAndPassword(user);

        String url = "";
        if (adminAuth != null) {
            final HttpSession session = request.getSession();
            session.setAttribute("adminAuth", (Object) adminAuth);
            url = "/admin-dashboard.jsp";
        } else if (userAuth != null) {
            final HttpSession session = request.getSession();
            session.setAttribute("userAuth", (Object) userAuth);
            url = "/news?action=view-dashboard";
        } else {
            request.setAttribute("error", (Object) "T\u00ean \u0111\u0103ng nh\u1eadp ho\u1eb7c m\u1eadt kh\u1ea9u kh\u00f4ng \u0111\u00fang!");
            url = "/login.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //huỷ bỏ ssession
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}
