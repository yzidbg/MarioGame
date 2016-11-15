/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

/**
 *
 * @author Duke
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BDManager {
    
    private final String database ="mariogamedb";
    private final String driver="com.mysql.jdbc.Driver";
    private final String user= "root";
    private final String password= "9876"; 
    private final String url="jdbc:mysql://localhost:3306/"+database;

    public BDManager() {
    }

    
    
    protected  void addObject(ArrayList v , ResultSet rs){
        
    }
            
// Para Consultar a la Data Base que necesita un Retorno
    protected ArrayList executeQuery(String sz) {
        ArrayList c =new ArrayList();
        try {
            Class.forName(driver);
            System.out.println(" \n Estableciendo Conexion.....");
            Connection conexion=DriverManager.getConnection(url,user,password);
            if(conexion == null)
            {
                System.out.println("Problemas al tratar de Conectase a la Base de Datos");
                return c;
            }
            Statement stm =conexion.createStatement();
            ResultSet rs=stm.executeQuery(sz);
            while(rs.next()){
                addObject(c, rs);
            }
            conexion.close();
        }catch (ClassNotFoundException e) {

            System.out.println("--------------------ERROR--------------------");
            System.out.println("SQL:" + sz);
            System.out.println("Exception: " + e);
            } catch (SQLException e) {
            System.out.println("--------------------ERROR--------------------");
            System.out.println("SQL:" + sz);
            System.out.println("Exception: " + e);
        }
        
        
        return c;
    }
    // Para Operaciones (Insertar Eliminar Consultar)en la DB que no necesitan Retorno 
     public void execute(String sz) {

        try {
            Class.forName(driver);
            System.out.println("\nEstableciendo conexión..");
            Connection conexion = DriverManager.getConnection(url,user, password);
            if (conexion == null) {
                System.out.println("database conection not working");
                return;
            }
            Statement stm = conexion.createStatement();
            stm.execute(sz);
            conexion.close();

        } catch (ClassNotFoundException e) {

            System.out.println("--------------------ERROR--------------------");
            System.out.println("SQL:" + sz);
            System.out.println("Exception: " + e);
        } catch (SQLException e) {
            System.out.println("--------------------ERROR--------------------");
            System.out.println("SQL:" + sz);
            System.out.println("Exception: " + e);
        }

    }
}
