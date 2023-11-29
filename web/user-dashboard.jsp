<%-- 
    Document   : dashboard
    Created on : 1 thg 7, 2023, 09:34:40
    Author     : phangiabao
--%>

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
    <c:if test="${sessionScope.userAuth!=null}">

        <body class="sb-nav-fixed">
            <%@include file="includes/user-navbar.jsp" %>
            <div id="layoutSidenav">
                <%@include file="includes/user-sidebar.jsp" %>
                <div id="layoutSidenav_content">
                    <main>

                        <div class="container-fluid px-4 mt-4">
                            <h2><i class="fas fa-bell"></i> Notification</h2>

                            <div class="">
                                <table id="example" class="table table-striped table-bordered" style="width:100%">
                                    <thead class="table" style="background-color: #f27124; color: white" >

                                    <th>Title</th>
                                    <th>Post By</th>
                                    <th>Date</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%                                    int no = 1;
                                        %>
                                        <c:forEach items = "${requestScope.data}" var="c">
                                            <tr class="font-chu-nho">

                                                <td><i class="fas fa-newspaper"></i> <a style="text-decoration: none" href="news?action=user-news-detail&newsId=${c.newsId}">${c.title}</a></td>
                                                <td>${c.adminId.username}</td>
                                                <td>${c.timeCreate}</td>



                                            </tr>
                                        </c:forEach>



                                    </tbody>

                                </table>

                            </div>

                        </div>
                    </main>
                    <!-- foooter -->
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>        <script>

                $(document).ready(function () {

                    $('#example').DataTable({
                        search: {
                            return: false,
                        },
                        lengthMenu: [
                            [10, 25, 50, -1],
                            [10, 25, 50, 'All'],
                        ],
                        order: [[2, 'desc']],
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
