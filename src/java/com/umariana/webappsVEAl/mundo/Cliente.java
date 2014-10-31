/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.webappsVEAl.mundo;

/**
 *
 * clase que representa un cliente
 */
public class Cliente {
    
    ///----------------------------------------------
    /// Atributos
    ///----------------------------------------------
    /**
     * apellidos del cliente
     */
    private String  apellidos;
    /**
     * ciudad del cliente
     */
    private String ciudad;
    /**
     * email del cliente
     */
    private String email;
    /**
     * identificacion unica del cliente
     */
    private String identificacion;
    /**
     * nombres del cliente
     */
    private String nombres;
    /**
     * telefono del cliente
     */
    private String telefono;
    
    ///----------------------------------------------
    /// Atributos
    ///----------------------------------------------
    
    /**
    * Constructor de la clase cliente
    * @param pApellidos apellidos del cliente. apellidos !="" and apellidos !=null
    * @param pCiudad ciudad del cliente. ciudad !="" and ciudad !=null
    * @param pEmail email del cliente. email !="" and email !=null
    * @param pIdentificacion identificacion del cliente. identificacion !="" and identificacion !=null
    * @param pNombres nombres del cliente. nombres !="" and nombres !=null
    * @param pTelefono telefono del cliente. telefono !="" and telefono !=null
    */
    public Cliente (String pApellidos,String pCiudad,String pEmail,String pIdentificacion,String pNombres,String pTelefono)
    {
        apellidos=pApellidos;
        ciudad=pCiudad;
        email=pEmail;
        identificacion=pIdentificacion;
        nombres=pNombres;
        telefono=pTelefono;
    }
    
    
    ///----------------------------------------------
    /// Metodos
    ///----------------------------------------------
    
    /**
     * metodo que retorna los apellidos
     * @return los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * metodo que cambia los apellidos
     * @param pApellidos apellidos del cliente. apellidos !="" and apellidos !=null
     */
    public void setApellidos(String pApellidos) {
        apellidos = pApellidos;
    }

    /**
     * metodo que retorna la ciudad
     * @return la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * metodo que cambia la ciudad
     * @param pCiudad ciudad del cliente. ciudad !="" and ciudad !=null
     */
    public void setCiudad(String pCiudad) {
        this.ciudad = pCiudad;
    }

    /**
     * metodo que retorna el email
     * @return el email
     */
    public String getEmail() {
        return email;
    }

    /**
     * metodo que cambia el email
     * @param pEmail email del cliente. email !="" and email !=null
     */
    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    /**
     * metodo que retorna la identificacion
     * @return la identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * metodo que cambia la identificacion
     * @param pIdentificacion identificacion del cliente. identificacion !="" and identificacion !=null
     */
    public void setIdentificacion(String pIdentificacion) {
        this.identificacion = pIdentificacion;
    }

    /**
     * metodo que retorna los nombres
     * @return los nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * metodo que cambia los nombres
     * @param pNombres nombres del cliente. nombres !="" and nombres !=null
     */
    public void setNombres(String pNombres) {
        this.nombres = pNombres;
    }

    /**
     * metodo que retorna el telefono
     * @return el telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * metodo que cambia el telefono
     * @param pTelefono telefono del cliente. telefono !="" and telefono !=null
     */
    public void setTelefono(String pTelefono) {
        this.telefono = pTelefono;
    }
    
    /**
     * Metodo que retorna la representación en cadena de caracteres la clase
     * @return identificacion
     */
    public String toString()
    {
        return identificacion;
    }
}
