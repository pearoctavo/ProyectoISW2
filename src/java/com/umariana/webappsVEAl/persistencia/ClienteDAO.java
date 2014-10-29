/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.webappsVEAl.persistencia;

import com.umariana.webappsVEAl.mundo.Cliente;
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
public class ClienteDAO {
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
    public ClienteDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * 
     * @param pCliente
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int agregarCliente(Cliente pCliente) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "INSERT INTO cliente (identificacion, nombres, apellidos, email, telefono, ciudad) "
                + "VALUES('" + pCliente.getIdentificacion()+ "', " + pCliente.getNombres() + ", '"+ pCliente.getApellidos() + "', '" + pCliente.getEmail() + "', '" + pCliente.getTelefono() + "', '"+ pCliente.getCiudad() +"')";
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
        ArrayList clientes = new ArrayList();
        String sqlConsultar = "SELECT identificacion, nombres, apellidos, email, telefono, ciudad FROM cliente";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Cliente cliente = new Cliente(tabla.getString("apellidos"), tabla.getString("ciudad"), tabla.getString("email"), tabla.getString("identificacion"), tabla.getString("nombres"), tabla.getString("telefono"));
                clientes.add(cliente);
            }
        }
        fachada.desconectar(miConexion);
        return clientes;
    }
    
    /**
     * 
     * @param pIdentificacion
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Cliente consultarPorIdentificacion(String pIdentificacion) throws SQLException, ClassNotFoundException
    {
        Cliente cliente = null;
        String sqlConsultar = "SELECT identificacion, nombres, apellidos, email, telefono, ciudad FROM cliente WHERE identificacion='"+ pIdentificacion +"'";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                cliente = new Cliente(tabla.getString("apellidos"), tabla.getString("ciudad"), tabla.getString("email"), tabla.getString("identificacion"), tabla.getString("nombres"), tabla.getString("telefono"));
            }
        }
        fachada.desconectar(miConexion);
        return cliente;
    }
    
    /**
     * 
     * @param pCliente
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int eliminarVehiculo(Vehiculo pCliente) throws ClassNotFoundException, SQLException
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