<%-- 
    Document   : vehiculo
    Created on : 16-sep-2014, 8:24:28
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.webappsVEAl.mundo.Linea"%>
<%@page import="com.umariana.webappsVEAl.mundo.Marca"%>
<%@page import="com.umariana.webappsVEAl.mundo.Tienda"%>
<%@page import="com.umariana.webappsVEAl.mundo.Vehiculo"%>

<%  Tienda tienda = (Tienda) session.getAttribute("tienda"); 
    
    if (tienda == null)
        tienda = new Tienda();
    
    session.setAttribute("tienda", tienda);
    
    ArrayList<Marca> marcas = tienda.darListaMarcas();
    ArrayList<Vehiculo> vehiculos = tienda.darListaVehiculos();
    //ArrayList<Linea> lineas = new ArrayList<Linea>();
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
                    <th colspan="4" align="center"><h1>Vehiculo</h1></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td align="center"><a href="../index.jsp" target="_top">Inicio</a></td>
                    <td align="center"><a href="vehiculo.jsp" target="_top">Vehículo</a></td>
                    <td align="center"><a href="../Marca/marca.jsp" target="_top">Marca</a></td>
                    <td align="center"><a href="../Linea/linea.jsp" target="_top">Linea</a></td>
                </tr>
                <tr>
                    <td colspan="2" bgcolor="yellow">
                        <form name="frmAdicionarVehiculo" action="../ControladorVehiculo" method="POST">
                            <table align= "center" bgcolor="yellow" border="0">
                                <thead>
                                    <tr>
                                        <th colspan="3">ADICIONAR</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Placa: </td>
                                        <td colspan="2"><input type="text" name="a_vehiculo_placa" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Modelo: </td>
                                        <td colspan="2"><input type="text" name="a_vehiculo_modelo" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Imagen: </td>
                                        <td colspan="2"><input type="file" value="a_vehiculo_imagen" name="btnImagenVehiculo" /></td>
                                    </tr>
                                    <tr>
                                        <td>Costo Hora: </td>
                                        <td colspan="2"><input type="text" name="a_vehiculo_costo" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Marca: </td>
                                        <td colspan="2">
                                            <select name="a_vehiculo_marca">                                            
                                                <%
                                                    for (int i= 0; i<marcas.size(); i++)
                                                    {
                                                        out.println("<option>"+marcas.get(i).getNombreMarca()+"</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Linea: </td>
                                        <td colspan="2"><input type="text" velue="" name="a_vehiculo_linea" /></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right"><input type="submit" value="Agregar" name="btnAgregar" /></td>
                                        <td align="right"><input type="reset" value="Limpiar" name="btnLimpiar" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </td>
                    <td colspan="2">
                        <form name="frmModificarVehiculo" action="../ControladorVehiculo" method="POST">
                            <table align= "center" bgcolor="yellow" border="0">
                                <thead>
                                    <tr>
                                        <th colspan="3">MODIFICAR</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Vehículo: </td>
                                        <td colspan="2">
                                            <select name="m_placa">
                                                <%
                                                    for (int i= 0; i<vehiculos.size(); i++)
                                                    {
                                                        out.println("<option>"+vehiculos.get(i).getPlaca()+"</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>                                      
                                    </tr>
                                    <tr>
                                        <td>Nueva Placa: </td>
                                        <td colspan="2"><input type="text" name="m_vehiculo_placa" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Modelo: </td>
                                        <td colspan="2"><input type="text" name="m_vehiculo_modelo" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Imagen: </td>
                                        <td><input type="file" value="Agregar Imagen" name="m_vehiculo_imagen" /></td>
                                    </tr>
                                    <tr>
                                        <td>Costo Hora: </td>
                                        <td colspan="2"><input type="text" name="m_vehiculo_costo" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Marca: </td>
                                        <td colspan="2">
                                            <select name="m_vehiculo_marca">
                                                <%
                                                    for (int i= 0; i<marcas.size(); i++)
                                                    {
                                                        out.println("<option>"+marcas.get(i).getNombreMarca()+"</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Linea</td>
                                        <td colspan="2"><input type="text" name="m_vehiculo_linea" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right"><input type="submit" value="Modificar" name="btnModificar" /></td>
                                        <td align="right"><input type="reset" value="Limpiar" name="btnLimpiar" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" bgcolor="yellow">
                        <form name="frmBuscarVechiculo" action="../ControladorVehiculo" method="POST">
                            <table align="center" border="0" bgcolor="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">BUSCAR</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Vehículo: </td>
                                        <td colspan="2"><input type="text" name="b_vehiculo_placa" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" align="right"><input type="submit" value="Buscar" name="btnBuscar" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </td>
                    <td colspan="2" bgcolor='yellow'>
                        <form name="frmEliminarVehiculo" action="../ControladorVehiculo" method="POST">
                            <table align="center" border="0" bgcolor="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">ELIMINAR</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Vehíuculo: </td>
                                        <td colspan="2">
                                            <select name="e_vehiculo_placa">
                                                <%
                                                    for (int i= 0; i<vehiculos.size(); i++)
                                                    {
                                                        out.println("<option>"+vehiculos.get(i).getPlaca()+"</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" align="right"><input type="submit" value="Eliminar" name="btnEliminar" /></td>
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