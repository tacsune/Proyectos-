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
public class Menu extends javax.swing.JFrame {
    
    JPanel panel=new JPanel();
    JPanel panel2= new JPanel();
    
    JLabel imagen = new JLabel();
    JLabel equipo = new JLabel();
    JLabel Estudiante = new JLabel();
    JLabel codigo = new JLabel();
    JLabel Fecha = new JLabel();
    
    JLabel mostrarEquipo = new JLabel();
    JLabel mostrarEstudiante = new JLabel();
    JLabel mostrarCodigo = new JLabel();
    JLabel mostrarFecha = new JLabel();
    
    JButton eliminar = new JButton ();
     
    PreparedStatement ps;
    ResultSet rs;
    
    int pc;
    int crecimiento=200;
    int numero=0; // numero del equipo cliqueado
    
    public Menu() {
        cantidadPC();
        paneles();
        botones();
        textos();
        barra();
        initComponents();
        this.setLocationRelativeTo(null); 
    }

    public void cantidadPC(){
        
        Connection conexion= null;
        
        try{
            conexion=getConexion();
            ps = conexion.prepareStatement("SELECT count(*) FROM equipos");
            rs=ps.executeQuery();
            
            if(rs.next()){
                
                pc=rs.getInt("COUNT(*)");
                
            }
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }
    
    }
    
    
    public void auxiliarEventosBoton(){
        
        Connection conexion= null;
        int codigo=0;

        try{
            conexion=getConexion();
            ps = conexion.prepareStatement("SELECT * FROM prestamos where id="+numero);
            rs=ps.executeQuery();
            
            while(rs.next()){
                 
                mostrarEquipo.setText(Integer.toString(rs.getInt("ID_equipo")));
                mostrarCodigo.setText(Integer.toString(rs.getInt("codigo_estudiante")));
                mostrarFecha.setText(rs.getString("fecha_inicio"));
                                
                mostrarEquipo.setVisible(true);
                mostrarCodigo.setVisible(true);
                mostrarFecha.setVisible(true);
                        
                codigo=rs.getInt("codigo_estudiante");
      
                ps = conexion.prepareStatement("SELECT * FROM estudiantes where codigo="+codigo);
                rs=ps.executeQuery();
                        
                while(rs.next()){

                    mostrarEstudiante.setText(rs.getString("nombre_comple"));
                    mostrarEstudiante.setVisible(true);
    
                }
                
            }
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }
        

    }
    
    public void auxiliarEventoEliminar(){
        
        Connection conexion= null;
        
        try{
            conexion=getConexion();
            ps = conexion.prepareStatement("delete from prestamos where id="+numero);
            
            if(ps.executeUpdate()>0){
                 
                Menu menu= new Menu();
                menu.setVisible(true);
                this.setVisible(false);
                
            }else{
                System.out.println("Error al eliminar el estudiante");   
            }
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }

    }
    

    public void barra(){

         JScrollPane barra = new JScrollPane(panel);
         barra.setBounds(101, 67, 418, 450);
         this.getContentPane().add(barra);
         
         JScrollPane barra2 = new JScrollPane(panel2);
         barra2.setBounds(530, 67, 408, 450);
         this.getContentPane().add(barra2);
         
         
    }
        
     public void paneles(){
        panel.setBounds(101, 67, 418, 450);
        panel.setBackground(Color.white);
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panel.setPreferredSize(new Dimension(300,70*pc));
        panel.setLayout(null);
        //this.getContentPane().add(panel); //no es necesario porque el panel está en el JScrollPane
        
        
        
        panel2.setBackground(Color.white);
        panel2.setBounds(530, 67, 418, 450);
        //panel2.setPreferredSize(new Dimension(300,550)); Este codigo está en la funcion actionPerformed (Eventos del boton)
        panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panel2.setLayout(null);
        //this.getContentPane().add(panel2);
    }
     
    public void botones(){
         
        Connection conexion= null;
        
        try{
            
            //Eventos de los botones
            ActionListener accion= new ActionListener(){

            @Override
                public void actionPerformed(ActionEvent e){
                

                    imagen.setVisible(true);
                    equipo.setVisible(true);
                    Estudiante.setVisible(true);
                    codigo.setVisible(true);
                    Fecha.setVisible(true);  
                    
                     
                    
                    if(e.getActionCommand().length()==11){
                        
                        numero= Integer.parseInt(Character.toString(e.getActionCommand().charAt(10)));
                        
                        
                        
                    }else 
                        if(e.getActionCommand().length()==12){
                        
                        numero = Integer.parseInt(Character.toString(e.getActionCommand().charAt(10)) + Character.toString(e.getActionCommand().charAt(11)));
                           
                    }
                    
                    panel2.setPreferredSize(new Dimension(300,550));
                    eliminar.setVisible(true);
                    auxiliarEventosBoton();
                    
                }
                
            
            };
            
            //Evento del boton eliminar 
            ActionListener accion_eliminar= new ActionListener(){

            @Override
                public void actionPerformed(ActionEvent e){
               
                    auxiliarEventoEliminar();

                }

            };
            
            //Creacion de botones

            int y=85;
            String numPc;
            JButton boton[]= new JButton[pc];
            
            for(int i=0; i<pc; i++){
                
                numPc=Integer.toString(i+1);
                boton[i]= new JButton();
                boton[i].setText("    Equipo"+numPc);
                boton[i].setBounds(22,y,110,50);
                boton[i].setBorder(null);
                boton[i].setBackground(Color.white);
                boton[i].setFont(new Font("Yu Gothic UI", 0, 14));
                boton[i].setIcon(new ImageIcon(getClass().getResource("/Archivos/pantalla-de-pc.png")));
                boton[i].setCursor(new Cursor(HAND_CURSOR));
                boton[i].addActionListener(accion);
                panel.add(boton[i]);
                
                y=y+60;
            }
            
                //Desactivar los botones que sean de los equipos disponibles
                conexion=getConexion();
                ps=conexion.prepareStatement("select estado from equipos");
                rs=ps.executeQuery();
                
                int contador=0;
                String a;
                String b="Disponible";
                
                while(rs.next()){
                    a=rs.getString("estado");
                    
                    if(a.equals(b)){
                      boton[contador].setEnabled(false);  
                    }
                    
                    contador++;   
                }
                
                //Boton eliminar estudiante (ubicado en el panel 2)
                eliminar.setText("    Eliminar Estudiante");
                eliminar.setFont(new Font("Yu Gothic UI", 0, 14));
                eliminar.setForeground(Color.RED);
                eliminar.setBackground(Color.white);
                eliminar.setIcon(new ImageIcon(getClass().getResource("/Archivos/eliminar.png")));
                eliminar.setCursor(new Cursor(HAND_CURSOR));
                eliminar.setBounds(60, 464, 250, 40);
                eliminar.setBorder(null);
                eliminar.setVisible(false);
                eliminar.addActionListener(accion_eliminar);
                panel2.add(eliminar);
                
           
                
            
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }

    }
  
