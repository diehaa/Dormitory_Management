<%-- 
    Document   : account
    Created on : 1 thg 7, 2023, 09:38:12
    Author     : diemhang
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
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
        <script defer src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
    </head>
    <%
        String error = request.getAttribute("error") + "";
        error = (error.equals("null")) ? "" : error;
    %>
    <c:if test="${sessionScope.userAuth!=null}">

        <body class="sb-nav-fixed">
            <%@include file="includes/user-navbar.jsp" %>
            <div id="layoutSidenav">
                <%@include file="includes/user-sidebar.jsp" %>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">


                            <h2 class="mt-4">Booking Room for New Semester</h2>
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                <p><b>You will not be able to make a reservation if:</b></p><p>- You are in a certain room</p><p>- Room registration period has not started</p>

                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>


                            <table id="example" class="table table-striped table-bordered" style="width:100%">
                                <thead class="table" style="background-color: #f27124; color: white" >

                                <th>Name</th>
                                <th>Type</th>
                                <th>Price</th>
                                <th>Slot</th>

                                <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int no = 1;

                                    %>

                                    <c:forEach items = "${requestScope.data}" var="c">
                                        <c:set var="d" value="${sessionScope.userAuth}"/>
                                        <tr class="font-chu-nho">

                                            <td>${c.name}</td>
                                            <td>${c.type}</td>
                                            <td >${c.price}</td>
                                            <td>${c.slot} / ${c.type}</td>

                                            <td>
                                                <a ${(c.slot=='4' && c.type=='4 BEDS' || c.slot=='6' && c.type=='6 BEDS' || d.roomId != null) ?'hidden':''} class="btn btn-primary" href="user?action=confirm-payment&roomId=${c.roomId}" role="button">Book</a>
                                                <p ${(c.slot=='4' && c.type=='4 BEDS' || c.slot=='6' && c.type=='6 BEDS' || d.roomId != null) ?'':'hidden'} style="color: red">Unauthorized</p>
                                            </td>

                                        </tr>
                                    </c:forEach>



                                </tbody>

                            </table>


                        </div>
                    </main>
                    <!-- foooter -->
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
            <script>
                function doDelete(maAdmin, tenDangNhap) {
                    if (confirm("Bạn có muốn xoá " + tenDangNhap + " không?")) {
                        window.location = "tai-khoan?hanhDong=delete&maAdmin=" + maAdmin;
                    }
                }
                $(document).ready(function () {

                    $('#example').DataTable({
                        search: {
                            return: false,
                        },
                        lengthMenu: [
                            [10, 25, 50, -1],
                            [10, 25, 50, 'All'],
                        ],
                        order: [[0, 'asc']],
                    });

                });
            </script>
        </body>
    </c:if>
    <c:if test="${sessionScope.userAuth==null}">
        <div class="alert alert-danger container mt-4" role="alert">
            <h2>You are not logged into the system!</h2>

            <a href="login.jsp">Login in here!</a>
        </div>
    </c:if>
</html>