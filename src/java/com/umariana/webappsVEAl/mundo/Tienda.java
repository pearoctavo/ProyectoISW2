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
 * Clase principal de la aplciación - webappsVEAl
 */
public class Tienda 
{
    /// ---------------------------------------
    /// Atributos
    /// ---------------------------------------
    /**
     * Lista de las marcas vehiculares registradas en el sistema
     */
    private ArrayList<Marca> marcas;
    
    /**
     * Lista de los vehiculos registrados en el sistema
     */
    private ArrayList<Vehiculo> vehiculos;
    
    
    private LineaDAO lineaDAO;
    
    private MarcaDAO marcaDAO;
    
    private VehiculoDAO vehiculoDAO;
    
    //private ClienteDAO clienteDAO;
    
    //private CiudadDAO ciudadDAO;
    
    /// ---------------------------------------
    /// Constructor
    /// ---------------------------------------
    /**
     * Constructor de la clase principal.
     */
    public Tienda()
    {        
        try 
        {
            marcaDAO = new MarcaDAO();
            marcas = marcaDAO.consultar();
            vehiculoDAO = new VehiculoDAO();
            vehiculos = vehiculoDAO.consultar();
            //clientes = clienteDAO.consultar();
            //ciudades = ciudadDAO.consultar();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        //marcas = new ArrayList<Marca>();
        
        //vehiculos = new ArrayList<Vehiculo>();
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    
    /**
     * Método que adiciona un nuevo cliente al sistema
     * @param pNombre - nombre del cliente que se quiere adicionar. pNombre != null 
     * @param pApellidos - Apellidos del cliente. pApellidos != null.
     * @param pIdentificacion - identificacion del cliente. pIdentificacion != null.
     * @param pTelefono - Telefono del cliente. pTelefono != null.
     * @param pEmail - email del cliente. pEmail != null.
     * @param pCiudad - Ciudad donde reside el cliente. pCiudad != null.
     * @throws Exception - El cliente ya existe
     */
    
   /**public void adicionarCliente(String pNombre, String pApellidos, String pIdentificacion, String pTelefono, String pEmail, String pCiudad) throws Exception
    {
        Cliente cliente = buscarCliente(pIdentificacion);
        if (cliente == null)
        {
            cliente = new Cliente(pNombre, pApellidos, pIdentificacion, pTelefono, pEmail, pCiudad);
            clienteDAO.agregarVehiculo(cliente);
            clientes.add(cliente);
        }
        else
            throw new Exception("El cliente con identificacion: " + pIdentificacion + " ya se encuetra registrado.");
    }**/
    
    /**
     * Método que adiciona una nueva línea de marca vehicular al sistema
     * @param pMarca - Marca de la línea. pMarca != null
     * @param pNombre - Nombre de la línea. pNombre != null
     * @throws Exception - La línea ya existe
     */
    public void adicionarLinea(String pMarca, String pNombre) throws Exception
    {
        if (buscarLinea(pNombre) == null)
        {
            Marca marca = buscarMarca(pMarca);
            if (marca != null)
            {
                marca.adicionarLinea(pNombre);
            }
            else
                throw new Exception("La marca: " + pMarca + " no se encuentra registrada.");
        }
        else
            throw new Exception("La línea: " + pNombre + " ya se encuentra registrada.");
    }
    
    /**
     * Método que adiciona una nueva marca vehicular al sistema
     * @param pNombre - Nombre de la marca. pNombre != null
     * @param pPais - País del origen de la marca. pPais != null
     * @throws Exception - La marca ya existe
     */
    public void adicionarMarca(String pNombre, String pPais) throws Exception
    {
        Marca marca = buscarMarca(pNombre);
        if (marca == null)
        {            
            marca = new Marca(pNombre, pPais);
            marcaDAO.agregarMarca(marca);
            marcas.add(marca);
        }
        else
            throw new Exception("La marca con nombre: " + pNombre + " ya se encuentra registrada.");
    }
    
    /**
     * Método que adiciona un nuevo vehículo al sistema
     * @param pCosto - Costo, valor o precio del vehículo en el mercado. pCosto != null && pCosto > 0.
     * @param pImagen - Imagen del vehículo. pImagen != null.
     * @param pLinea - Linea de la marca vehicular. pLinea != null.
     * @param pMarca - Marca vehicular. pMarca != null.
     * @param pModelo - Modelo o año en que el vehículo fue fabricado. pModelo != null.
     * @param pPlaca - Placa del vehículo. pPlaca != null.
     * @throws Exception - El vehículo ya existe
     */
    public void adicionarVehiculo(double pCosto, String pImagen, String pLinea, String pMarca, String pModelo, String pPlaca) throws Exception
    {
        Vehiculo vehiculo = buscarVehiculo(pPlaca);
        if (vehiculo == null)
        {
            vehiculo = new Vehiculo(pCosto, pImagen, pLinea, pMarca, pModelo, pPlaca);
            vehiculoDAO.agregarVehiculo(vehiculo);
            vehiculos.add(vehiculo);
        }
        else
            throw new Exception("El vehículo con placa: " + pPlaca + " ya se encuetra registrado.");
    }
    
    /**
     * Método que retorna una linea dado su nombre como parametro.
     * @param pNombre - Nombre de la línea. pNombre != null.
     * @return linea
     * @throws Exception - Si la marca no existe
     */
    public Linea buscarLinea(String pNombre) throws Exception
    {
        Linea linea = null;
        boolean encontrado = false;
        for (int i=0; i<marcas.size()&&!encontrado; i++)
        {
            linea = marcas.get(i).buscarLinea(pNombre);
            if (linea != null)
                encontrado = true;
        }
        return linea;
    }
    
    /**
     * Método que retorna una linea dado su nombre como parametro.
     * @param pMarca - Nombre de la marca a la que pertenece la línea. pMarca != null.
     * @param pNombre - Nombre de la línea. pNombre != null.
     * @return linea
     * @throws Exception - Si la marca no existe
     */
    public Linea buscarLineaPorMarca(String pMarca, String pNombre) throws Exception
    {
        Linea linea = null;
        Marca marca = buscarMarca(pMarca);
        {
            if (marca != null)
            {
                linea = marca.buscarLinea(pNombre);
            }
            else
                throw new Exception("La marca: " + pMarca + " no se encuentra registrada.");
        }
        return linea;
    }
    
    /**
     * Método que retorna una linea dado su nombre como parametro.
     * @param pNombre - Nombre de la marca. pNombre != null.
     * @return marca
     */
    public Marca buscarMarca(String pNombre)
    {
        Marca marca = null;
        boolean encontrado = false;
        for (int i=0; i<marcas.size() && !encontrado; i++)
        {
            if (marcas.get(i).getNombreMarca().equals(pNombre))
            {
                marca = marcas.get(i);
                encontrado = true;
            }
        }
        return marca;
    }
    
    
    /**
     * Método que retorna una ciudad dado su nombre como parametro.
     * @param pNombreCiudad- Nombre de la ciudad a buscar. pNombreciu != null.
     * @return ciudad
     * @throws Exception - Si la ciudad no existe
     */
    /**public Ciudad buscarCiudad(String pNombreCiudad) throws Exception
    {
        Ciudad ciudad = null;
        boolean encontrado = false;
        for (int i=0; i<ciudades.size() && !encontrado; i++)
        {
            if (ciudades.get(i).getNombreCiudad().equals(pNombreCiudad))
            {
                ciudad = ciudades.get(i);
                encontrado = true;
            }
        }
        return ciudad;
    }*/
    
    /**
     * Método que retorna un vehículo dada su placa como parametro
     * @param pPlaca - Placa del vehículo a buscar. pPlaca != null.
     * @return vehiculo
     * @throws Exception - Si la linea no existe || Si la marca no existe 
     */
    public Vehiculo buscarVehiculo(String pPlaca) throws Exception
    {
        Vehiculo vehiculo = null;
        boolean encontrado = false;
        for (int i=0; i<vehiculos.size()&&!encontrado; i++)
        {
            if(vehiculos.get(i).getPlaca().equals(pPlaca))
            {
                vehiculo = vehiculos.get(i);
                encontrado = true;
            }
        }
        return vehiculo;
    }
    
    
    /**
     * Método que eliminar un cliente del sistema dado como parametro la identificacion.
     * @param pIdentificacion - identificacion del cliente. pIdentificacion != null.
     * @throws Exception - El cliente no existe.
     */
    /**public void eliminarCliente(String pIdentificacion) throws Exception
    {
        Cliente cliente = buscarCliente(pIdentificacion);
        if (cliente != null)
        {
            clienteDAO.eliminarMarca(cliente);
            clientes.remove(cliente);
        }
        else
            throw new Exception("El cliente con identificacion: " + pIdentificacion + " no se encuetra registrada.");
    }*/
    
    /**
     * Método que elimina una línea de marca vehicular del sistema según su nombre.
     * @param pMarca - Marca a la que la línea pertenece. pMarca != null.
     * @param pNombre - Nombre de la línea. pNombre != null.
     * @throws Exception - La marca no existe || La línea no existe.
     */
    public void eliminarLinea(String pMarca, String pNombre) throws Exception
    {
        Marca marca = buscarMarca(pMarca);
        if (marca != null)
        {
            marca.eliminarLinea(pNombre);
        }
        else
            throw new Exception("La marca: " + pMarca + " no se encuetra registrada.");
    }
    
    /**
     * Método que elimina una marca vehicular del sistema según su nombre.
     * @param pNombre - Nombre de la marca. pNombre != null.
     * @throws Exception - La marca no existe.
     */
    public void eliminarMarca(String pNombre) throws Exception
    {
        Marca marca = buscarMarca(pNombre);
        if (marca != null)
        {
            marcaDAO.eliminarMarca(marca);
            marcas.remove(marca);
        }
        else
            throw new Exception("La marca: " + pNombre + " no se encuetra registrada.");
    }
    
    /**
     * Método que elimina un vehículo del sistema según su placa.
     * @param pPlaca - Placa del vehículo. pPlaca != null.     
     * @throws Exception - El vehículo no existe.
     */
    public void eliminarVehiculo(String pPlaca) throws Exception
    {
        Vehiculo vehiculo = buscarVehiculo(pPlaca);
        if (vehiculo != null)
        {
            vehiculoDAO.eliminarVehiculo(vehiculo);
            vehiculos.remove(vehiculo);
        }
        else
            throw new Exception("El vehículo con placa: " + pPlaca + " no se encuetra registrado.");
    }
    
    /**
     * Método que módifica los datos de una línea. 
     * Para su modificación es necesario conocer la marca a la que pertenece la línea, la línea que se desea modificar y los nuevos valores de la línea.
     * @param pLinea - Nombre de la linea que se desea modificar. pLinea != null.
     * @param pMarca - Nombre de la marca a la que pertenece la línea. pMarca != null.
     * @param pNombre - Nombre modificado de la línea.
     * @throws Exception - La marca no existe || La línea no exise
     */
    public void modificarLinea(String pLinea, String pMarca, String pNombre) throws Exception
    {
        Marca marca = buscarMarca(pMarca);
        if (marca != null)
        {
            Linea linea = buscarLinea(pNombre);
            if (linea == null)
            {
                marca.modificarLinea(pLinea, pNombre);
            }
            else
                throw new Exception("La línea: " + pNombre + " ya se encuetra registrada.");
        }
        else
            throw new Exception("La marca: " + pMarca + " no se encuetra registrada.");
    }
    
    /**
     * Método que modifica los datos de una marca.
     * Para su modificación es ncesario conocer la marca a la que se desea modificar y los nuevos valores de la marca.
     * @param pMarca - Nombre de la marca que se desa modificar. pMarca != null.
     * @param pNombre - Nombre modificado de la marca. pNombre != null.
     * @param pPaisOrigen - País modificado de la marca. pPais != null.
     * @throws Exception - La marca no existe || El nuevo nombre de la marca ya se encuentra reistrado
     */
    public void modificarMarca(String pMarca, String pNombre, String pPaisOrigen) throws Exception
    {
        Marca marca = buscarMarca(pMarca);
        if (marca != null)
        {
            Marca nMarca = buscarMarca(pNombre);
            if (nMarca == null)
            {
                marca.setNombreMarca(pNombre);
                marca.setPaisOrigen(pPaisOrigen);
                marcaDAO.modificarMarca(marca, pMarca);
            }
            else
                throw new Exception("La marca con nombre: " + pNombre + " ya se encuentra registrada.");
        }
        else
            throw new Exception("La marca: " + pMarca + " no se encuetra registrada.");
    }
    
    /**
     * Método que modifica los datos de un vehículo.     
     * @param pCosto - Costo modificado del vehículo. pCosto != null.
     * @param pImagen - Imagen modificada del vehículo. pImagen != null.
     * @param pLinea - Linea modificada del vehículo. pLinea != null.
     * @param pMarca - Marca modificada del vehículo. pMarca != null.
     * @param pModelo - Modelo modificado del vehículo. pModelo != null.
     * @param pPlaca - Placa modificada del vehículo. pPlaca != null.
     * @param pVehiculo - Vehículo que se desea modificar. actualPlaca != null.
     * @throws Exception - El vehículo no existe || La nueva placa del vehículo ya se encuentra registrada
     */
    public void modificarVehiculo(double pCosto, String pImagen, String pLinea, String pMarca, String pModelo, String pPlaca, String pVehiculo) throws Exception
    {
        Vehiculo vehiculo = buscarVehiculo(pVehiculo);
        if (vehiculo != null)
        {
            Vehiculo nVehiculo = buscarVehiculo(pPlaca);
            if (nVehiculo == null)
            {
                Marca marca = buscarMarca(pMarca);
                if (marca != null)
                {
                    Linea linea = marca.buscarLinea(pLinea);
                    if (linea != null)
                    {
                        vehiculo.setCosto(pCosto);
                        vehiculo.setImagen(pImagen);
                        vehiculo.setLinea(pLinea);
                        vehiculo.setMarca(pMarca);
                        vehiculo.setModelo(pModelo);
                        vehiculo.setPlaca(pPlaca);
                        vehiculoDAO.modificarVehiculo(pVehiculo, vehiculo);
                    }
                    else
                        throw new Exception("La linea: " + pLinea + " no se encuetra registrado o no pertenece a la marca seleccionada.");
                }
                else
                    throw new Exception("ELa marca: " + pMarca + " no se encuetra registrado.");
            }
            else
                throw new Exception("El vehículo con placa: " + pPlaca + " ya se encuetra registrado.");
        }
        else
            throw new Exception("El vehículo con placa: " + pPlaca + " no se encuetra registrado.");
    }
    
    /**
     * Método que retorna la lista de las marcas registradas en el sistema
     * @return marcas
     */
    public ArrayList<Marca> darListaMarcas()
    {
        return marcas;
    }
    
    /**
     * Método que retorna la lista de vehiculos registrados en el sistema
     * @return vehiculos
     */
    public ArrayList<Vehiculo> darListaVehiculos()
    {
        return vehiculos;
    }
    
/**
     * Método que retorna la lista de los clientes registrados en el sistema
     * @return clientes
     */
    /**public ArrayList<Cliente> darListaClientes()
    {
        return clientes;
    }
    
    
   /**
     * Método que retorna la lista de las ciudades registrados en el sistema
     * @return ciudades
     */
   /** public ArrayList<Ciudad> darListaCiudades()
    {
       return ciudades;
    }*/
}
