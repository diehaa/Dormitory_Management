<%-- 
    Document   : account
    Created on : 1 thg 7, 2023, 09:38:12
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
                            <c:set var="c" value="${sessionScope.userAuth}"/>
                            <c:set var="d" value="${requestScope.data}"/>

                            <h2 class="mt-4">Confirm payment for the transaction ${d.roomId}</h2>

                            <form method="post" action="user">
                                <div class="card-body">
                                    <input type="hidden" name="action" value="add-booking"/>
                                    <table class="table table-bordered">

                                        <tr>
                                            <th>User ID</th>
                                            <td><input readonly="" class="form-control" id="usersId" name="usersId" type="text" value="${c.usersId}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Room ID</th>
                                            <td><input readonly class="form-control" id="roomId" name="roomId" type="text" value="${d.roomId}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Semester</th>
                                            <td>
                                                <select class="form-select" aria-label="Default select example" name="semester" required="">
                                                    <option selected>Choose Semester</option>
                                                    <option value="Summer 2023">Summer 2023</option>
                                                    <option value="Fall 2023">Fall 2023</option>
                                                    <option value="Spring 2024">Spring 2024</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Total</th>
                                            <td><input readonly class="form-control" id="total" name="total" type="text" value="${d.price}" required /></td>
                                        </tr>

                                        <tr>
                                            <td colspan="4" style="text-align:center ;"><button type="submit" class="btn btn-primary btn-block">Confirm</button></td>

                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>

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
                        order: [[3, 'des']],
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