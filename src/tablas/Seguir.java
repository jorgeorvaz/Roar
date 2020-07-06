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
public class Seguir {
    private int id_seguir;
    private int fk_id_usuario;
    private int fk_id_siguiendo;

    public Seguir(int id_seguir, int fk_id_usuario, int fk_id_siguiendo) {
        this.id_seguir = id_seguir;
        this.fk_id_usuario = fk_id_usuario;
        this.fk_id_siguiendo = fk_id_siguiendo;
    }

    public int getId_seguir() {
        return id_seguir;
    }

    public void setId_seguir(int id_seguir) {
        this.id_seguir = id_seguir;
    }

    public int getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(int fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }

    public int getFk_id_siguiendo() {
        return fk_id_siguiendo;
    }

    public void setFk_id_siguiendo(int fk_id_siguiendo) {
        this.fk_id_siguiendo = fk_id_siguiendo;
    }
    
    
}
