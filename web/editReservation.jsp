<%@page import="Controller.ReservationDAO"%>
<jsp:useBean id="rs" class="Model.Reservation"></jsp:useBean>
<jsp:setProperty property="*" name="rs"/>

<%
    int i = ReservationDAO.update(rs);
    response.sendRedirect("admin.jsp");
%>