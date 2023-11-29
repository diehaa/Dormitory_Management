<%-- 
    Document   : account
    Created on : 1 thg 7, 2023, 09:38:12
    Author     : diemhang
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
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <!-- include summernote css/js -->
        <script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/classic/ckeditor.js"></script>
    </head>
    <c:if test="${sessionScope.userAuth!=null}">

        <body class="sb-nav-fixed">
            <%@include file="includes/user-navbar.jsp" %>
            <div id="layoutSidenav">
                <%@include file="includes/user-sidebar.jsp" %>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h3 class="mt-4">Add new Application</h3>
                            <div class="card mb-4">
                                <form method="POST" action="application" enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="add"/>
                                    <table class="table table-bordered">
                                        <tr>
                                            <th>Title</th>
                                            <td><textarea required class="form-control" id="title" name="title" style="height: 100px"></textarea></td>
                                        </tr>
                                        <tr>
                                            <th>Reason</th>
                                            <td><textarea class="form-control" name="reason" id="content" style=" min-height: 500px;"></textarea></td>
                                        </tr>
                                        <tr>
                                            <th>File</th>
                                            <td><input class="form-control" type="file" id="file" name="file" accept="image/png,image/jpeg"></td>
                                        </tr>


                                        <tr>
                                            <th>User</th>
                                            <td>
                                                <select class="form-control" name="userId" id="userId">
                                                    <c:set var="c" value="${sessionScope.userAuth}"/>
                                                    <option selected value="${c.usersId}">${c.username}</option>

                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Room</th>
                                            <td>
                                                <select class="form-control" name="roomId" id="roomId">
                                                    <c:set var="c" value="${sessionScope.userAuth}"/>
                                                    <option selected value="${c.roomId}">${c.roomId.name}</option>

                                                </select>
                                            </td>
                                        </tr>
                                        <input hidden type="text" name="status" value="Mới">
                                        <input hidden type="text" name="userId" value="${c.usersId}">


                                        <tr>
                                            <td colspan="4" style="text-align:center ;"><button type="submit" class="btn btn-primary btn-block">Send</button></td>

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
                ClassicEditor
                        .create(document.querySelector('#content'))
                        .then(editor => {
                            console.log(editor);
                        })
                        .catch(error => {
                            console.error(error);
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