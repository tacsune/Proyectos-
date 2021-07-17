

package Clases;

import Interfaces.Menu;
import Interfaces.configuracion;
import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    
    
    
    public static void main(String[] args){
        
        getConexion();
        
        Menu menu= new Menu();
        menu.setVisible(true);
        
        
    }
    
    public static Connection getConexion() {
    Connection conexion =null;
    
    try{
        
        conexion=DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/gestion_laboratorio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        if(conexion!=null){
            System.out.println("Conexion realizada exitosamente");
        }
    }catch(Exception e){
        System.out.println("Ocurrio un error"+e.getMessage());
    }
        return conexion;
    
    
}
    

}
