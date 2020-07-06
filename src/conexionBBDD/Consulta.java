/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jorge Ordóñez Vázquez
 */
public class Consulta {
    private Connection conexion;
    private ResultSet resultado;

    public Consulta(Connection conexion, String consulta)throws SQLException {
        this.conexion = conexion;
        Statement ejecutar = conexion.createStatement();
        resultado = ejecutar.executeQuery(consulta);
    }

    public ResultSet getResultado() {
        return resultado;
    }   
}