    public void textos(){
        
        Connection conexion= null;
        
        JLabel texto = new JLabel("Listado de equipos");
        texto.setFont(new Font("Yu Gothic", 0, 14));
        texto.setBounds(22, 35, 133, 33);
        texto.setForeground(Color.BLACK);
        panel.add(texto);
        
        
        JLabel estado[]= new JLabel[pc];
        String a;
        String b ="Disponible";
        int y=94;
        
        
        try{
              conexion=getConexion();  
              ps=conexion.prepareStatement("select estado from equipos");
              rs=ps.executeQuery();
              int i = 0;
              
            while(rs.next()){
                a=rs.getString("estado");
                estado[i]= new JLabel();
                estado[i].setText(a);
                estado[i].setBounds(193, y, 133, 33);
                estado[i].setFont(new Font("Yu Gothic", 0, 14));
                if(a.equals(b)){
                    estado[i].setForeground(Color.GREEN);  
                }else{
                    estado[i].setForeground(Color.red);
                }
             panel.add(estado[i]);
             
             i++;
             y=y+60;
         }
            
            
        }catch(Exception e){
            System.err.println("Ocurrio el siguiente error: "+e);
        }
                
        // Etiquetas panel 2
        
        //Imagen
        imagen.setBounds(142,35, 128, 128);
        imagen.setIcon(new ImageIcon("src/Archivos/pc.png"));
        imagen.setVisible(false);
        panel2.add(imagen);
        
        //Etiqueda de Equipo
        equipo.setText("Equipo:");
        equipo.setFont(new Font("Yu Gothic", 0, 14));
        equipo.setBounds(50, 214, 90, 40);
        equipo.setForeground(Color.BLACK);
        equipo.setVisible(false);
        panel2.add(equipo);
        
        //Etiqueda de Estudiante
        Estudiante.setText("Estudiante:");
        Estudiante.setFont(new Font("Yu Gothic", 0, 14));
        Estudiante.setBounds(50, 274, 90, 40);
        Estudiante.setForeground(Color.BLACK);
        Estudiante.setVisible(false);
        panel2.add(Estudiante);
        
        //Etiqueda de codigo
        codigo.setText("Codigo:");
        codigo.setFont(new Font("Yu Gothic", 0, 14));
        codigo.setBounds(50, 334, 90, 40);
        codigo.setForeground(Color.BLACK);
        codigo.setVisible(false);
        panel2.add(codigo);
        
        //Etiqueda de hora de inicio
        Fecha.setText("Fecha De Inicio:");
        Fecha.setFont(new Font("Yu Gothic", 0, 14));
        Fecha.setBounds(50, 394, 133, 40);
        Fecha.setForeground(Color.BLACK);
        Fecha.setVisible(false);
        panel2.add(Fecha);
        
        //Etiquetas con la informacion del equipo cliqueado
        
        
        mostrarEquipo.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        mostrarEquipo.setBounds(120, 214, 90, 40);
        mostrarEquipo.setForeground(Color.BLACK);
        mostrarEquipo.setVisible(false);
        panel2.add(mostrarEquipo);
        
        
        mostrarEstudiante.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        mostrarEstudiante.setBounds(140, 274, 200, 40);
        mostrarEstudiante.setForeground(Color.BLACK);
        mostrarEstudiante.setVisible(false);
        panel2.add(mostrarEstudiante);
        
        
        mostrarCodigo.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        mostrarCodigo.setBounds(120, 334, 130, 40);
        mostrarCodigo.setForeground(Color.BLACK);
        mostrarCodigo.setVisible(false);
        panel2.add(mostrarCodigo);
        
        
        mostrarFecha.setFont(new Font("Yu Gothic UI Semilight", 0, 14));
        mostrarFecha.setBounds(170, 394, 200, 40);
        mostrarFecha.setForeground(Color.BLACK);
        mostrarFecha.setVisible(false);
        panel2.add(mostrarFecha);
        
        
        
        
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
        setPreferredSize(new java.awt.Dimension(952, 557));
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
                .addContainerGap(590, Short.MAX_VALUE))
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
                .addContainerGap(227, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(727, Short.MAX_VALUE))
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         Menu menu= new Menu();
         menu.setVisible(true);
         this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        estadisticas estadisticas= new estadisticas();
        estadisticas.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        asignar usuario =new asignar(1);
        usuario.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        configuracion configuracion =new configuracion();
        configuracion.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Menu().setVisible(true);
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
