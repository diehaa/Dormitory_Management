/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.NewsDAO;
import database.RoomDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
@WebServlet(name = "News", urlPatterns = {"/news"})
public class News extends HttpServlet {

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
        }
        else if (action.equals("add-news")) {
            addNews(request, response);
        }else if (action.equals("view")) {
            view(request, response);
        } else if (action.equals("edit")) {
            edit(request, response);
        } else if (action.equals("delete")) {
            delete(request, response);
        } else if (action.equals("news-detail")) {
            newsDetail(request, response);
        }else if (action.equals("edit-news-detail")) {
            editNewsDetail(request, response);
        }else if (action.equals("user-news-detail")) {
            userNewsDetail(request, response);
        }else if (action.equals("view-dashboard")) {
            viewDashboard(request, response);
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

    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String adminId_raw = request.getParameter("adminId");
        int adminId = Integer.parseInt(adminId_raw);
        String url = "";
        NewsDAO newsDAO = new NewsDAO();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Date ngayTao = new Date(date.getTime());
        Random rd = new Random();
        int newsId = (int) System.currentTimeMillis() + rd.nextInt(100);
        model.Admin a1 = new model.Admin();
        a1.setAdminId(adminId);
        model.News kh = new model.News(newsId, title, content, a1, ngayTao);
        newsDAO.insert(kh);

        response.sendRedirect("news?action=view");
    }
    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newsId_raw = request.getParameter("newsId");
        int newsId = Integer.parseInt(newsId_raw);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String adminId_raw = request.getParameter("adminId");
        int adminId = Integer.parseInt(adminId_raw);
        String url = "";
        NewsDAO newsDAO = new NewsDAO();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Date ngayUpdate = new Date(date.getTime());
        
        model.Admin a1 = new model.Admin();
        a1.setAdminId(adminId);
        model.News kh = new model.News(newsId, title, content, a1, ngayUpdate);
        newsDAO.update(kh);

        response.sendRedirect("news?action=view");
    }

    protected void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        ArrayList<model.News> newsList = newsDAO.getList();
        request.setAttribute("data", newsList);
        request.getRequestDispatcher("admin-news-list.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        NewsDAO newsDAO = new NewsDAO();
        newsDAO.delete(newsId);
        response.sendRedirect("news?action=view");
    }

    protected void newsDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        NewsDAO newsDAO = new NewsDAO();
        model.News newsDetail = newsDAO.getNewsById(newsId);
        request.setAttribute("data", newsDetail);
        request.getRequestDispatcher("admin-news-detail.jsp").forward(request, response);
    }
    protected void userNewsDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        NewsDAO newsDAO = new NewsDAO();
        model.News newsDetail = newsDAO.getNewsById(newsId);
        request.setAttribute("data", newsDetail);
        request.getRequestDispatcher("user-news-detail.jsp").forward(request, response);
    }
    protected void viewDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        ArrayList<model.News> newsList = newsDAO.getList();
        request.setAttribute("data", newsList);
        request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
    }
    protected void addNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin-news.jsp").forward(request, response);
    }
    protected void editNewsDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        NewsDAO newsDAO = new NewsDAO();
        model.News newsDetail = newsDAO.getNewsById(newsId);
        request.setAttribute("data", newsDetail);
        request.getRequestDispatcher("admin-news-edit.jsp").forward(request, response);
    }

}
