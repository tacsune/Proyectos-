/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Clases.conexion.getConexion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Carlos
 */
public class estadisticas extends javax.swing.JFrame {
    
    JPanel panel=new JPanel();
    JPanel panel2= new JPanel();
    
    JLabel n_prestamos = new JLabel();
    JLabel porcentaje = new JLabel();
    JLabel n_disponibles = new JLabel();
    JLabel n_ocupados = new JLabel();
    JLabel n_problems = new JLabel();
    JLabel n_sinProblems = new JLabel();
    
    PreparedStatement ps;
    ResultSet rs;
    
    int numero=0; // numero del equipo cliqueado
    
    public estadisticas() {
        
        paneles();
        botones();
        textos();
        barra();
        //setSize(2000,2000);
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public void auxiliarEventosBoton(){
        
        Connection conexion= null;
        
        try{
            conexion=getConexion();
            ps = conexion.prepareStatement("SELECT * FROM prestamos");
            rs=ps.executeQuery();

            if(ps.executeUpdate()>0){
                 
                 Menu menu= new Menu();
                 menu.setVisible(true);
                 this.setVisible(false);
                 
            }else{
                System.out.println("Error al generar el prestamo");
            }
                
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }
        

    }
    
    public void barra(){

         JScrollPane barra = new JScrollPane(panel);
         barra.setBounds(101, 67, 418, 450);
         this.getContentPane().add(barra);

    }
        
     public void paneles(){
        panel.setBounds(101, 67, 418, 450);
        panel.setBackground(Color.white);
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panel.setPreferredSize(new Dimension(300,70));
        panel.setLayout(null);
        //this.getContentPane().add(panel); //no es necesario porque el panel está en el JScrollPane
 
        panel2.setBackground(Color.white);
        panel2.setBounds(530, 67, 408, 450);
        panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panel2.setLayout(null);
        this.getContentPane().add(panel2);
    }
     
    public void botones(){
         
        Connection conexion= null;
        
        try{
            
            //Eventos de los botones
            ActionListener accion1= new ActionListener(){

            @Override
                public void actionPerformed(ActionEvent e){
                
                    if(e.getActionCommand().length()==11){
                        
                        numero= Integer.parseInt(Character.toString(e.getActionCommand().charAt(10)));

                    }else 
                        if(e.getActionCommand().length()==12){
                        
                        numero = Integer.parseInt(Character.toString(e.getActionCommand().charAt(10)) + Character.toString(e.getActionCommand().charAt(11)));
                           
                    }
                    
                    auxiliarEventosBoton();
                    
                }
                
            
            };
            
            //Creacion de botones

            int y=85;
          
            conexion=getConexion();  
            ps=conexion.prepareStatement("select * from equipos");
            rs=ps.executeQuery();
            
            int i=0;
            String a;
            String b ="Disponible";
            
            while(rs.next()){
                a=rs.getString("estado");
                
                
            }
            
                //Desactivar los botones que sean de los equipos disponibles
  
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }
        
        JButton detalles1 = new JButton();
        detalles1.setText("Detalles");
        detalles1.setBounds(270,95,110,30);
        detalles1.setBackground(Color.white);
        detalles1.setFont(new Font("Yu Gothic UI", 0, 14));
        detalles1.setIcon(new ImageIcon(getClass().getResource("/Archivos/lupa.png")));
        detalles1.setCursor(new Cursor(HAND_CURSOR));
        //detalles1.addActionListener(accion1);
        panel.add(detalles1);
        
        
        JButton detalles2 = new JButton();
        detalles2.setText("Detalles");
        detalles2.setBounds(270,225,110,30);
        detalles2.setBackground(Color.white);
        detalles2.setFont(new Font("Yu Gothic UI", 0, 14));
        detalles2.setIcon(new ImageIcon(getClass().getResource("/Archivos/lupa.png")));
        detalles2.setCursor(new Cursor(HAND_CURSOR));
        //detalles1.addActionListener(accion);
        panel.add(detalles2);
        
        
        JButton detalles3 = new JButton();
        detalles3.setText("Detalles");
        detalles3.setBounds(270,355,110,30);
        detalles3.setBackground(Color.white);
        detalles3.setFont(new Font("Yu Gothic UI", 0, 14));
        detalles3.setIcon(new ImageIcon(getClass().getResource("/Archivos/lupa.png")));
        detalles3.setCursor(new Cursor(HAND_CURSOR));
        //detalles1.addActionListener(accion);
        panel.add(detalles3);
        
        

    }
  
    public void textos(){
        
        Connection conexion= null;
        
        //Total prestamo estudiantes
        
        JLabel texto = new JLabel("Total prestamo estudiantes");
        texto.setFont(new Font("Yu Gothic", 0, 14));
        texto.setBounds(22, 35, 220, 33);
        texto.setForeground(Color.BLACK);
        panel.add(texto);
        
        JLabel cantidad1 = new JLabel("N.Prestamos:");
        cantidad1.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        cantidad1.setBounds(22,75, 220, 33);
        cantidad1.setForeground(Color.BLACK);
        panel.add(cantidad1);
        
        JLabel porcentaje1 = new JLabel("Porcentaje:");
        porcentaje1.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        porcentaje1.setBounds(22,115, 220, 33);
        porcentaje1.setForeground(Color.BLACK);
        panel.add(porcentaje1);
        
        
        //Estado de equipos
        JLabel estados = new JLabel("Estados de equipos");
        estados.setFont(new Font("Yu Gothic", 0, 14));
        estados.setBounds(22, 165, 220, 33);
        estados.setForeground(Color.BLACK);
        panel.add(estados);
        
        JLabel disponibles = new JLabel("Disponibles:");
        disponibles.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        disponibles.setBounds(22, 205, 220, 33);
        disponibles.setForeground(Color.BLACK);
        panel.add(disponibles);
        
        JLabel ocupados = new JLabel("Ocupados:");
        ocupados.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        ocupados.setBounds(22, 245, 220, 33);
        ocupados.setForeground(Color.BLACK);
        panel.add(ocupados);
        
        //Situación de equipos 
        JLabel situacion = new JLabel("Situacion de equipos");
        situacion.setFont(new Font("Yu Gothic", 0, 14));
        situacion.setBounds(22, 295, 220, 33);
        situacion.setForeground(Color.BLACK);
        panel.add(situacion);
        
        JLabel sinProblem = new JLabel("Sin daños:");
        sinProblem.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        sinProblem.setBounds(22, 335, 220, 33);
        sinProblem.setForeground(Color.BLACK);
        panel.add(sinProblem);
        
        JLabel Problems = new JLabel("Con daños:");
        Problems.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        Problems.setBounds(22, 375, 220, 33);
        Problems.setForeground(Color.BLACK);
        panel.add(Problems);
        
        double porcentaje2=0;
        double cantidad_prestamos=0;
        int cantidad_pc=0;
        
        try{
              conexion=getConexion();  
              ps = conexion.prepareStatement("SELECT count(*) FROM prestamos");
              rs=ps.executeQuery();
              
              if(rs.next()){
                n_prestamos.setText(Integer.toString(rs.getInt("count(*)")));
                n_prestamos.setFont(new Font("Yu Gothic UI Semilight", 0, 14));             
                n_prestamos.setBounds(110, 75, 220, 33);                                    //JLabel porcentaje = new JLabel();
                n_prestamos.setForeground(Color.BLACK);                                     //JLabel n_disponibles = new JLabel();
                panel.add(n_prestamos);                                                     //JLabel n_ocupados = new JLabel();
                cantidad_prestamos=rs.getInt("count(*)");  
                
                ps = conexion.prepareStatement("SELECT count(*) FROM equipos");
                rs=ps.executeQuery();  
                                                                                                //JLabel n_problems = new JLabel();
                    if(rs.next()){
                        cantidad_pc=rs.getInt("count(*)");                                                                             //JLabel n_sinProblems = new JLabel();
                        porcentaje2=(cantidad_prestamos*100)/cantidad_pc;
                        System.out.println(porcentaje2);
                        porcentaje.setText(porcentaje2+"%");
                        porcentaje.setFont(new Font("Yu Gothic UI Semilight", 0, 14));             
                        porcentaje.setBounds(110, 115, 220, 33);                                    //JLabel  = new JLabel();
                        porcentaje.setForeground(Color.BLACK);                                     //JLabel n_disponibles = new JLabel();
                        panel.add(porcentaje);
                                                                                                        
                     }
              }
              
              ps = conexion.prepareStatement("SELECT * FROM equipos where estado=Disponible");
              rs=ps.executeQuery();
              while(rs.next()){
                  
                  
                  
                  
              }
              
            
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel1.setText("Laboratorio de infomatica ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(721, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivos/ajustes.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivos/los-usuarios.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivos/analitica.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivos/casa.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(858, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    
        
        asignar usuario =new asignar(1);
        
        usuario.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       
        estadisticas estadisticas= new estadisticas();
        estadisticas.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        configuracion configuracion =new configuracion();
        configuracion.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         Menu menu= new Menu();
         menu.setVisible(true);
         this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed
    
  
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new estadisticas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
