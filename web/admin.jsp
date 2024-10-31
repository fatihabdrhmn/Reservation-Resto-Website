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
    <title>AdminResto</title>
</head>
<body>
    <!-- header -->
    <header>
        <a href="#" class="Logo"><i class='bx bxs-bowl-hot'></i>Restoku.</a>
        <nav class="navbar">
            <a href="admin.jsp">on process</a>
            <a href="Done.jsp">done</a>
            <a href="User.jsp">user</a>
        </nav>
        <div class="icons">
            <i class="fas fa-bars" id="menu-bars"></i>
            <a href="Restoku.html" class="bx bxs-log-out-circle"></a>
        </div>
    </header>
    
    <%@page import="Controller.ReservationDAO,Model.Reservation,java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <section class="process" id="process">
        <h3 class="sub-heading">on process</h3>
        <h1 class="heading">Processing</h1>
        
        <!-- Form Search -->
        
        <form action="admin.jsp" method="get" class="search-form" style="margin-left: auto; margin-bottom: 2rem;">
            <input type="text" name="search" placeholder="Search by Name, Phone, Email">
            <button type="submit">Search</button>
        </form>
        
        <%
        String search = request.getParameter("search");
        List<Reservation> list;
        if (search != null && !search.trim().isEmpty()) {
            list = ReservationDAO.searchRecords(search);
        } else {
            list = ReservationDAO.getAllRecords();
        }
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
                <th>Edit</th>
                <th>Done</th>
            </tr>
            <c:forEach items="${list}" var="rs">
                <tr>
                    <td data-label="Id">${rs.getId_reservation()}</td>
                    <td data-label="First Name">${rs.getFirst_name()}</td>
                    <td data-label="Last Name">${rs.getLast_name()}</td>
                    <td data-label="Phone">${rs.getPhone()}</td>
                    <td data-label="Email">${rs.getEmail()}</td>
                    <td data-label="People">${rs.getPeople()}</td>
                    <td data-label="Date">${rs.getDate()}</td>
                    <td data-label="Time">${rs.getTime()}</td>
                    <td data-label="Edit"><a href="editReservationform.jsp?id_reservation=${rs.getId_reservation()}"><i class='fas fa-edit' style='font-size:1.5rem'></i></a></td>
                    <td data-label="Done"><a href="moveToDone.jsp?id_reservation=${rs.getId_reservation()}"><i class='fas fa-check' style='font-size:1.5rem'></i></a></td>
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