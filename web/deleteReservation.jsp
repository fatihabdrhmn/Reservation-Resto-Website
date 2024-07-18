<%@page import="Controller.DoneDAO"%>
<jsp:useBean id="d" class="Model.Done"></jsp:useBean>
<jsp:setProperty property="*" name="d"/>

<%
    int i = DoneDAO.delete(d);
    response.sendRedirect("Done.jsp");
%>