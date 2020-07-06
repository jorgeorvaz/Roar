/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jorge Ordóñez Vázquez
 */
public class ConexionBBDD {

    private Connection conexion;
    public ConexionBBDD(String bbdd)throws SQLException{
    
        String conector = "jdbc:mysql:";
        String servidorBBDD = "//localhost:3306/"; //~ 127.0.0.1:<puerto>
        String zonaHoraria = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid";
        String usuario = "root";
        String clave = "root";
        String cadenaConexion = conector + servidorBBDD + bbdd + zonaHoraria;
        conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
    }

    public Connection getConexion() {
        return conexion;
    } 
}
