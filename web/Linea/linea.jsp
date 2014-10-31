<%-- 
    Document   : linea
    Created on : 16-sep-2014, 8:23:41
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.webappsVEAl.mundo.Marca"%>
<%@page import="com.umariana.webappsVEAl.mundo.Tienda"%>
<%@page import="com.umariana.webappsVEAl.mundo.Linea"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%  Tienda tienda = (Tienda) session.getAttribute("tienda"); 
    
    if (tienda == null)
        tienda = new Tienda();
    
    session.setAttribute("tienda", tienda);
    
    ArrayList<Marca> marcas = tienda.darListaMarcas();
    //ArrayList<Linea> lineas = tienda..darListaLineas();
    pageContext.setAttribute("marcasJstl", marcas);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alquiler de Vehiculos</title>
    </head>
    <body>
      <table border="1" align="center">
            <thead>
                <tr>
                    <th colspan="7" align="center"><h1>Linea</h1></th>
                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td align="center"><a href="../index.jsp" target="_top">Inicio</a></td>
                    <td align="center"><a href="../Vehiculo/vehiculo.jsp" target="_top">Vehiculo</a></td>
                    <td align="center"><a href="../Marca/marca.jsp" target="_top">Marca</a></td>
                    <td align="center"><a href="linea.jsp" target="_top">Linea</a></td>
                    <td align="center"><a href="../ciudad/ciudad.jsp" target="_top">Ciudad</a></td>
                    <td align="center"><a href="../cliente/cliente.jsp" target="_top">Cliente</a></td>
                    <td align="center"><a href="../alquiler/alquiler.jsp" target="_top">Alquiler</a></td>
                </tr>
                 <tr>    
                    <td colspan="3" bgcolor="yellow"><!TABLA DE ADICIONA LINEA!>
                        <form name="frmAdicionarLinea" action="../ControladorLinea" method="POST">
                            <table border="0" bgcolor="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">ADICIONAR</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Marca</td>
                                        <td colspan="2">
                                            <select name="a_linea_marca">                                               
                                                    <option>Seleccione</option>
                                                    <c:forEach var="listaMarcas"  items="${marcasJstl}">
                                                      <option><c:out value="${listaMarcas}"/></option>
                                                    </c:forEach>                                          
                                            </select>  
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nombre Linea</td>
                                        <td colspan="2"><input type="text" name="a_linea_nombre" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" id="Agregar" value="Agregar" name="btnAgregar"/></td>
                                        <td><input type="reset" value="Limpiar" /></td>
                                    </tr>
                                </tbody>
                            </table><!Finaliza formulario adicionar Linea!>
                        </form>                    
                    </td>
                    <td colspan="4" bgcolor="yellow" >
                        <form name="frmModificarLinea" action="../ControladorLinea" method="POST">
                            <table border="0" bgcolor="yellow"><!TABLA DE MODIFICAR LINEA!>
                                <thead>
                                    <tr>
                                        <th colspan="3">MODIFICAR</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Marca</td>
                                        <td colspan="2">
                                           <select name="m_linea_marca">
                                                <option>seleccione</option>
                                                <%
                                                    for (int i=0; i<marcas.size();i++){
                                                        out.println("<option>"+marcas.get(i).getNombreMarca()+"</option>");
                                                    }
                                                %>                                                 
                                            </select>                                         
                                        </td>                                         
                                    </tr>
                                    <tr>
                                        <td>Linea</td>
                                        <td colspan="2">     
                                            <input type="text" name="m_nombre_linea" value="" />                               
                                        </td>                                       
                                    </tr>
                                    <tr>
                                        <td>Nuevo Nombre</td>
                                        <td colspan="2"><input type="text" name="m_nuevo_nombre" value="" /></td>                                       
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Modificar" name="btnModificar" /></td>
                                        <td><input type="reset" value="Limpiar" /></td>
                                    </tr>
                                </tbody>
                            </table><!Finaliza formulario modificar Linea!>
                        </form>
                    </td>                    
                </tr>
                <tr>                    
                    <td colspan="3"  bgcolor="yellow" aling="center" >
                        <form name="frmBuscarLinea" action="../ControladorLinea" method="POST">
                            <table align="center" BGCOLOR="yellow"><!TABLA DE BUSCAR LINEA!>
                                <thead>
                                    <tr>
                                        <th colspan="3">BUSCAR</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <%--                                            
                                            <td>Marca</td>
                                            <td>
                                            <select name="b_lista_marca">
                                                <option>Seleccione</option>
                                                <%
                                                    for (int i=0; i<marcas.size();i++){
                                                        out.println("<option>"+marcas.get(i).getNombreMarca()+"</option>");
                                                    }
                                                %>  
                                            </select>
                                            </td>
                                        --%>                                        
                                    </tr>
                                    <tr>
                                        <td>Linea</td>
                                        <td><input type="text" name="b_nombre_linea" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right"><input type="submit" value="Buscar" name="btnBuscar"/></td>
                                    </tr>
                                </tbody>
                            </table><!Finaliza formulario buscar Linea!>
                        </form>                        
                    </td>
                    <td colspan="4" bgcolor="yellow">
                        <form name="frmBuscarLinea" action="../ControladorLinea" method="POST">
                            <!TABLA DE ELIMINAR LINEA!>
                            <table border="0" align="center">
                                <thead>
                                    <tr>
                                        <th colspan="3">ELIMINAR</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Marca</td>
                                        <td colspan="2">
                                            <select name="e_lista_marcas">
                                                <option>Seleccione</option>
                                                 <%
                                                    for (int i=0; i<marcas.size();i++){
                                                        out.println("<option>"+marcas.get(i).getNombreMarca()+"</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>                                        
                                    </tr>
                                    <tr>
                                        <td>Linea</td>
                                        <td colspan="2">
                                            <input type="text" name="e_nombre_linea" value="" />
                                        </td>                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Eliminar" name="btnEliminar" /></td>
                                        <td><input type="reset" value="Limpiar" /></td>
                                    </tr>
                                </tbody>
                            </table>

                        </form>
                    </td>                                        
                </tr>
            </tbody>
        </table>
    </body>
</html>
