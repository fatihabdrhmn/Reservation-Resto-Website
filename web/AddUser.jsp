<%@page import="Controller.UserDAO"%>
<jsp:useBean id="user" class="Model.User"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>

<%
    int i = UserDAO.save(user);
    if (i > 0) {
        response.sendRedirect("addUsersuccess.jsp");
    } else {
        response.sendRedirect("addReservationerror.jsp");
    }
%>