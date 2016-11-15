/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Modelo.Conexion10;
import Servicio.BDManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class ConexionDB10 extends BDManager{
    public static ConexionDB10 mgr = new ConexionDB10();

    public ConexionDB10() {
    }
    
    public void addObject(ArrayList v, ResultSet rs) {
        v.add(new Conexion10(rs));
    }
    
    public ArrayList<Conexion10> getTop10(){
        return executeQuery("SELECT jugadores.nick, conexiones.ip, conexiones.fecha_hora, "
                + "conexiones.puntaje FROM jugadores JOIN conexiones "
                + "ON jugadores.id = conexiones.jugadores_id order by "
                + "conexiones.puntaje desc Limit 10");
    }
    
}
