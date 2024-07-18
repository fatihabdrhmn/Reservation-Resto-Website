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

    <!-- Link to css -->
    <link rel="stylesheet" href="CSS/admin.css">
    <title>Restoku</title>
</head>
<body>
    <!-- header -->
    <header>
        <a href="#" class="Logo"><i class='bx bxs-bowl-hot'></i>Restoku.</a>
        <nav class="navbar">
            <a href="admin.jsp">on process</a>
            <a href="Done.jsp">done</a>
        </nav>
        <div class="icons">
            <i class="fas fa-bars" id="menu-bars"></i>
            <a href="Restoku.html" class="bx bxs-log-out-circle"></a>
        </div>
    </header>

    <section>
        <h3 class="sub-heading">our menu</h3>
        <h1 class="heading">delicious food</h1>
    <%@page import="Controller.DoneDAO,Model.Done,java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <%
        List<Done> list = DoneDAO.getAllRecordsdone();
        request.setAttribute("list", list);
    %>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>People</th>
            <th>Date</th>
            <th>Time</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${list}" var="d">
            <tr>
                <td data-label="Id">${d.getId_reservation()}</td>
                <td data-label="First Name">${d.getFirst_name()}</td>
                <td data-label="Last Name">${d.getLast_name()}</td>
                <td data-label="Phone">${d.getPhone()}</td>
                <td data-label="Email">${d.getEmail()}</td>
                <td data-label="People">${d.getPeople()}</td>
                <td data-label="Date">${d.getReservation_date()}</td>
                <td data-label="Time">${d.getReservation_time()}</td>
                <td data-label="Edit"><a href="deleteReservation.jsp?id_reservation=${d.getId_reservation()}"><i class='fas fa-trash' style='font-size:1.5rem'></i></a></td>
            </tr>
        </c:forEach>
    </table>
    </section>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="JS/Restoku.js"></script>
</body>
</html>