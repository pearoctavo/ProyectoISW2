<%-- 
    Document   : respuesta
    Created on : 16-sep-2014, 22:26:28
    Author     : Pear Team
--%>

<%@page import="com.umariana.webappsVEAl.mundo.*"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  Tienda tienda = (Tienda) session.getAttribute("tienda");
  
  session.setAttribute("tienda", tienda);
  
  String mensaje = (String) session.getAttribute("mensaje");
  
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alquiler de vehiculos</title>
    </head>
    <body>
        <%=mensaje %><p>
        <%-- <p>las lista hasta el momento de marcas es:<% out.println(miListaMarca);   %> </p> !>--%>
    </body>    
    <a href="index.jsp" target="_top">Inicio</a>    
</html>
