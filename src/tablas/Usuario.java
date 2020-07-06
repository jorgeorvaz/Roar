/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 *
 * @author Jorge Ordóñez Vázquez
 */
public class Usuario {
    private int id_usuario;
    private String nick;
    private String correo;
    private int fk_pass;

    public Usuario(int id_usuario, String nick, String correo, int fk_pass) {
        this.id_usuario = id_usuario;
        this.nick = nick;
        this.correo = correo;
        this.fk_pass = fk_pass;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getFk_pass() {
        return fk_pass;
    }

    public void setFk_pass(int fk_pass) {
        this.fk_pass = fk_pass;
    }
    
    
}
