/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBBDD;

import interfazGrafica.Form_Usuarios;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tablas.Mensaje;

/**
 *
 * @author Jorge Ordóñez Vázquez
 */
public class Gestion_Datos {

    private Connection c;

    public Gestion_Datos(Connection c) {
        this.c = c;
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    
    
    //Métodos usuario
    public void insertar_usuario(String consulta, String nick, String email, int id_pass) {
        try {
            PreparedStatement update_datos;
            update_datos = this.c.prepareStatement("INSERT INTO USUARIO (nick, correo, pass) VALUES(?, ?, ?)");
            update_datos.setString(1, nick);
            update_datos.setString(2, email);
            update_datos.setInt(3, id_pass);
            update_datos.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar_usuario(Consulta consulta, String nick, String email) {
        try {
            PreparedStatement update_datos;
            update_datos = this.c.prepareStatement("update usuario set nick = ?, correo = ? where id_usuario = ?");
            update_datos.setString(1, nick);
            update_datos.setString(2, email);
            update_datos.setInt(3, consulta.getResultado().getInt(1));
            update_datos.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int buscar_usuario(String nick) throws SQLException {
        Consulta consulta_usuario = new Consulta(c, "select id_usuario from usuario where nick = " + "'" + nick + "'");
        int ultimo_id = 0;
        if (consulta_usuario.getResultado().next()) {
            ultimo_id = consulta_usuario.getResultado().getInt(1);
        }
        return ultimo_id;
    }

    public void borrar_usuario(int id_usuario, int id_password) throws SQLException {
        borrar_mensajes_usuario(id_usuario);
        borrar_seguidos_seguidores(id_usuario);
        borrar_password(id_password);
    }

    public boolean validar_usuario(String nick, String password) throws SQLException {
        if (buscar_usuario(nick) != 0) {
            Consulta consulta_id_pass = new Consulta(c, "select pass from usuario where nick = " + "'" + nick + "'");
            int id_pass_usuario = 0;
            if (consulta_id_pass.getResultado().next()) {
                id_pass_usuario = consulta_id_pass.getResultado().getInt(1);
            }
            Consulta consulta_pass = new Consulta(c, "select pass from pass where id_password = " + id_pass_usuario);
            String password_autentico = "";
            if (consulta_pass.getResultado().next()) {
                password_autentico = consulta_pass.getResultado().getString(1);
            }

            return password.equals(password_autentico);

        } else {
            return false;
        }

    }

    public void borrar_usuario_nick(){
        String usuario_borrar = JOptionPane.showInputDialog(null, "Introduce Usuario", "Usuario", 3);
        String password = JOptionPane.showInputDialog(null, "Introduce Contraseña", "Contraseña", 3);
        Gestion_Datos gd = new Gestion_Datos(c);
        try {
            if(gd.validar_usuario(usuario_borrar, password)){
                int id_usuario = gd.buscar_usuario(usuario_borrar);
                Consulta consulta = new Consulta(c, "select pass from usuario where id_usuario = " + id_usuario);
                if (consulta.getResultado().next()){
                    int id_pass = consulta.getResultado().getInt(1);
                    gd.borrar_usuario(id_usuario, id_pass);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Métodos password
    public void insertar_password(String pass) {
        try {
            PreparedStatement update_datos;
            update_datos = this.c.prepareStatement("INSERT INTO PASS" + "(pass) VALUES(?)");
            update_datos.setString(1, pass);
            update_datos.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar_password(int id, String pass) {
        try {
            PreparedStatement update_datos;
            update_datos = this.c.prepareStatement("update pass set pass = ? where id_password = ?");
            update_datos.setString(1, pass);
            update_datos.setInt(2, id);
            update_datos.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int ultimo_password() throws SQLException {
        Consulta consulta_ultimo = new Consulta(c, "select id_password from pass order by id_password desc limit 1");
        int ultimo_id = -1;
        if (consulta_ultimo.getResultado().next()) {
            ultimo_id = consulta_ultimo.getResultado().getInt(1);
        }
        return ultimo_id;
    }

    private void borrar_password(int id_pass) throws SQLException {
        PreparedStatement update_datos;
        update_datos = this.c.prepareStatement("delete from pass where id_password = ?");
        update_datos.setInt(1, id_pass);
        update_datos.executeUpdate();
    }

    
    
    //Métodos mensaje
    public void insertar_mensaje(String nick, String password, String mensaje) throws SQLException {
        if (validar_usuario(nick, password)) {
            Mensaje msg = new Mensaje(buscar_usuario(nick), mensaje);
            PreparedStatement update_datos;
            update_datos = c.prepareStatement("insert into mensaje(id_usuario, mensaje, hora, fecha) values(?,?,?,?)");
            update_datos.setInt(1, msg.getFk_id_usuario());
            update_datos.setString(2, msg.getMensaje());
            update_datos.setTime(3, Time.valueOf(msg.getHora()));
            update_datos.setDate(4, Date.valueOf(msg.getFecha()));
            update_datos.executeUpdate();
            JOptionPane.showMessageDialog(null, "Mensaje publicado.");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR. Login failed.");
        }

    }

    public Consulta buscar_mensajes(String nick) throws SQLException {
        int id_nick = buscar_usuario(nick);
        Consulta consulta = null;
        if (id_nick != 0) {
            consulta = new Consulta(c, "select mensaje, hora, fecha from mensaje where id_usuario = " + id_nick);
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
        
        return consulta;
    }

    public void borrar_mensaje(int id_mensaje) throws SQLException {
        PreparedStatement update_datos;
        update_datos = this.c.prepareStatement("delete from mensaje where id_mensaje = ?");
        update_datos.setInt(1, id_mensaje);
        update_datos.executeUpdate();
    }
    
    private void borrar_mensajes_usuario(int id_usuario) throws SQLException{
        PreparedStatement update_datos;
        update_datos = this.c.prepareStatement("delete from mensaje where id_usuario = ?");
        update_datos.setInt(1, id_usuario);
        update_datos.executeUpdate();
    }

    
    
    //Métodos tabla seguidores
    public void dejar_de_seguir(JTable tabla, String borrado) throws SQLException {
        DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
        if (tabla.getSelectedRow() != -1) {
            int id_seleccionada = (int) tm.getValueAt(tabla.getSelectedRow(), 0);
            PreparedStatement update_datos;
            update_datos = this.c.prepareStatement(borrado);
            update_datos.setInt(1, id_seleccionada);
            update_datos.executeUpdate();
            tm.removeRow(tabla.getSelectedRow());
        }
    }

    public boolean comprobar_seguidor(int id_sigue, int id_seguido) throws SQLException {
        Consulta consulta = new Consulta(c, "select id_seguir from seguir where "
                + "id_siguiendo = " + id_sigue
                + " and id_seguidor = " + id_seguido);
        return consulta.getResultado().next();
    }

    public void seguir_a(String nick_sigue, String nick_seguido) {
        try {
            int id_sigue = buscar_usuario(nick_sigue);
            int id_seguido = buscar_usuario(nick_seguido);
            if (!comprobar_seguidor(id_sigue, id_seguido)) {
                PreparedStatement update_datos;
                update_datos = this.c.prepareStatement("insert into seguir (id_siguiendo, id_seguidor) values(?,?)");
                update_datos.setInt(1, id_sigue);
                update_datos.setInt(2, id_seguido);
                update_datos.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Consulta buscar_seguidores(String nick, String opcion) throws SQLException {
        Consulta consulta=null;
        if (buscar_usuario(nick) != 0) {

            switch (opcion) {
                case "Seguidores":
                    consulta = new Consulta(c, "select nick from usuario, seguir "
                            + "where id_usuario = id_siguiendo and id_seguidor = " + buscar_usuario(nick));
                    break;
                case "Usuarios seguidos":
                    consulta = new Consulta(c, "select nick from usuario,seguir where "
                            + "id_usuario = id_seguidor and id_siguiendo = " + buscar_usuario(nick));
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }

        return consulta;
    }
    
    public void borrar_seguidos_seguidores(int id_usuario) throws SQLException{
        
            PreparedStatement update_datos;
            update_datos = this.c.prepareStatement("delete from seguir "
                    + "where id_siguiendo = ? or id_seguidor = ?");
            update_datos.setInt(1, id_usuario);
            update_datos.setInt(2, id_usuario);
            update_datos.executeUpdate();
        
    }

    //Métodos borrar registros tablas
    public void borrar_registro_usuario(JTable tabla, String borrado) {
        DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
        if (tabla.getSelectedRow() != -1) {
            try {
                int id_seleccionada = (int) tm.getValueAt(tabla.getSelectedRow(), 0);
                Consulta consulta = new Consulta(c, borrado + id_seleccionada);
                int id_password = -1;
                if (consulta.getResultado().next()) {
                    id_password = consulta.getResultado().getInt(1);
                }
                Gestion_Datos gd = new Gestion_Datos(c);
                gd.borrar_usuario(id_seleccionada, id_password);
                tm.removeRow(tabla.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(Form_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void borrar_tabla(JTable tabla, String [] columnas){
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    columnas
            ) {
                Class[] types = new Class[]{
                    java.lang.String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
            });
    }
}
