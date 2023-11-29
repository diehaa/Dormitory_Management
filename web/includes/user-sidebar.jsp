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
                <a class="nav-link" href="news?action=view-dashboard">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>


                <a class="nav-link" href="user?action=view-room">
                    <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                    Booking Room
                </a>
                <a class="nav-link" href="user?action=view-payment">
                    <div class="sb-nav-link-icon"><i class="fas fa-credit-card"></i></div>
                    Payment
                </a>
<!--                <a class="nav-link" href="application?action=view">
                    <div class="sb-nav-link-icon"><i class="fas fa-credit-card"></i></div>
                    Application
                </a>-->


                <a class="nav-link" href="login?action=logout">
                    <div class="sb-nav-link-icon"><i class="fas fa-sign-out-alt"></i></div>
                    Signout
                </a>
            </div>
        </div>

    </nav>
</div>