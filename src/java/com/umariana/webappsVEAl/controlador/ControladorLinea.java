/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.webappsVEAl.controlador;

import com.umariana.webappsVEAl.mundo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pear
 */

/**
 * Controlador de la clase Linea que permite la conexión entre mundo y vista
 */
@WebServlet(name = "ControladorLinea", urlPatterns = {"/ControladorLinea"})
public class ControladorLinea extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
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
        
        if(operacionAgregar != null && operacionAgregar.equals("Agregar")){
            try
            {
                String nNombreMarca = request.getParameter("a_linea_marca");
                String nNombreLinea = request.getParameter("a_linea_nombre");
                tienda.adicionarLinea(nNombreMarca,nNombreLinea);

                mensaje = "La linea: " + nNombreLinea + " fue registrada con éxito";

                session.setAttribute("mensaje", mensaje);

                response.sendRedirect("./respuesta.jsp");
            }
            catch( Exception e )
            {
                out.println(e.getMessage());
            }
        }
        else if (operacionModificar != null && operacionModificar.equals("Modificar")){
            try {
                
                    String nombreMarca = request.getParameter("m_linea_marca");           
                    String nombreLinea =  request.getParameter("m_nombre_linea");
                    String nuevoNombreLinea = request.getParameter("m_nuevo_nombre");

                    tienda.modificarLinea(nombreLinea,nombreMarca, nuevoNombreLinea);

                    mensaje = "La marca: " + nombreMarca + " fue modificada con éxito";
                    session.setAttribute("mensaje", mensaje);

                    response.sendRedirect("./respuesta.jsp");
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
        else if(operacionBuscar != null && operacionBuscar.equals("Buscar"))
        {
            try
            {
                //String nMarca= request.getParameter("b_lista_marca");
                String nLinea = request.getParameter("b_nombre_linea");
                

                Linea linea = tienda.buscarLinea(nLinea);
             
                mensaje = "Datos de la linea \n" +
                        "Nombre: \n " + espacio + linea.getNombre()+" ";
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
                String nombreMarca = request.getParameter("e_lista_marcas");
                String nombreLinea = request.getParameter("e_nombre_linea");
                

                tienda.eliminarLinea(nombreMarca, nombreLinea);
             
                mensaje = "La linea: " + nombreLinea + " fue eliminada con éxito";
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