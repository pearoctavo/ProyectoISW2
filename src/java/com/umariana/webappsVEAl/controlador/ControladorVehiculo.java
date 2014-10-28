/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.webappsVEAl.controlador;

import com.umariana.webappsVEAl.mundo.Linea;
import com.umariana.webappsVEAl.mundo.Marca;
import com.umariana.webappsVEAl.mundo.Tienda;
import com.umariana.webappsVEAl.mundo.Vehiculo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
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
/**
 * Controlador de la clase Vehículo que permite la conexión entre mundo y vista
 */

@WebServlet(name = "ControladorVehiculo", urlPatterns = {"/ControladorVehiculo"})
public class ControladorVehiculo extends HttpServlet {

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
        
        String espacio = "      ";
        String mImagen = "";
        String mensaje = "";
        
        if (operacionAgregar != null && operacionAgregar.equals("Agregar"))
        {
            try
            {
                double costo = Double.parseDouble(request.getParameter("a_vehiculo_costo"));

             String fichero = String.valueOf(request.getAttribute("imagenCarro"));
                   
                copyFile(new File(fichero), new File("D:\\Nueva carpeta"));

                String imagen = request.getParameter("a_vehiculo_imagen");
                String linea = request.getParameter("a_vehiculo_linea");
                String marca = request.getParameter("a_vehiculo_marca");
                String modelo = request.getParameter("a_vehiculo_modelo");
                String placa = request.getParameter("a_vehiculo_placa");
                tienda.adicionarVehiculo(costo, imagen, linea, marca, modelo, placa);

                mensaje = "El vehículo con placa: " + imagen + " fue registrada con éxito";

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
                double costo = Double.parseDouble(request.getParameter("m_vehiculo_costo"));
                String imagen = request.getParameter("m_vehiculo_imagen");
                String linea = request.getParameter("m_vehiculo_linea");
                String marca = request.getParameter("m_vehiculo_marca");
                String modelo = request.getParameter("m_vehiculo_modelo");
                String placa = request.getParameter("m_vehiculo_placa");
                String vehiculo = request.getParameter("m_placa");

                tienda.modificarVehiculo(costo, imagen, linea, marca, modelo, placa, vehiculo);

                mensaje = "El vehículo con placa: " + placa + " fue modificado con éxito";
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
                String placa = request.getParameter("b_vehiculo_placa");

                Vehiculo vehiculo = tienda.buscarVehiculo(placa);
             
                mensaje = "Datos del vehículo \n" +
                        "Placa: " + espacio + vehiculo.getPlaca() +
                        "Modelo: " + espacio + vehiculo.getModelo() +
                        "Costo por Hora: " + espacio + vehiculo.getCosto() +
                        "Marca: " + espacio + vehiculo.getMarca() +
                        "Línea: " + espacio + vehiculo.getLinea();
                
                mImagen = vehiculo.getImagen();
                session.setAttribute("imagen", mImagen);
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
                String placa = request.getParameter("e_vehiculo_placa");

                tienda.eliminarVehiculo(placa);
             
                mensaje = "El vehículo con placa: " + placa + " fue eliminado con éxito";
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
    
    public void copyFile(File s, File t)
{
try{
     FileChannel in = (new FileInputStream(s)).getChannel();
    FileChannel out = (new FileOutputStream(t)).getChannel();
in.transferTo(0, s.length(), out);
in.close();
out.close();
}
catch(Exception e)
{
System.out.println(e);
}
}
}
