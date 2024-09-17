<%@page import="Controller.UserDAO"%>
<jsp:useBean id="user" class="Model.User"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>

<%
    int i = UserDAO.delete(user);
    response.sendRedirect("deleteUserpop.jsp");
%>