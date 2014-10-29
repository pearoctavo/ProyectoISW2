/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.webappsVEAl.persistencia;

import com.umariana.webappsVEAl.mundo.Ciudad;
import com.umariana.webappsVEAl.mundo.Tienda;
import com.umariana.webappsVEAl.mundo.Vehiculo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jairo
 */
public class CiudadDAO {
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
     * atributo que conecta con la clase fachada.
     */
    private Fachada fachada;
    
    /**
     * 
     */
    private Tienda tienda;
    
    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
    * Constructor de la clase vehiculoDAO.
    */
    public CiudadDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * 
     * @param pCiudad
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int agregarCiudad(Ciudad pCiudad) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "INSERT INTO ciudad (nombre) "
                + "VALUES('" + pCiudad.getNombreCiudad() + "')";
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
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList consultar( ) throws SQLException, ClassNotFoundException
    {
        ArrayList ciudades = new ArrayList();
        String sqlConsultar = "SELECT nombre FROM ciudad";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Ciudad ciudad = new Ciudad(tabla.getString("nombre"));
                ciudades.add(ciudad);
            }
        }
        fachada.desconectar(miConexion);
        return ciudades;
    }
    
    /**
     * 
     * @param pNombre
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Ciudad consultarPorNombre(String pNombre) throws SQLException, ClassNotFoundException
    {
        Ciudad ciudad = null;
        String sqlConsultar = "SELECT nombre FROM ciudad WHERE nombre='"+ pNombre +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                ciudad = new Ciudad(tabla.getString("nombre"));
            }
        }
        fachada.desconectar(miConexion);
        return ciudad;
    }
    
    /**
     * 
     * @param pVehiculo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int eliminarVehiculo(Vehiculo pVehiculo) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "DELETE FROM vehiculo WHERE placa='"+pVehiculo.getPlaca()+"'";
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
     * @param pPlaca
     * @param pVehiculo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int modificarVehiculo(String pPlaca, Vehiculo pVehiculo) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "UPDATE vehiculo SET placa='"+ pVehiculo.getPlaca() +"', costo="+ pVehiculo.getCosto() +", imagen='"+ pVehiculo.getImagen() +"', linea='"+ pVehiculo.getLinea() +"', marca='"+ pVehiculo.getMarca() +"', modelo='"+ pVehiculo.getModelo() +"' "
                + "WHERE placa='"+ pPlaca +"'";
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
