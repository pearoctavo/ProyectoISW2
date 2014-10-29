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
     * Lista de los alquileres registrados en el sistema
     */
    private ArrayList<Alquiler> alquileres;
    
    /**
     * Lista de las ciudades registradas en el sistema
     */
    private ArrayList<Ciudad> ciudades;
    
    /**
     * Lista de los clientes registrados en el sistema
     */
    private ArrayList<Cliente> clientes;

    /**
     * Lista de las marcas vehiculares registradas en el sistema
     */
    private ArrayList<Marca> marcas;
    
    /**
     * Lista de los vehiculos registrados en el sistema
     */
    private ArrayList<Vehiculo> vehiculos;
    
    /**
     * Relación con la clase de persistencia para el Alquiler
     */
    private AlquilerDAO alquilerDAO;
    
    /**
     * Relación con la clase de persistencia para la Ciudad
     */
    private CiudadDAO ciudadDAO;
    
    /**
     * Relación con la clase de persistencia para el Cliente
     */
    private ClienteDAO clienteDAO;
    
    /**
     * Relación con la clase de persistencia para la Línea
     */
    private LineaDAO lineaDAO;
    
    /**
     * Relación con la clase de persistencia para la Marca
     */
    private MarcaDAO marcaDAO;
    
    /**
     * Relación con la clase de persistencia para el Vehículo
     */
    private VehiculoDAO vehiculoDAO;
    
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
            alquilerDAO = new AlquilerDAO();
            //alquileres = alquilerDAO.consultar();
            ciudadDAO = new CiudadDAO();
            ciudades = ciudadDAO.consultar();
            clienteDAO = new ClienteDAO();
            //clientes = clienteDAO.consultar();
            marcaDAO = new MarcaDAO();
            marcas = marcaDAO.consultar();
            vehiculoDAO = new VehiculoDAO();
            vehiculos = vehiculoDAO.consultar();
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /// ---------------------------------------
    /// Metodos
    /// ---------------------------------------
    /**
     * Método que adiciona un nuevo alquiler de un veículo realizado por un cliente.
     * El alquiler debe tener la información de las horas por la cual será alquilado el vehículo.
     * @param pCliente - Identificación del cliente que desea realizar el alquiler vehicular. pCliente != null
     * @param pHoras - Tiempo en horas por el cual el vehículo será alquilado. pHoras != null
     * @param pVehiculo - Placa del vehículo que será alquilado por el cliente. pVehiculo != null
     * @throws Exception Si el cliente o el vehículo no se encuentra registrado en el sistema
     */
    public void adicionarAlquiler(String pCliente, int pHoras, String pVehiculo) throws Exception
    {
        Cliente cliente = buscarCliente(pCliente);
        if (cliente != null)
        {
            Vehiculo vehiculo = buscarVehiculo(pVehiculo);
            if (vehiculo != null)
            {
                Alquiler alquiler = new Alquiler(cliente, pHoras, vehiculo);
                //alquilerDAO.agregarAlquiler(alquiler);
                alquileres.add(alquiler);
            }
            else
                throw new Exception ("No se encuentra registrado el vehículo ingresado");
        }
        else
            throw new Exception ("No se encuentra registrado el cliente ingresado");
    }
    
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
    public void adicionarCliente(String pNombre, String pApellidos, String pIdentificacion, String pTelefono, String pEmail, String pCiudad) throws Exception
    {
        Cliente cliente = buscarCliente(pIdentificacion);
        if (cliente == null)
        {
            cliente = new Cliente(pNombre, pApellidos, pIdentificacion, pTelefono, pEmail, pCiudad);
            //clienteDAO.agregarVehiculo(cliente);
            clientes.add(cliente);
        }
        else
            throw new Exception("El cliente con identificacion: " + pIdentificacion + " ya se encuetra registrado.");
    }
    
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
     * Método que retorna una ciudad dado su nombre como parametro.
     * @param pNombreCiudad- Nombre de la ciudad a buscar. pNombreciu != null.
     * @return ciudad
     * @throws Exception - Si la ciudad no existe
     */
    public Ciudad buscarCiudad(String pNombreCiudad) throws Exception
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
    }
    
    /**
     * Método que busca a un cliente en el sistema dada su identificación com oparametro
     * @param pIdentificacion - Identificación del cliente a buscar. pIdentificación != null
     * @return cliente
     */
    public Cliente buscarCliente(String pIdentificacion)
    {
        Cliente cliente = null;
        boolean existe = false;
        for (int i=0; i<clientes.size() && !existe; i++)
        {
            if (clientes.get(i).getIdentificacion().equals(pIdentificacion))
            {
                cliente = clientes.get(i);
                existe = true;
            }
        }
        return cliente;
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
     * Método que elimina una ciudad del sistema dado su nombre como parametro
     * @param pCiudad - Nombre de la ciudad a eliminar. pCiudad != null
     * @throws Exception Si la ciudad no existe o no esta registrada
     */
    public void eliminarCiudad(String pCiudad) throws Exception
    {
        Ciudad ciudad = buscarCiudad(pCiudad);
        if (ciudad != null)
        {
            //ciudadDAO.eliminarCiudad(ciudad);
            ciudades.remove(ciudad);
        }
        else
            throw new Exception ("No se encuentra la ciudad registrada");
    }
    
    /**
     * Método que eliminar un cliente del sistema dado como parametro la identificacion.
     * @param pIdentificacion - identificacion del cliente. pIdentificacion != null.
     * @throws Exception - El cliente no existe.
     */
    public void eliminarCliente(String pIdentificacion) throws Exception
    {
        Cliente cliente = buscarCliente(pIdentificacion);
        if (cliente != null)
        {
            //clienteDAO.eliminarMarca(cliente);
            clientes.remove(cliente);
        }
        else
            throw new Exception("El cliente con identificacion: " + pIdentificacion + " no se encuetra registrada.");
    }
    
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
     * Método que modifica una ciudad en el sistema
     * @param pCiudad - Ciudad a la que se desea modificar. pCiudad != null
     * @param pNombre - Nuevo nombre de la ciudad. pNombre != null
     * @throws Exception Si la ciudad no existe o no esta registrada
     */
    public void modificarCiudad(String pCiudad, String pNombre) throws Exception
    {
        Ciudad ciudad = buscarCiudad(pCiudad);
        if (ciudad != null)
        {
            ciudad = buscarCiudad(pNombre);
            if (ciudad == null)
            {
                ciudad.setNombreCiudad(pNombre);
                //ciudadDAO.modificarCiudad(ciudad);
            }
            else
                throw new Exception ("Ya existe ciudad con el nombre ingresado");
        }
        else
            throw new Exception ("No se encuentra la ciudad registrada");
    }
    
    /**
     * Método que modifica o actualiza la información de un cliente que se encuentra en sistema
     * @param pApellidos - Apellidos actuales o modificados del cliente. pApellidos != null
     * @param pCiudad - Ciudad actual o modificada del cliente. pCiudad != null
     * @param pEmail - Email actual o modificado del cliente. pEmail != null
     * @param pIdentificacion - Identificación del cliente a modificar o actualizar información. pApellidos != null
     * @param pNombres - Nombres actuales o modificados del cliente. pNombres != null
     * @param pTelefono - Tleléfono actual o modificado del cliente. pTelefono != null
     * @throws Exception 
     */
    public void modificarCliente(String pApellidos, String pCiudad, String pEmail, String pIdentificacion, String pNombres, String pTelefono) throws Exception
    {
        Cliente cliente = buscarCliente(pIdentificacion);
        if (cliente!=null)
        {
            cliente.setApellidos(pApellidos);
            cliente.setCiudad(pCiudad);
            cliente.setEmail(pEmail);
            cliente.setIdentificacion(pIdentificacion);
            cliente.setNombres(pNombres);
            cliente.setTelefono(pTelefono);
            //clienteDAO.modificarCliente(cliente);
        }
        else
            throw new Exception ("No se encuentra el cliente registrado");
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
     * Método que retorna la lista de las ciudades registradas en el sistema
     * @return ciudades
     */
    public ArrayList<Ciudad> darListaCiudades()
    {
       return ciudades;
    }
    
    /**
     * Método que retorna la lista de los clientes registrados en el sistema
     * @return clientes
     */
    public ArrayList<Cliente> darListaClientes()
    {
        return clientes;
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
}