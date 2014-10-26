<%-- 
    Document   : marca
    Created on : 16-sep-2014, 8:24:08
    Author     : ERICK
    <%@page import="com.umariana.webappsVEAl.controlador.*" %>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.webappsVEAl.mundo.Marca"%>
<%@page import="com.umariana.webappsVEAl.mundo.Tienda"%>

<%  Tienda tienda = (Tienda) session.getAttribute("tienda"); 
    
    if (tienda == null)
        tienda = new Tienda();
    
    session.setAttribute("tienda", tienda);
    
    ArrayList<Marca> marcas = tienda.darListaMarcas();
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
                    <th colspan="4" align="center"><h1>Marca</h1></th>                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td align="center"><a href="../index.jsp" target="_top">Inicio</a></td>
                    <td align="center"><a href="../Vehiculo/vehiculo.jsp" target="_top">Vehiculo</a></td>
                    <td align="center"><a href="marca.jsp" target="_top">Marca</a></td>
                    <td align="center"><a href="../Linea/linea.jsp" target="_top">Linea</a></td>
                </tr>
                <tr>                   
                    <td colspan="2" BGCOLOR="yellow"> 
                        <form name="frmAdicionarMarca" action="../ControladorMarca" method="POST">
                                <table align="center" BGCOLOR="yellow">
                                <thead>                                   
                                    <tr>
                                        <th colspan="3">Adicionar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Nombre marca</td>
                                        <td colspan="2"> <input type="text" name="a_marca_nombre" value="" />
                                    </tr>
                                    <tr>
                                        <td>Pais Origen</td>
                                        <td colspan="2"><input type="text" name="a_marca_pais" value="" /> </td>                                 
                                    </tr>                                   
                                    <tr>                                    
                                        <td></td>
                                        <td><input type="submit" value="Agregar" id="btnAgregar" name="btnAgregar"/></td>
                                        <td><input type="reset" value="Limpiar" name="btnLimpiarAdicionarMarca" /></td>
                                    </tr>                                   
                                </tbody>
                            </table>
                        </form><!termina el formulario adicionar marca!>
                    </td>
                    <td colspan="2"> 
                     <form name="frmModificarMarca" action="../ControladorMarca" method="POST">
                                <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">Modificar</th>                                    
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Marca</td>
                                        <td>
                                            <select name="m_marca">
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
                                        <td>Nombre marca</td>
                                        <td colspan="2"> <input type="text" name="m_marca_nombre" value="" />
                                    </tr>
                                    <tr>
                                        <td>Pais Origen</td>
                                        <td colspan="2"><input type="text" name="m_marca_pais" value="" /> </td>                                 
                                    </tr>
                                    <tr>                                    
                                        <td></td>
                                        <td><input type="submit" value="Modificar"  id="btnModificar" name="btnModificar" /></td>
                                        <td><input type="reset" value="Limpiar" name="btnLimpiarAdicionarMarca" /></td>
                                    </tr>                               
                                </tbody>
                            </table>
                        </form><!termina el formulario modificar marca!>
                    </td>
                </tr>
                 <tr>
                    <td colspan="2" BGCOLOR="yellow">
                        <form name="frmBuscarMarca" action="../ControladorMarca" method="POST">
                           <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr> 
                                        <th colspan="3">Buscar</th>                                        
                                    </tr>                                    
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Marca</td>
                                        <td><input type="text" name="b_marca" value="" /></td>                                        
                                    </tr>                                   
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Buscar" id="btnBuscar" name="btnBuscar" /></td>
                                    </tr>                                    
                                </tbody>
                            </table>
                        </form>
                         </td><!termina el fomulario de buscar marca!>
                    <td colspan="2" BGCOLOR="yellow"> 
                        <form name="btnEliminarMarca" action="../ControladorMarca" method="POST">
                           <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">Eliminar</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Marca</td>
                                        <td></td>
                                        <td>
                                            <select name="e_marca">
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
                                        <td></td>                                       
                                        <td><input type="submit" value="Eliminar"  id="btnEliminar" name="btnEliminar" /></td>
                                        <td><input type="reset" value="Cancelar" name="btnCancelar" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form><!termina el formulario eliminar marca!>
                    </td>                
                 </tr>               
            </tbody>
        </table>
    </body>
</html>
