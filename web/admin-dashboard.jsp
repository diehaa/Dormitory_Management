<%-- 
    Document   : dashboard
    Created on : 1 thg 7, 2023, 09:34:40
    Author     : diemhang
--%>

<%@page import="model.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard | Dormitory Management </title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <c:if test="${sessionScope.adminAuth!=null}">
       
    <body class="sb-nav-fixed">
        <%@include file="includes/navbar.jsp" %>
        <div id="layoutSidenav">
            <%@include file="includes/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="bg-warning">
                        <h2 class="text-center align-middle ">DORMITORY MANAGEMENT</h2>
                        <h3 class="text-center align-middle ">FPT EDUCATION</h3>
                    </div>
                    <div class="row">

                        <div class="col-md-12">
                            <img class=" fixed-bottom align-items-center" src="img/bg-desktop.png" width="100%" height="" alt="alt"/>
                        </div>
                    </div>

                </main>
                <!-- foooter -->
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
        </c:if>
    <c:if test="${sessionScope.adminAuth==null}">
        <div class="alert alert-danger container mt-4" role="alert">
            <h2>You are not logged into the system!</h2>

            <a href="login.jsp">Login in here!</a>
        </div>
    </c:if>

</html>
