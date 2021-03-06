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
     * atributo que conecta con la clase tienda
     */
    private Tienda tienda;
    
    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
    * Constructor de la clase CiudadDAO.
    */
    public CiudadDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * agrega una ciudad a la base de datos
     * @param pCiudad
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int agregarCiudad(Ciudad pCiudad) throws ClassNotFoundException, SQLException
    {
        ArrayList ultimaCiudad=consultarUltimoId();
        Ciudad laCiudad=(Ciudad)ultimaCiudad.get(0);
        int resultado = -1;
        int ciudadid= laCiudad.getId()+1;
        String sql = "INSERT INTO ciudad (id,nombre) "
                + "VALUES("+ciudadid +",'" + pCiudad.getNombreCiudad() + "')";
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
     * consulta todas las ciudades de la base de datos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList consultar( ) throws SQLException, ClassNotFoundException
    {
        ArrayList ciudades = new ArrayList();
        String sqlConsultar = "SELECT id,nombre FROM ciudad";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Ciudad ciudad = new Ciudad(Integer.parseInt(tabla.getString("id")),tabla.getString("nombre"));
                ciudades.add(ciudad);
            }
        }
        fachada.desconectar(miConexion);
        return ciudades;
    }
    
    
    /**
     * consulta el ultimo id la base de datos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList consultarUltimoId( ) throws SQLException, ClassNotFoundException
    {
        ArrayList ciudades = new ArrayList();
        String sqlConsultar = "SELECT id, nombre FROM ciudad order by id desc";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Ciudad ciudad = new Ciudad(Integer.parseInt(tabla.getString("id")),tabla.getString("nombre"));
                ciudades.add(ciudad);
            }
        }
        fachada.desconectar(miConexion);
        return ciudades;
    }
    
    /**
     * consulta por nombre la ciudad en la base de datos 
     * @param pNombre
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Ciudad consultarPorNombre(String pNombre) throws SQLException, ClassNotFoundException
    {
        Ciudad ciudad = null;
        String sqlConsultar = "SELECT id, nombre FROM ciudad WHERE nombre='"+ pNombre +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                ciudad = new Ciudad(Integer.parseInt(tabla.getString("id")),tabla.getString("nombre"));
            }
        }
        fachada.desconectar(miConexion);
        return ciudad;
    }
    
    /**
     * elimina una ciudad den la base de datos 
     * @param pCiudad
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int eliminarCiudad(Ciudad pCiudad) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "DELETE FROM ciudad WHERE nombre='"+pCiudad.getNombreCiudad()+"'";
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
     * modifica una ciudad en la base de datos
     * @param pNombre
     * @param pCiudad
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int modificarCiudad(String pNombre, Ciudad pCiudad) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "UPDATE ciudad SET nombre='"+ pCiudad.getNombreCiudad() +"'"
                + "WHERE nombre='"+ pNombre +"'";
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
