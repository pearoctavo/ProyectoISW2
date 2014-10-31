package com.umariana.webappsVEAl.mundo;

/**
 * Clase que representa una ciudad a la cual 
 * pertenecen clientes y tienda de alquileres
 * @author Pear Team
 */
public class Ciudad 
{
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
     * nombre de la ciudad
     */
    private String nombreCiudad;
    
    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
     * Constructor del objeto ciudad
     * @param pNombreCiudad - ciudad a la cual pertenecen clientes y tienda de alquiler. pNombreCiudad != ""
     */
    public Ciudad(String pNombreCiudad)
    {
        nombreCiudad = pNombreCiudad;
    }

    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * Método que retorna el nombre de la ciudad
     * @return nombreCiudad
     */
    public String getNombreCiudad() 
    {
        return nombreCiudad;
    }

    /**
     * Método que modifica el nombre de una ciudad
     * @param pNombreCiudad - nuevo nombre ciudad. pNombreCiudad != ""
     */
    public void setNombreCiudad(String pNuevoNombreCiudad) 
    {
        this.nombreCiudad = pNuevoNombreCiudad;
    }
    /**
     * Metodo que retorna la representación en cadena de caracteres la clase
     * @return nombre
     */
    public String toString()
    {
        return nombreCiudad;
    }
    
    
    
}