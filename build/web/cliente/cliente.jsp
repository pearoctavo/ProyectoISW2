<%-- 
    Document   : cliente
    Created on : 20-oct-2014, 17:31:26
    Author     : Pear
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.webappsVEAl.mundo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>

<%  Tienda tienda = (Tienda) session.getAttribute("tienda"); 
    
    if (tienda == null)
        tienda = new Tienda();
    
    session.setAttribute("tienda", tienda);
    
    ArrayList<Ciudad> ciudades = tienda.darListaCiudades();
    //ArrayList<Cliente> clientes = tienda.darListaClientes();
    
    pageContext.setAttribute("ciudadesJstl", ciudades); 
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alquiler de Vehiculos - Cliente</title>
    </head>
    <body>
        <table border="1" align="center">
            <thead>
                <tr>
                    <th colspan="7" align="center"><h1>Clientes</h1></th>                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td align="center"><a href="../index.jsp" target="_top">Inicio</a></td>
                    <td align="center"><a href="../Vehiculo/vehiculo.jsp" target="_top">Vehiculo</a></td>
                    <td align="center"><a href="../Marca/marca.jsp" target="_top">Marca</a></td>
                    <td align="center"><a href="../Linea/linea.jsp" target="_top">Linea</a></td>
                    <td align="center"><a href="../ciudad/ciudad.jsp" target="_top">Ciudad</a></td>
                    <td align="center"><a href="cliente.jsp" target="_top">Cliente</a></td>
                    <td align="center"><a href="../alquiler/alquiler.jsp" target="_top">Alquiler</a></td>                    
                </tr>
                <tr>                   
                    <td colspan="3" BGCOLOR="yellow"> 
                        <form name="frmAdicionarCliente" action="../ControladorCliente" method="POST">
                                <table align="center" BGCOLOR="yellow">
                                <thead>                                   
                                    <tr>
                                        <th colspan="3">Adicionar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Nombres: </td>
                                        <td colspan="2"> <input type="text" name="a_cliente_nombre" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Apellidos: </td>
                                        <td colspan="2"> <input type="text" name="a_cliente_apellido" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Identificación: </td>
                                        <td colspan="2"> <input type="text" name="a_cliente_identificacion" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Ciudad: </td>
                                        <td>
                                            <select name="a_cliente_ciudad">
                                                <option>Seleccione</option>
                                                <c:forEach var="listaCiudades"  items="${ciudadesJstl}">
                                                <option><c:out value="${listaCiudades}"/></option>
                                                </c:forEach>                                                 
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Email: </td>
                                        <td colspan="2"> <input type="text" name="a_cliente_email" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Teléfono: </td>
                                        <td colspan="2"> <input type="text" name="a_cliente_telefono" value="" required/>
                                    </tr>
                                    <tr>                                    
                                        <td></td>
                                        <td><input type="submit" value="Agregar" id="btnAgregar" name="btnAgregar"/></td>
                                        <td><input type="reset" value="Limpiar" name="btnLimpiarAdicionarCiudad" /></td>
                                    </tr>                                   
                                </tbody>
                            </table>
                        </form><!termina el formulario adicionar cliente!>
                    </td>
                    <td colspan="4"> 
                     <form name="frmModificarCliente" action="../ControladorCliente" method="POST">
                                <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">Modificar</th>                                    
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Identificación Cliente: </td>
                                        <td>
                                            <select name="m_cliente_identificacion">
                                                <%  
                                                    /*for (int i= 0; i<clientes.size(); i++)
                                                    {
                                                        out.println("<option>"+clientes.get(i).getIdentificacion()+"</option>");
                                                    }*/
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nombres: </td>
                                        <td colspan="2"> <input type="text" name="m_cliente_nombre" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Apellidos: </td>
                                        <td colspan="2"> <input type="text" name="m_cliente_apellido" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Ciudad: </td>
                                        <td>
                                            <select name="m_cliente_ciudad">
                                                <%
                                                    /*for (int i= 0; i<ciudades.size(); i++)
                                                    {
                                                        out.println("<option>"+ciudades.get(i).getNombreCiudad()+"</option>");
                                                    }*/
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Email: </td>
                                        <td colspan="2"> <input type="text" name="m_cliente_email" value="" required/>
                                    </tr>
                                    <tr>
                                        <td>Teléfono: </td>
                                        <td colspan="2"> <input type="text" name="m_cliente_telefono" value="" required/>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Modificar"  id="btnModificar" name="btnModificar" /></td>
                                        <td><input type="reset" value="Limpiar" name="btnLimpiarModificarCiudad" /></td>
                                    </tr>                               
                                </tbody>
                            </table>
                        </form><!termina el formulario modificar Cliente!>
                    </td>
                </tr>
                 <tr>
                    <td colspan="3" BGCOLOR="yellow">
                        <form name="frmBuscarCliente" action="../ControladorCliente" method="POST">
                           <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr> 
                                        <th colspan="3">Buscar</th>                                        
                                    </tr>                                    
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Identificación: </td>
                                        <td><input type="text" name="b_cliente" value="" /></td>                                        
                                    </tr>                                   
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Buscar" id="btnBuscar" name="btnBuscar" /></td>
                                    </tr>                                    
                                </tbody>
                            </table>
                        </form>
                         </td><!termina el fomulario de buscar Cliente!>
                    <td colspan="4" BGCOLOR="yellow"> 
                        <form name="btnEliminarCliente" action="../ControladorCliente" method="POST">
                           <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">Eliminar</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Identificación Cliente: </td>
                                        <td>
                                            <select name="e_cliente">
                                                <%  
                                                    /*for (int i= 0; i<clientes.size(); i++)
                                                    {
                                                        out.println("<option>"+clientes.get(i).getIdentificacion()+"</option>");
                                                    }*/
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
                        </form><!termina el formulario eliminar ciudad!>
                    </td>                
                 </tr>               
            </tbody>
        </table>
    </body>
</html>
