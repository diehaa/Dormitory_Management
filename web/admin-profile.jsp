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
                        <div class="container-fluid px-4">
                            <div class="panel-body inf-content mt-4">
                                <div class="row">
                                    <div class="col-md-4">
                                        <img alt="" style="width:600px;" title="" class="img-circle img-thumbnail isTooltip" src="https://upload.wikimedia.org/wikipedia/vi/1/1d/Logo_%C4%90%E1%BA%A1i_h%E1%BB%8Dc_FPT.png" data-original-title="Usuario"> 
                                        <ul title="Ratings" class="list-inline ratings text-center">
                                            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                                            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                                            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                                            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                                            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-6">
                                        <c:set var="c" value="${sessionScope.adminAuth}"/>
                                        <h3>ACCOUNT INFORMATION</h3><br>
                                        <div class="table-responsive">
                                            <table class="table table-user-information">
                                                <tbody>
                                                    <tr>        
                                                        <td>
                                                            <strong>
                                                                <span class="glyphicon glyphicon-asterisk text-primary"></span>
                                                                Identifier in the database                                              
                                                            </strong>
                                                        </td>
                                                        <td class="text-primary">
                                                            ${c.adminId}    
                                                        </td>
                                                    </tr>
                                                    <tr>    
                                                        <td>
                                                            <strong>
                                                                <span class="glyphicon glyphicon-user  text-primary"></span>    
                                                                Username                                               
                                                            </strong>
                                                        </td>
                                                        <td class="text-primary">
                                                            ${c.username}      
                                                        </td>
                                                    </tr>
                                                    <tr>        
                                                        <td>
                                                            <strong>
                                                                <span class="glyphicon glyphicon-cloud text-primary"></span>  
                                                                Name                                               
                                                            </strong>
                                                        </td>
                                                        <td class="text-primary">
                                                            ${c.name}    
                                                        </td>
                                                    </tr>

                                                    <tr>        
                                                        <td>
                                                            <strong>
                                                                <span class="glyphicon glyphicon-bookmark text-primary"></span> 
                                                                Email                                                
                                                            </strong>
                                                        </td>
                                                        <td class="text-primary">
                                                            ${c.email}   
                                                        </td>
                                                    </tr>


                                                    <tr>        
                                                        <td>
                                                            <strong>
                                                                <span class="glyphicon glyphicon-eye-open text-primary"></span> 
                                                                Phone Number                                               
                                                            </strong>
                                                        </td>
                                                        <td class="text-primary">
                                                            ${c.phone}  
                                                        </td>
                                                    </tr>
                                                    <tr>        
                                                        <td>
                                                            <strong>
                                                                <span class="glyphicon glyphicon-eye-open text-primary"></span> 
                                                                Role                                              
                                                            </strong>
                                                        </td>
                                                        <td class="text-primary">
                                                            ${c.role}  
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
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
