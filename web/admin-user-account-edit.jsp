<%-- 
    Document   : user-account-edit
    Created on : 1 thg 7, 2023, 09:38:12
    Author     : diemhang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard | User Account Edit</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script defer src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
    </head>
    <c:if test="${sessionScope.adminAuth!=null}">

        <body class="sb-nav-fixed">
            <%@include file="includes/navbar.jsp" %>
            <div id="layoutSidenav">
                <%@include file="includes/sidebar.jsp" %>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <c:set var="user" value="${requestScope.data}"/>

                            <h2 class="mt-4">Update Information for User ${user.username}</h2>

                            <form method="post" action="admin">
                                <div class="card-body">
                                    <input type="hidden" name="action" value="edit-users"/>
                                    <table class="table table-bordered">
                                        <tr>
                                            <th>User ID</th>
                                            <td><input readonly="" class="form-control" id="usersId" name="usersId" type="text" value="${user.usersId}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Username</th>
                                            <td><input readonly class="form-control" id="username" name="username" type="text" value="${user.username}" required /></td>
                                        </tr>

                                        <tr>
                                            <th>Name</th>
                                            <td><input class="form-control" id="name" name="name" type="text" value="${user.name}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Email</th>
                                            <td><input class="form-control" id="email" name="email" type="text" value="${user.email}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Phone</th>
                                            <td><input class="form-control" id="phone" name="phone" type="text" value="${user.phone}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Parent Name</th>
                                            <td><input class="form-control" id="parentName" name="parentName" type="text" value="${user.parentName}" required /></td>
                                        </tr>
                                        <tr>
                                            <th>Parent Phone</th>
                                            <td><input class="form-control" id="parentPhone" name="parentPhone" type="text" value="${user.parentPhone}" required /></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align:center ;"><button type="submit" class="btn btn-primary btn-block">Update</button></td>
                                        </tr>

                                    </table>
                                </div>
                            </form>
                        </div>
                    </main>
                    <!-- footer -->
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
            <script>
                $(document).ready(function () {
                    // Initialize DataTables
                    $('#example').DataTable({
                        search: {
                            return: false,
                        },
                        lengthMenu: [
                            [10, 25, 50, -1],
                            [10, 25, 50, 'All'],
                        ],
                        order: [[3, 'desc']],
                    });
                });

                function doDelete(userId) {
                    if (confirm('Are you sure you want to delete this user?')) {
                        window.location.href = 'admin?action=delete-users&usersId=' + userId;
                    }
                }
            </script>
        </body>
    </c:if>
    <c:if test="${sessionScope.adminAuth==null}">
        <div class="alert alert-danger container mt-4" role="alert">
            <h2>You are not logged into the system!</h2>

            <a href="login.jsp">Login in here!</a>
        </div>
    </c:if>
</html>