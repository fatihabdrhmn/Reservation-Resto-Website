<%@page import="Controller.ReservationDAO"%>
<jsp:useBean id="rs" class="Model.Reservation"></jsp:useBean>
<jsp:setProperty property="*" name="rs"/>

<%
    int i = ReservationDAO.save(rs);
    if (i > 0) {
        response.sendRedirect("addReservationsuccess.jsp");
    } else {
        response.sendRedirect("addReservationerror.jsp");
    }
%>