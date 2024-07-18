<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.9/dist/sweetalert2.all.min.js"></script>
</head>
<body>
    <%@include file="loginForm.jsp" %>
    <script>
        <%
            String status = (String) request.getAttribute("status");
            if ("fail".equals(status)) {
        %>
        Swal.fire({
            icon: 'error',
            title: 'Login Failed',
            text: 'Invalid username or password!'
        });
        <%
            }
        %>
    </script>
    <!-- Your login form content -->
</body>
</html>
