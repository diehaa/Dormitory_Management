<%-- 
    Document   : sidebar
    Created on : 1 thg 7, 2023, 09:01:01
    Author     : phangiabao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" href="admin-dashboard.jsp">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>


                <a class="nav-link" href="admin?action=view-account">
                    <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                    Manage Staff/Account
                </a>
                <a class="nav-link" href="admin?action=view-users">
                    <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                    Manage Users
                </a>

                <a class="nav-link" href="room?action=view">
                    <div class="sb-nav-link-icon"><i class="fas fa-calendar"></i></div>
                    Manage Rooms
                </a>
                <a class="nav-link" href="payment?action=view-payment">
                    <div class="sb-nav-link-icon"><i class="fas fa-credit-card"></i></div>
                    Manage payment
                </a>
                <a class="nav-link" href="news?action=view">
                    <div class="sb-nav-link-icon"><i class="fa fa-calendar"></i></div>
                    Manage News
                </a>

                <a class="nav-link" href="login?action=logout">
                    <div class="sb-nav-link-icon"><i class="fas fa-sign-out-alt"></i></div>
                    Signout
                </a>


            </div>

        </div>

    </nav>
</div>