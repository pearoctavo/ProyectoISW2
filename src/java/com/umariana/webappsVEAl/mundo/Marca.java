/**
 *
 * @author Pear Team
 */

package com.umariana.webappsVEAl.mundo;

import java.util.*;
import com.umariana.webappsVEAl.persistencia.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa a una línea de marca vehicular
 */
public class Marca 
{
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
     * nombre de la marca a la cual pertenece un vehiculo
     */
    private String nombreMarca;
        
    /**
     * pais de origen  a la cual pertenece la marca
     */
    private String paisOrigen;
       
    /**
     * contenedora de las lineas de marca
     */
    private ArrayList<Linea> lineas;
        
    private LineaDAO lineaDAO;
        
    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
     * Constructor de la clase marca
     * @param pNombreMarca  nombre de la nueva marca
     * @param pPaisOrigen nombre del pais de origen
     */        
    public Marca(String pNombreMarca, String pPaisOrigen) {
        try 
        {
            nombreMarca =  pNombreMarca;
            paisOrigen =  pPaisOrigen;
            lineaDAO = new LineaDAO();
            lineas = lineaDAO.consultar(this);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
                Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * metodo que devuelve el <tt> nombre de la marca </tt>
     * @return retorna el nombre de la marca
     */
    public String getNombreMarca()
    {
        return nombreMarca;
    }
    
    /**
     * metodo que cambia el <tt> nombre de marca</tt>
     * @param pNombreMarca a cambiar
     */
    public void setNombreMarca(String pNombreMarca)
    {
        this.nombreMarca = pNombreMarca;
    }
        
    /*
     * metodo que devuelve el <tt> pais de origen de la marca </tt>
     * @return retorna el pais de origen de la marca
     */
    public String getPaisOrigen()
    {
        return paisOrigen;
    }
    
    /**
     * metodo que cambia el <tt> Pais de origen de marca</tt>
     * @param pPaisOrigen de origen a  cambiar
     */
    public void setPaisOrigen(String pPaisOrigen)
    {
        this.paisOrigen = pPaisOrigen;
    }
    
    /**
     * metodo que adiciona una nueva Linea
     * @param pNombreLinea nuevo nombre de la liena
     * @throws Exception Si la línea ya existe
     */
    public void adicionarLinea(String pNombreLinea) throws Exception
    {
        Linea linea = buscarLinea(pNombreLinea);
        if (linea == null)
        {
            linea = new Linea(pNombreLinea);
            lineaDAO.agregarLinea(linea, this);
            lineas.add(linea);
        }
        else
            throw new Exception("La línea: " + pNombreLinea + " ya se encuentra registrada");
    }
        
    /**
     * metodo que busca una Linea
     * @param pNombreLinea nuevo nombre de la liena
     * @return linea
     */
    public Linea buscarLinea(String pNombreLinea)
    {
        Linea linea = null;
        boolean encontrado = false;
        for (int i=0; i<lineas.size()&&!encontrado; i++)
        {
            if (lineas.get(i).getNombre().equals(pNombreLinea))
            {
                linea = lineas.get(i);
                encontrado = true;
            }
        }
        return linea;
    }
        
    /**
     * metodo que elimina una Linea
     * @param pNombreLinea nuevo nombre de la liena
     * @throws Exception Si la línea no existe
     */
    public void eliminarLinea(String pNombreLinea) throws Exception
    {
        Linea linea = buscarLinea(pNombreLinea);
        if (linea != null)
        {
            lineaDAO.eliminarLinea(linea);
            lineas.remove(linea);
        }
        else
            throw new Exception("La línea: " + pNombreLinea + " no se encuentra registrada.");
    }
        
    /**
     * metodo que modifica una Linea
     * @param pLinea linea que se desea modificar
     * @param pNombreLinea nuevo nombre de la liena
     * @throws Exception Si la línea no existe
     */
    public void modificarLinea(String pLinea, String pNombreLinea) throws Exception
    {
        Linea linea = buscarLinea(pLinea);
        if (linea != null)
        {
            linea.setNombre(pNombreLinea);
            lineaDAO.modificarLinea(pLinea, pNombreLinea);
        }
        else
            throw new Exception("La línea: " + pLinea + " no se encuentra registrada.");
    }
        
    /**
     * metodo que devuelve la contenedora de lineas
     * @return lineas
     */
    public ArrayList darLineas() 
    {
        return lineas;
    }
    
    /**
     * Metodo que retorna la representación en cadena de caracteres la clase
     * @return nombre
     */
    public String toString()
    {
        return nombreMarca;
    }
}