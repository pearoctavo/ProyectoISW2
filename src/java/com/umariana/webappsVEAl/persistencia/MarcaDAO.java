/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.webappsVEAl.persistencia;

import com.umariana.webappsVEAl.mundo.Marca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PearTeam
 */
public class MarcaDAO {
    
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
    * Constructor de la clase MarcaDAO.
    */
    public MarcaDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * 
     * @param pMarca
     * @return resultado
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int agregarMarca(Marca pMarca) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "Insert into marca (nombre_marca, pais_origen) values('" + pMarca.getNombreMarca()+"', '"+ pMarca.getPaisOrigen()+"')";
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
     * @return marcas
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList consultar() throws SQLException, ClassNotFoundException
    {
        ArrayList marcas = new ArrayList();
        String sqlConsultar = "SELECT nombre_marca, pais_origen FROM marca";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Marca marca = new Marca(tabla.getString("nombre_marca"),tabla.getString("pais_origen"));
                marcas.add(marca);
            }
        }
        fachada.desconectar(miConexion);
        return marcas;
    }
    
    /**
     * 
     * @param pNombre
     * @return marca
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Marca consultarPorNombre(String pNombre) throws SQLException, ClassNotFoundException
    {
        Marca marca = null;
        String sqlConsultar = "SELECT nombre_marca, pais_origen FROM marca WHERE nombre_marca='"+ pNombre +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                marca = new Marca(tabla.getString("nombre_marca"),tabla.getString("pais_origen"));
            }
        }
        fachada.desconectar(miConexion);
        return marca;
    }
    
    /**
     * 
     * @param pMarca
     * @param pNombre
     * @return resultado
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int modificarMarca(Marca pMarca, String pNombre) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "UPDATE marca SET nombre_marca = '"+ pMarca.getNombreMarca() +"', pais_origen = '"+ pMarca.getPaisOrigen() +"' WHERE nombre_marca='"+ pNombre +"'";
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
     * @return resultado
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int eliminarMarca(Marca pMarca) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "DELETE FROM marca WHERE nombre_marca='"+pMarca.getNombreMarca()+"'";
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