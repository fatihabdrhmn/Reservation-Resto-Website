<%@page import="Controller.DoneDAO"%>
<%@page import="java.io.*"%>
<%
    String idStr = request.getParameter("id_reservation");
    int id = Integer.parseInt(idStr);
    int status = DoneDAO.moveToDone(id);

    if (status > 0) {
        response.sendRedirect("admin.jsp");
    } else {
        out.println("Failed to move the reservation.");
    }
%>
