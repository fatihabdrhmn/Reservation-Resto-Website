<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Link to cdnjs -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    
    <!-- Link to swiper from cdn -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    
    <!-- Link to sweetalert2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    
    <!-- Box icon -->
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    
    <!-- Link to custom CSS -->
    <link rel="stylesheet" href="CSS/admin.css">
    <title>Admin Management</title>
</head>
<body>
    <!-- header -->
    <header>
        <a href="#" class="Logo"><i class='bx bxs-bowl-hot'></i>Restoku.</a>
        <nav class="navbar">
            <a href="admin.jsp">On Process</a>
            <a href="Done.jsp">Done</a>
            <a href="User.jsp">User</a>
        </nav>
        <div class="icons">
            <i class="fas fa-bars" id="menu-bars"></i>
            <a href="Restoku.html" class="bx bxs-log-out-circle"></a>
        </div>
    </header>

    <%@page import="Controller.UserDAO,Model.User,java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <section class="admin-management" id="admin-management">
        <h3 class="sub-heading">Admin Management</h3>
        <h1 class="heading">Manage Admin Accounts</h1>

        <%
        List<User> list = UserDAO.getAllUsers();
        request.setAttribute("list", list);
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${list}" var="admin">
                <tr>
                    <td>${admin.getId()}</td>
                    <td>${admin.getUsername()}</td>
                    <td>${admin.getPassword()}</td>
                    <td>
                        <a href="deleteUser.jsp?id=${admin.getId()}" class="delete-btn"><i class='fas fa-trash-alt'></i> Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="AddUserform.jsp" target="target"></a>
    </section>
        <a href="AddUserform.jsp" class="btn"><i class="fas fa-user-plus"></i> Add New User</a>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="JS/Restoku.js"></script>
</body>
</html>
