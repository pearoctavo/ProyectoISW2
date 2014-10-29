/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.webappsVEAl.controlador;

import com.umariana.webappsVEAl.mundo.Ciudad;
import com.umariana.webappsVEAl.mundo.Tienda;
import com.umariana.webappsVEAl.mundo.Vehiculo;
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
 * @author ERICK
 */
@WebServlet(name = "ControladorCiudad", urlPatterns = {"/ControladorCiudad"})
public class ControladorCiudad extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        Tienda tienda = (Tienda) session.getAttribute("tienda");
        session.setAttribute("tienda", tienda);
                             
        String operacionAgregar =  request.getParameter("btnAgregar");
        String operacionModificar =  request.getParameter("btnModificar");
        String operacionEliminar = request.getParameter("btnEliminar");
        String operacionBuscar = request.getParameter("btnBuscar");
        
        String espacio = "      ";
        String mImagen = "";
        String mensaje = "";
        
        if (operacionAgregar != null && operacionAgregar.equals("Agregar"))
            
        {
            try
            {
                
                String nombreCiudad = request.getParameter("a_ciudad_nombre");

                tienda.adicionarCiudad(nombreCiudad);

                mensaje = "El la ciudad de nombre: " + nombreCiudad + " fue registrada con éxito";

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
                String nombreCiudad = request.getParameter("m_ciudad");
                String nuevoNombre = request.getParameter("m_ciudad_nombre");

                tienda.modificarCiudad(nombreCiudad,nuevoNombre);

                mensaje = "La ciudad de nombre: " + nombreCiudad + " fue modificada con éxito";
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
                String nombre = request.getParameter("b_Ciudad");

                Ciudad ciudad = tienda.buscarCiudad(nombre);
             
                mensaje = "Datos de la ciudad \n" +
                        "nombre: " + espacio + ciudad.getNombreCiudad();
                
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
                String nombre = request.getParameter("e_Ciudad");

                tienda.eliminarCiudad(nombre);
             
                mensaje = "La ciudad con el nombre " + nombre + " fue eliminada con éxito";
                session.setAttribute("mensaje", mensaje);

                response.sendRedirect("./respuesta.jsp");
            }
            catch( Exception e )
            {
                out.println(e.getMessage());
            }
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
