/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.webappsVEAl.persistencia;

import com.umariana.webappsVEAl.mundo.Linea;
import com.umariana.webappsVEAl.mundo.Marca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class LineaDAO 
{
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
    * atributo que conecta con la clase fachada.
    */
    private Fachada fachada;
    
    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
    * Constructor de la clase LineaDAO.
    */
    public LineaDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * 
     * @param pLinea
     * @param pMarca
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */    
    public int agregarLinea(Linea pLinea, Marca pMarca) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "INSERT INTO linea (nombre_linea, nombre_marca) "
                + "VALUES('" + pLinea.getNombre()+"', '" + pMarca.getNombreMarca()+"')";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = (Statement) miConexion.createStatement();
            resultado = ((java.sql.Statement) instruccion).executeUpdate(sql);
            miConexion.close();
        }
        fachada.desconectar(miConexion);
        return resultado;
    }
    
    /**
     * 
     * @param pMarca
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList consultar(Marca pMarca) throws SQLException, ClassNotFoundException
    {
        ArrayList lasLineas = new ArrayList();
        String sqlConsultar = "SELECT nombre_linea FROM linea WHERE nombre_marca='"+ pMarca.getNombreMarca() +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Linea miLinea;
                miLinea = new Linea(tabla.getString("nombre_linea"));
                lasLineas.add(miLinea);
            }
        }
        fachada.desconectar(miConexion);
        return lasLineas;
    }
    
    /**
     * 
     * @param pLinea
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int eliminarLinea(Linea pLinea) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "DELETE FROM linea WHERE nombre_linea='"+pLinea.getNombre()+"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = (Statement) miConexion.createStatement();
            resultado = ((java.sql.Statement) instruccion).executeUpdate(sql);
            miConexion.close();
        }
        fachada.desconectar(miConexion);
        return resultado;
    }
    
    /**
     * 
     * @param pNombre
     * @param pNuevoNombre
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int modificarLinea(String pNombre, String pNuevoNombre) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "UPDATE linea SET nombre_linea='"+ pNuevoNombre +"' "
                + "WHERE nombre_linea='"+ pNombre +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = (Statement) miConexion.createStatement();
            resultado = ((java.sql.Statement) instruccion).executeUpdate(sql);
            miConexion.close();
        }
        fachada.desconectar(miConexion);
        return resultado;
    }
}