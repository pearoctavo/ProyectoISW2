/**
 *
 * @author Pear Team
 */

package com.umariana.webappsVEAl.mundo;

/**
 * Clase que representa a una línea de marca vehicular
 */
public class Linea 
{
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
     * Nombre de la linea
     */
    private String nombre;

    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
     * Contructor de la clase
     * @param pNombre - Nombre de la linea. pNombre != null
     */
    public Linea(String pNombre)
    {
        nombre = pNombre;
    }

    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * Retorna el nombre de la linea
     * @return nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Modifica el nombre de la línea
     * @param pNombre - Nombre de la linea. pNombre != null
     */
    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    
    /**
     * Metodo que retorna la representación en cadena de caracteres la clase
     * @return nombre
     */
    public String toString()
    {
        return nombre;
    }
}