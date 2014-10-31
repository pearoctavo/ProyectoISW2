<%-- 
    Document   : ciudad
    Created on : 20-oct-2014, 17:31:59
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
    
    pageContext.setAttribute("ciudadesJstl", ciudades);    
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alquiler de Vehiculos</title>
    </head>
    <body>
        <table border="1" align="center">
            <thead>
                <tr>
                    <th colspan="7" align="center"><h1>Ciudad</h1></th>                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td align="center"><a href="../index.jsp" target="_top">Inicio</a></td>
                    <td align="center"><a href="../Vehiculo/vehiculo.jsp" target="_top">Vehiculo</a></td>
                    <td align="center"><a href="../Marca/marca.jsp" target="_top">Marca</a></td>
                    <td align="center"><a href="../Linea/linea.jsp" target="_top">Linea</a></td>
                    <td align="center"><a href="ciudad.jsp" target="_top">Ciudad</a></td>
                    <td align="center"><a href="../cliente/cliente.jsp" target="_top">Cliente</a></td>
                    <td align="center"><a href="../alquiler/alquiler.jsp" target="_top">Alquiler</a></td>                    
                </tr>
                <tr>                   
                    <td colspan="3" BGCOLOR="yellow"> 
                        <form name="frmAdicionarCiudad" action="../ControladorCiudad" method="POST">
                                <table align="center" BGCOLOR="yellow">
                                <thead>                                   
                                    <tr>
                                        <th colspan="3">Adicionar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Nombre ciudad</td>
                                        <td colspan="2"> <input type="text" name="a_ciudad_nombre" value="" />
                                    </tr>                                  
                                    <tr>                                    
                                        <td></td>
                                        <td><input type="submit" value="Agregar" id="btnAgregar" name="btnAgregar"/></td>
                                        <td><input type="reset" value="Limpiar" name="btnLimpiarAdicionarCiudad" /></td>
                                    </tr>                                   
                                </tbody>
                            </table>
                        </form><!termina el formulario adicionar ciudad!>
                    </td>
                    <td colspan="4"> 
                     <form name="frmModificarCiudad" action="../ControladorCiudad" method="POST">
                                <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">Modificar</th>                                    
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Ciudad</td>
                                        <td>
                                            <select name="m_ciudad">
                                                <option>Seleccione</option>
                                                <c:forEach var="listaCiudades"  items="${ciudadesJstl}">
                                                <option><c:out value="${listaCiudades}"/></option>
                                                </c:forEach> 
                                            </select>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td>Nuevo nombre ciudad</td>
                                        <td colspan="2"> <input type="text" name="m_ciudad_nombre" value="" />
                                    </tr>                                   
                                    <tr>                                    
                                        <td></td>
                                        <td><input type="submit" value="Modificar"  id="btnModificar" name="btnModificar" /></td>
                                        <td><input type="reset" value="Limpiar" name="btnLimpiarModificarCiudad" /></td>
                                    </tr>                               
                                </tbody>
                            </table>
                        </form><!termina el formulario modificar Ciudad!>
                    </td>
                </tr>
                 <tr>
                    <td colspan="3" BGCOLOR="yellow">
                        <form name="frmBuscarCiudad" action="../ControladorCiudad" method="POST">
                           <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr> 
                                        <th colspan="3">Buscar</th>                                        
                                    </tr>                                    
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Ciudad</td>
                                        <td><input type="text" name="b_Ciudad" value="" /></td>                                        
                                    </tr>                                   
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Buscar" id="btnBuscar" name="btnBuscar" /></td>
                                    </tr>                                    
                                </tbody>
                            </table>
                        </form>
                         </td><!termina el fomulario de buscar Ciudad!>
                    <td colspan="4" BGCOLOR="yellow"> 
                        <form name="btnEliminarCiudad" action="../ControladorCiudad" method="POST">
                           <table align="center" BGCOLOR="yellow">
                                <thead>
                                    <tr>
                                        <th colspan="3">Eliminar</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Ciudad</td>
                                        <td></td>
                                        <td>
                                            <select name="e_Ciudad">
                                                <option>Seleccione</option>
                                                <c:forEach var="listaCiudades"  items="${ciudadesJstl}">
                                                <option><c:out value="${listaCiudades}"/></option>
                                                </c:forEach> 
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
