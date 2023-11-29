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
        <title>Account Manage | Dormitory Management </title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script defer src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
    </head>
    <c:if test="${sessionScope.adminAuth!=null}">

        <body class="sb-nav-fixed">
            <%
                String error = request.getAttribute("error") + "";
                error = (error.equals("null")) ? "" : error;

                String username = request.getAttribute("username") + "";
                username = (username.equals("null")) ? "" : username;
                String name = request.getAttribute("name") + "";
                name = (name.equals("null")) ? "" : name;
                String email = request.getAttribute("email ") + "";
                email = (email.equals("null")) ? "" : email;
                String role = request.getAttribute("role") + "";
                role = (role.equals("null")) ? "" : role;
                String phone = request.getAttribute("phone") + "";
                phone = (phone.equals("null")) ? "" : phone;
            %>
            <%@include file="includes/navbar.jsp" %>
            <div id="layoutSidenav">
                <%@include file="includes/sidebar.jsp" %>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <button type="button" class="btn btn-primary mt-4 mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                Add New Account
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Add New Account</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="admin" method="post">
                                                <input type="hidden" name="action" value="add-account"/>


                                                <!-- CCCD input -->
                                                <div class="form-floating mb-4">
                                                    <input type="text" id="username" class="form-control" name="username" placeholder="Username" required="" value="<%=username%>"/>
                                                    <label class="form-label" for="username">Username</label>
                                                </div>

                                                <!-- Password input -->
                                                <div class="form-floating mb-4">
                                                    <input type="password" id="password" class="form-control" name="password" placeholder="Password" required=""/>
                                                    <label class="form-label" for="password">Password</label>
                                                </div>
                                                <div class="form-floating mb-4">
                                                    <input type="password" id="repassword" class="form-control" name="repassword" placeholder="Re-enter Password" required=""onkeyup="kiemTraMatKhau()"/>
                                                    <label class="form-label" for="repassword">Re-enter Password</label>
                                                    <span id ="thongBaoLoiMatKhau" style="color: red"></span>
                                                </div>
                                                <!--                                    Infomation-->
                                                <div class="form-floating mb-4">
                                                    <input type="text" id="hoVaTen" class="form-control" name="name" required="" placeholder="Name" value="<%=name%>"/>
                                                    <label class="form-label" for="hoVaTen">Name</label>
                                                </div>
                                                <div class="form-floating mb-4">
                                                    <input type="email" id="email" class="form-control" name="email" required="" placeholder="Email"  value="<%=email%>"/>
                                                    <label class="form-label" for="email">Email</label>
                                                </div>
                                                <div class="form-floating mb-4">
                                                    <select class="form-select" aria-label="Default select example" name="role"required>
                                                        <option value="Ban Quản Lý" <% if (role.equals("Ban Quản Lý")) {
                                                                out.print("selected");
                                                            } %> >Ban Quản Lý</option>
                                                        <option value="Bảo vệ" <% if (role.equals("Bảo vệ")) {
                                                                out.print("selected");
                                                            }%>>Bảo vệ</option>


                                                    </select>
                                                    <label class="form-label" for="role">Role</label>
                                                </div>
                                                <div class="form-floating mb-4">
                                                    <input type="text" id="phone" class="form-control" placeholder="Phone" name="phone" required="" value="<%=phone%>"/>
                                                    <label class="form-label" for="phone">Phone</label>
                                                </div>

                                                <div class="alert alert-danger" role="alert">
                                                    <%=error%>
                                                </div>
                                                <!-- Submit button -->
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Save</button>
                                                </div>




                                            </form>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Tại tài khoản -->
                            <%=error%>


                            <table id="example" class="table table-striped table-bordered" style="width:100%">
                                <thead class="table" style="background-color: #f27124; color: white" >

                                <th>Username</th>
                                <th>FullName</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Phone</th>
                                <!--                            <th>Avatar</th>-->

                                <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int no = 1;
                                    %>
                                    <c:forEach items = "${requestScope.data}" var="c">
                                        <tr class="font-chu-nho">

                                            <td><a href="admin?action=view-account-detail&adminId=${c.adminId}">${c.username}</a></td>
                                            <td>${c.name}</td>
                                            <td>${c.email}</td>
                                            <td>${c.role}</td>
                                            <td>${c.phone}</td>
    <!--                                        <td><img src="${c.avatar}" height="100px" alt="alt"/></td>-->
                                            <td>
                                                <a class="btn btn-danger" href="#" onclick="doDelete('${c.adminId}', '${c.username}')" role="button">Delete</a>
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
                                                    function doDelete(adminId, username) {
                                                        if (confirm("Do you want delete " + username + " ?")) {
                                                            window.location = "admin?action=delete-account&adminId=" + adminId;
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
    <c:if test="${sessionScope.adminAuth==null}">
        <div class="alert alert-danger container mt-4" role="alert">
            <h2>You are not logged into the system!</h2>

            <a href="login.jsp">Login in here!</a>
        </div>
    </c:if>

</html>
