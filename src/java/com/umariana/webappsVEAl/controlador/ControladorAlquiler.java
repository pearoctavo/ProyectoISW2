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
 * @author ERICK
 */
@WebServlet(name = "ControladorAlquiler", urlPatterns = {"/ControladorAlquiler"})
public class ControladorAlquiler extends HttpServlet {

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
        
        HttpSession session =  request.getSession(true);
        Tienda tienda =(Tienda) session.getAttribute("tienda");
        session.setAttribute("tienda", tienda);
        
        String operacionAgregar =  request.getParameter("btnAgregar");
        String operacionBuscar =  request.getParameter("btnBuscar");
        
        
        String mensaje = "";
        String espacio = "      ";
        
        if (operacionAgregar != null && operacionAgregar.equals("Agregar"))
        {
            try
            {
                String nCliente =  request.getParameter("a_clientes");                
                int nHoras = Integer.parseInt(request.getParameter("a_horas"));
                String nVehiculo = request.getParameter("a_vehiculos");                              
                
                
                tienda.adicionarAlquiler(nCliente, nHoras,nVehiculo);

                mensaje = "Detalles:  Cliente" + nCliente+
                          "\nNumero de horas " + nHoras + 
                          "\nVehiculo:"+ nVehiculo  +                        
                          "\n Este alquiler fue registrado con éxito";

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
                String nPlaca =  request.getParameter("b_alquiler");

                /*Alquiler alquiler =  tienda.buscarAlquiler(nPlaca);
             
                mensaje = "Datos del Alquiler \n" +
                        "\nCliente:  " + espacio + alquiler.getCliente()()+
                        "\nPlaca: " + espacio + alquiler.getVehiculo()+ 
                        "\nHorasas: " + espacio + alquiler.getHoras();
                session.setAttribute("mensaje", mensaje);*/

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
