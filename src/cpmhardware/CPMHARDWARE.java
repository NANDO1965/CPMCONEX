package cpmhardware;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CPMHARDWARE {
    public static void main(String[] args) {
        /** referenciar el usuario y password con el cual me conectaré a la base de datos */
        String usuario = "root";
        String password = "";
        
        String url = "jdbc:mysql://localhost:3306/cpmhardware";
                
        /** establecer conexión */

        Connection conexion; 
        Statement statement; /** permite ejecutar sentencias sql a través de la conexión establecida */
        ResultSet rs; /** sirve como objeto que tiene la capacidad de recibir la respuesta desde la base de datos, como el reflejo de una tabla */
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CPMHARDWARE.class.getName()).log(Level.SEVERE, null, ex);
          }   
         
         /** establecer la conexion*/
                
            try {
                conexion = DriverManager.getConnection(url,usuario,password);
                statement = conexion.createStatement();
            
             /**INSERTAR información a la base de datos*/
             
            statement.executeUpdate("INSERT INTO fabricantes(Id_fabricantes,Nombre,Pais_origen,Pagina_web  )VALUES(5,'Acer','EstadosUnidos','www.Acer.com')");
            rs = statement.executeQuery("SELECT * FROM fabricantes");
            rs.next();
                
            do{
            
            System.out.println (rs.getString("Nombre")+" "+rs.getString("Pais_origen" ));
                
            }while(rs.next());
            
            } catch (SQLException ex) {
                Logger.getLogger(CPMHARDWARE.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /** CONSULTAR */  
            
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpmhardware","root","");
            PreparedStatement sentencia = cn.prepareStatement("SELECT * FROM fabricantes WHERE Id_fabricantes = ?");
            sentencia.setString(1, "3");
            ResultSet prs =sentencia.executeQuery();
            prs.next();
            
            do {
                        System.out.println(""+prs.getString("Nombre")+" "+prs.getString("Pais_origen" ));
            } while(prs.next()); 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CPMHARDWARE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         /** ELIMINAR */
         
         
        try {
            Connection cne = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpmhardware","root","");
            PreparedStatement sentenciaEliminar = cne.prepareStatement("DELETE FROM fabricantes WHERE Id_fabricantes = ?");
            sentenciaEliminar.setString(1, "4");
            int elm = sentenciaEliminar.executeUpdate();
            
            System.out.println("Elemento eliminado: " + elm);
                                   
        } catch (SQLException ex) {
            Logger.getLogger(CPMHARDWARE.class.getName()).log(Level.SEVERE, null, ex);
        }
                             
     }
 }

    
    
    