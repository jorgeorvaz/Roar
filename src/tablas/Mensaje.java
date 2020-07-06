/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Jorge Ordóñez Vázquez
 */
public class Mensaje {
    private int id_mensaje;
    private int fk_id_usuario;
    private String mensaje;
    private LocalTime hora;
    private LocalDate fecha;

    public Mensaje(int id_mensaje, int fk_id_usuario, String mensaje, LocalTime hora, LocalDate fecha) {
        this.id_mensaje = id_mensaje;
        this.fk_id_usuario = fk_id_usuario;
        this.mensaje = mensaje;
        this.hora = LocalTime.now();
        this.fecha = LocalDate.now();
    }
    
    public Mensaje (int fk_id_usuario, String mensaje){
        this.fk_id_usuario = fk_id_usuario;
        this.mensaje = mensaje;
        this.hora = LocalTime.now();
        this.fecha = LocalDate.now();
    }

    public int getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public int getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(int fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalTime getHora() {
        return hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
}
