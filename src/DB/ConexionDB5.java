/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Modelo.Conexion10;
import Modelo.Conexion5;
import Servicio.BDManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class ConexionDB5 extends BDManager{
    public static ConexionDB5 mgr = new ConexionDB5();

    public ConexionDB5() {
    }
    
    public void addObject(ArrayList v, ResultSet rs) {
        v.add(new Conexion5(rs));
    }
    
    public ArrayList<Conexion10> getTop5(){
        return executeQuery("SELECT jugadores.nick, conexiones.ip, conexiones.fecha_hora, " +
                "count(conexiones.jugadores_id) conn from conexiones join jugadores " +
                "ON jugadores.id = conexiones.jugadores_id group by " +
                "conexiones.jugadores_id order by conn desc Limit 5");
    }
    
}
