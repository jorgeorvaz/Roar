/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import Principal.LiberarMemoria;
import conexionBBDD.Consulta;
import conexionBBDD.Gestion_Datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Ordóñez Vázquez
 */
public class Form_Insertar_Usuario extends javax.swing.JFrame {

    private Connection c;
    private int posicion;
    private Consulta consulta;

    /**
     * Creates new form Form_Insertar_Usuario
     */
    public Form_Insertar_Usuario(Connection c) {
        initComponents();
        this.c = c;
        this.posicion = -1;
        this.setLocationRelativeTo(null);
    }

    public Form_Insertar_Usuario(Connection c, int posicion, Consulta consulta) throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.c = c;
        this.posicion = posicion;
        this.consulta = consulta;
        if (consulta.getResultado().next()) {
            txt_nick.setText(consulta.getResultado().getString(2));
            txt_email.setText(consulta.getResultado().getString(3));
            Consulta password = new Consulta(c, "select pass from pass where id_password = " + consulta.getResultado().getString(4));
            if (password.getResultado().next()) {
                txt_pass.setText(password.getResultado().getString(1));
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nick = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nick");

        jLabel2.setText("Email");

        jLabel3.setText("Contraseña");

        btn_aceptar.setText("Aceptar");
        btn_aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_aceptarMouseClicked(evt);
            }
        });

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_email)
                            .addComponent(txt_nick, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(txt_pass)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelar)))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_aceptar))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_cancelarMouseClicked

    private void btn_aceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aceptarMouseClicked
        if (posicion == -1) {
            try {
                Gestion_Datos gd = new Gestion_Datos(c);
                gd.insertar_password(new String(txt_pass.getPassword()));
                gd.insertar_usuario("INSERT INTO USUARIO (nick, correo, pass) VALUES(?, ?, ?)",
                        txt_nick.getText(), txt_email.getText(), gd.ultimo_password());

            } catch (SQLException ex) {
                Logger.getLogger(Form_Insertar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Gestion_Datos gd = new Gestion_Datos(c);
                gd.actualizar_usuario(consulta, txt_nick.getText(), txt_email.getText());
                gd.actualizar_password(this.consulta.getResultado().getInt(4), new String(txt_pass.getPassword()));
            } catch (SQLException ex) {
                Logger.getLogger(Form_Insertar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        LiberarMemoria.pasarGarbageCollector();
        this.dispose();
    }//GEN-LAST:event_btn_aceptarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nick;
    private javax.swing.JPasswordField txt_pass;
    // End of variables declaration//GEN-END:variables
}
