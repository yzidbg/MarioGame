/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.ConexionDB;
import Modelo.Conexion;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class ConexionController {
    ArrayList conexiones = new ArrayList();
    Conexion conexion = null;
    
    public ConexionController() {
    }

    public ArrayList getConexiones() {
        return conexiones;
    }

    public void setConexiones(ArrayList conexiones) {
        this.conexiones = conexiones;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public ArrayList consultarTop10(){
        return ConexionDB.mgr.getTop10(true,"");
    }   
    
    public void addConexion(Conexion c){
        ConexionDB.mgr.save(c, "add");
    }
    
    public ArrayList consultarNumConex(String id){
        return ConexionDB.mgr.getTop10(false,id);
    }
}
