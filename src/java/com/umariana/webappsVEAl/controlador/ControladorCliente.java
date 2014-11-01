/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.webappsVEAl.controlador;

import com.umariana.webappsVEAl.mundo.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pear Team
 */
@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        Tienda tienda = (Tienda) session.getAttribute("tienda");
        session.setAttribute("tienda", tienda);
                             
        String operacionAgregar =  request.getParameter("btnAgregar");
        String operacionModificar =  request.getParameter("btnModificar");
        String operacionEliminar = request.getParameter("btnEliminar");
        String operacionBuscar = request.getParameter("btnBuscar");
        
        String mensaje = "";
        String espacio = "      ";
        
        if (operacionAgregar != null && operacionAgregar.equals("Agregar"))
        {
            try
            {
                String nIdentificacion =  request.getParameter("a_cliente_identificacion");
                String nNombre = request.getParameter("a_cliente_nombre");
                String nApellido= request.getParameter("a_cliente_apellido");
                String nCiudad =  request.getParameter("a_cliente_ciudad");
                String nEmail = request.getParameter("a_cliente_email");
                String nTelefono = request.getParameter("a_cliente_telefono");
                                
                tienda.adicionarCliente(nApellido, nCiudad, nEmail, nIdentificacion, nNombre, nTelefono);

                mensaje = "El usuario:" + nNombre + espacio + nApellido +  " fue registrada con éxito";
                
                session.setAttribute("mensaje", mensaje);

                response.sendRedirect("./respuesta.jsp");
            }
            catch( Exception e )
            {
                out.println(e.getMessage());
            }
        }
        else if (operacionModificar != null && operacionModificar.equals("Modificar")){            
            try
            {
                String nIdentificacion =  request.getParameter("m_cliente_identificacion");
                String nNombre = request.getParameter("m_cliente_nombre");
                String nApellido= request.getParameter("m_cliente_apellido");
                String nCiudad =  request.getParameter("m_cliente_ciudad");
                String nEmail = request.getParameter("m_cliente_email");
                String nTelefono = request.getParameter("m_cliente_telefono");

                tienda.modificarCliente(nApellido, nCiudad, nEmail, nIdentificacion, nNombre, nTelefono);
                
                mensaje = "El usuario con identificacion:" + nIdentificacion +  " fue modificado con éxito";
                session.setAttribute("mensaje", mensaje);

                response.sendRedirect("./respuesta.jsp");
            }
            catch( Exception e )
            {
                out.println(e.getMessage());
            }
        }        
        else if(operacionBuscar != null && operacionBuscar.equals("Buscar"))
        {
            try
            {
                String nIdentificacion =  request.getParameter("b_cliente");

                Cliente cliente =  tienda.buscarCliente(nIdentificacion);
             
                mensaje = "Datos del Usuario \n" +
                        "\nIdentificacion:  " + espacio + cliente.getIdentificacion()+
                        "\nNombre: " + espacio + cliente.getNombres() + 
                        "\nApellidos: " + espacio + cliente.getApellidos()+ 
                        "\nCiudad: " + espacio + cliente.getCiudad() + 
                        "\nTelefono: " + espacio + cliente.getTelefono() + 
                        "\nEmail: " + espacio + cliente.getEmail();
                session.setAttribute("mensaje", mensaje);

                response.sendRedirect("./respuesta.jsp");
            }
            catch( Exception e )
            {
                out.println(e.getMessage());
            }
        }    
        else if(operacionEliminar != null && operacionEliminar.equals("Eliminar")){
            try
            {
                String nIdentificacion =  request.getParameter("e_cliente");
                tienda.eliminarCliente(nIdentificacion);
             
                mensaje = "El cliente con identificacion::" + nIdentificacion+ " fue eliminado con éxito";
                session.setAttribute("mensaje", mensaje);

                response.sendRedirect("./respuesta.jsp");
            }
            catch( Exception e )
            {
                out.println(e.getMessage());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}