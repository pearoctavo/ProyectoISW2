/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.webappsVEAl.persistencia;

import com.umariana.webappsVEAl.mundo.Alquiler;
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

public class AlquilerDAO {
    
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
    * Constructor de la clase AlquilerDAO.
    */
    public AlquilerDAO()
    {
        fachada = new Fachada();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * agre un alquiler a la abse de datos
     * @param pAlquiler
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int agregarAlquiler(Alquiler pAlquiler) throws ClassNotFoundException, SQLException
    {
        int resultado = -1;
        String sql = "INSERT INTO alquiler (vehiculo,cliente,horas)"
                + "VALUES('" +pAlquiler.getVehiculo() + "','"+ pAlquiler.getCliente()+ "'," + pAlquiler.getHoras() + ")";
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
     * consulta todos los alquileres de la base de datos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList consultar( ) throws SQLException, ClassNotFoundException
    {
        ArrayList alquileres = new ArrayList();
        String sqlConsultar = "SELECT cliente,vehiculo,horas FROM alquiler";
        Connection miConexion = fachada.conectar();
        if(miConexion != null)
        {
            Statement instruccion = miConexion.createStatement();
            ResultSet tabla = instruccion.executeQuery(sqlConsultar);
            while(tabla.next())
            {
                Alquiler alquiler = new Alquiler(tabla.getString("cliente"),Integer.parseInt(tabla.getString("horas")), tabla.getString("vehiculo")  );
                alquileres.add(alquiler);
            }
        }
        fachada.desconectar(miConexion);
        return alquileres;
    }
    
   
}
    