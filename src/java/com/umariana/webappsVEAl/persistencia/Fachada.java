    /*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
    */

    package com.umariana.webappsVEAl.persistencia;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    /**
    *
    * @author Juan
    */
    public class Fachada {


            /// ---------------------------------------
            /// Constantes
            /// ---------------------------------------
            /**
            * driver conector hacia la base de datos
            */
            public final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
            /**
            * url conectora hacia la base de datos
            */
            public final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
            /**
            * usuario de la base de datos
            */
            public final static String USUARIO = "alquiler";
            /**
            * contraseña del usuario de la base de datos
            */
            public final static String PASSWORD = "alquiler";

            /// ---------------------------------------
            /// Atributos
            /// --------------------------------------- 
            /**
            * url conectora hacia la base de datos
            */
            public String url;
            /**
            * usuario de la base de datos
            */
            public String usuario;
            /**
            * contraseña del usuario de la base de datos
            */
            public String password;



            /// ---------------------------------------
            /// Constructor
            /// ---------------------------------------
            /**
            * Constructor de la clase fachada.
            */
            public Fachada()
            {
                this.url=URL;
                this.usuario= USUARIO;
                this.password = PASSWORD;
            }

            /// ---------------------------------------
            /// Metodos
            /// ---------------------------------------

            /**
            * metodo que consulta y retorna la conexion a la base de datos
            * @return miConeccion
            * @throws Exception - La clase no se encuentra
            * @throws Exception - error de sentencia sql
            */
                public Connection conectar() throws ClassNotFoundException, SQLException
                {
                    Connection miConeccion = null;
                    Class.forName(DRIVER);
                    miConeccion = DriverManager.getConnection(url, usuario, password);
                    return miConeccion;
                }

            /**
            * metodo que desconecta la conexion a la base de datos
            * @param pConeccion coneccion a la base de datos. coneccion!=null
            * @throws Exception - La clase no se encuentra
            * @throws Exception - error de sentencia sql
            */
            public void desconectar(Connection pConeccion) throws SQLException
            {
                pConeccion.close();
            }
    }
