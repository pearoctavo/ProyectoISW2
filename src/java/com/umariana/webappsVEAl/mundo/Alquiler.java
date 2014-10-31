package com.umariana.webappsVEAl.mundo;

/**
 * Clase que representa el alquiler de un vehículo por parte de un cliente
 * @author Pear Team
 */
public class Alquiler 
{
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
     * Cliente que alquila un vehículo
     */
    private String cliente;
    
    /**
     * Número de horas que el vehículo es alquilado
     */
    private int horas;
    
    /**
     * Vehículo que ha sido alquilado por un cliente
     */
    private String vehiculo;

    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
     * Constructor del objeto cliente
     * @param pCliente - Cliente del vehiculo. pCliente != null
     * @param pHoras - Número de horas que el vehículo es alquilado. pHoras != null
     * @param pVehiculo - Vehíclo alquilado. pVehiculo != null
     */
    public Alquiler(String pCliente, int pHoras, String pVehiculo)
    {
        cliente = pCliente;
        horas = pHoras;
        vehiculo = pVehiculo;
    }

    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * Método que retorna el cliente que alquila el vehículo
     * @return cliente
     */
    public String getCliente() 
    {
        return cliente;
    }

    /**
     * Método que modifica el cliente que alquila el vehículo
     * @param pCliente - Cliente del vehiculo. pCliente != null
     */
    public void setCliente(String pCliente) 
    {
        this.cliente = pCliente;
    }

    /**
     * Método que retorna el número de horas del vehículo alquilado
     * @return horas
     */
    public int getHoras() 
    {
        return horas;
    }

    /**
     * Método que modifica el número de horas del vehículo alquilado
     * @param pHoras - Número de horas que el vehículo es alquilado. pHoras != null 
     */
    public void setHoras(int pHoras) 
    {
        this.horas = pHoras;
    }

    /**
     * Método que retorna el vehículo alquilado 
     * @return vehiculo
     */
    public String getVehiculo() 
    {
        return vehiculo;
    }

    /**
     * Método que retorna el vehículo alquilado
     * @param pVehiculo - Vehíclo alquilado. pVehiculo != null
     */
    public void setVehiculo(String pVehiculo) 
    {
        this.vehiculo = pVehiculo;
    }
}