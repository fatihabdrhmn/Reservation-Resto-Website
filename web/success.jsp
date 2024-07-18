<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@200..1000&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Nunito', sans-serif;
        }
    </style>
</head>
<body>
    <%@include file="admin.jsp" %>
    <script>
        <%
            String status = (String) request.getAttribute("status");
            if ("success".equals(status)) {
        %>
        Swal.fire({
            icon: 'success',
            title: 'Login Successful',
            text: 'Welcome back!'
        });
        <%
            }
        %>
    </script>
    <!-- Your admin page content -->
</body>
</html>
