/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.ConexionDB10;
import DB.ConexionDB5;
import Modelo.Conexion10;
import Modelo.Conexion5;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class ConexionController {
    ArrayList conexiones = new ArrayList();
    Conexion10 conexion = null;
    Conexion5 conexion5 = null;
    
    public ConexionController() {
    }

    public ArrayList getConexiones() {
        return conexiones;
    }

    public void setConexiones(ArrayList conexiones) {
        this.conexiones = conexiones;
    }

    public Conexion10 getConexion() {
        return conexion;
    }

    public void setConexion(Conexion10 conexion) {
        this.conexion = conexion;
    }

    public Conexion5 getConexion5() {
        return conexion5;
    }

    public void setConexion5(Conexion5 conexion5) {
        this.conexion5 = conexion5;
    }
    
    public ArrayList consultarTop10(){
        return ConexionDB10.mgr.getTop10();
    }
    
    public ArrayList consultarTop5(){
        return ConexionDB5.mgr.getTop5();
    }
    
    
}
