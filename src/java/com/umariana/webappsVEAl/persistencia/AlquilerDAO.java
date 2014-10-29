/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.webappsVEAl.persistencia;

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
public class AlquilerDAO {
    
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
    public VehiculoDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * 
     * @param pVehiculo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int agregarVehiculo(Vehiculo pVehiculo) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "INSERT INTO vehiculo (placa, costo, imagen, linea, marca, modelo) "
                + "VALUES('" + pVehiculo.getPlaca()+ "', " + pVehiculo.getCosto() + ", '"+ pVehiculo.getImagen() + "', '" + pVehiculo.getLinea() + "', '" + pVehiculo.getMarca() + "', '"+ pVehiculo.getModelo() +"')";
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
        ArrayList vehiculos = new ArrayList();
        String sqlConsultar = "SELECT placa, costo, imagen, linea, marca, modelo FROM vehiculo";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Vehiculo vehiculo = new Vehiculo(Double.parseDouble(tabla.getString("costo")), tabla.getString("imagen"), tabla.getString("linea"), tabla.getString("marca"), tabla.getString("modelo"), tabla.getString("placa") );
                vehiculos.add(vehiculo);
            }
        }
        fachada.desconectar(miConexion);
        return vehiculos;
    }
    
    /**
     * 
     * @param pPlaca
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Vehiculo consultarPorPlaca(String pPlaca) throws SQLException, ClassNotFoundException
    {
        Vehiculo vehiculo = null;
        String sqlConsultar = "SELECT placa, costo, imagen, linea, marca, modelo,nombre_linea FROM vehiculo WHERE placa='"+ pPlaca +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                vehiculo = new Vehiculo(Double.parseDouble(tabla.getString("costo")), tabla.getString("imagen"), tabla.getString("linea"), tabla.getString("marca"), tabla.getString("modelo"), tabla.getString("placa") );
            }
        }
        fachada.desconectar(miConexion);
        return vehiculo;
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
