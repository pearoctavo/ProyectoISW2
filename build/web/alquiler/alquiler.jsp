<%-- 
    Document   : alquiler
    Created on : 20-oct-2014, 17:32:18
    Author     : Pear
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.webappsVEAl.mundo.Tienda"%>
<%@page import="com.umariana.webappsVEAl.mundo.Alquiler"%>
<%@page import="com.umariana.webappsVEAl.mundo.Vehiculo"%>
<%@page import="com.umariana.webappsVEAl.mundo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%  Tienda tienda = (Tienda) session.getAttribute("tienda"); 
    
    if (tienda == null)
        tienda = new Tienda();
    
    session.setAttribute("tienda", tienda);
    
    ArrayList<Vehiculo> vehiculos = tienda.darListaVehiculos();
    ArrayList<Alquiler> alquileres = tienda.darListaAlquileres();
    ArrayList<Cliente> clientes = tienda.darListaClientes();
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionar Alquiler</title>
    </head>
    <body>
        <table border="1" align="center">
            <thead>
                <tr>
                    <th colspan="6" align="center"><h1>Webapps ALVe</h1></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td align="center"><a href="index.jsp" target="_top">Inicio</a></td>
                    <td align="center"><a href="Vehiculo/vehiculo.jsp" target="_top">Vehiculo</a></td>
                    <td align="center"><a href="Marca/marca.jsp" target="_top">Marca</a></td>           
                    <td align="center"><a href="Linea/linea.jsp" target="_top">Linea</a></td>
                    <td align="center"><a href="ciudad/ciudad.jsp" target="_top">Ciudad</a></td>
                    <td align="center"><a href="alquiler/alquiler.jsp" target="_top">Alquiler</a></td>
                </tr>
                <tr>
                    <td height="196" colspan="3">
                        <form name="frmAdicionarAlquiler" action="../ControladorAlquiler" method="POST">
                        <table width="261" height="185" border="0" align="center" BGCOLOR="yellow">
                      <tr>
                        <td colspan="3"><div align="center"><strong>ADICIONAR ALQUILER</strong></div></td>
                      </tr>
                      <tr>
                        <td>Vehiculo</td>
                        <td colspan="2"><select name="a_vehiculos">                                            
                                                <%
                                                    for (int i= 0; i<vehiculos.size(); i++)
                                                    {
                                                        out.println("<option>"+vehiculos.get(i).getPlaca()+"</option>");
                                                    }
                                                %>
                                            </select></td>
                      </tr>
                      <tr>
                        <td>Cliente</td>
                        <td colspan="2"><select name="a_clientes">                                            
                                                <%
                                                    for (int i= 0; i<clientes.size(); i++)
                                                    {
                                                        out.println("<option>"+clientes.get(i).getNombres()+"</option>");
                                                    }
                                                %>
                                            </select></td>
                      </tr>
                      <tr>
                        <td>Horas</td>
                        <td colspan="2"><input type="text" name="a_horas" value="" /></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="Agregar" name="btnAgregar" /></td>
                      <td><input type="submit" value="cancelar" name="btnCancelar" /></td>
                      </tr>
                    </table>
                        </form>
                        <h1>&nbsp;</h1> </td>
                  <td colspan="3">
                      <form name="frmBuscarAlquiler" action="../ControladorAlquiler" method="POST">
                      <table width="209" height="189" align="center" BGCOLOR="yellow">
                    <tr>
                      <td colspan="3"><div align="center"><strong>BUSCAR ALQUILER</strong></div></td>
                    </tr>
                    <tr>
                      <td>Vehiculo</td>
                      <td colspan="2">
                          <select name="b_vehiculos">
                          <%
                                                    for (int i= 0; i<vehiculos.size(); i++)
                                                    {
                                                        out.println("<option>"+vehiculos.get(i).getPlaca()+"</option>");
                                                    }
                                                %></select></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td><input type="submit" value="buscar" name="btnBuscar" /></td>
                      <td><input type="submit" value="cancelarb" name="btnCancelarB" /></td>
                    </tr>
                  </table>
                      </form>
                      <h1>&nbsp;</h1> </td>                                       
                </tr>
            </tbody>
        </table>
    </body>
</html>
