/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Modelo.Conexion;
import Servicio.BDManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class ConexionDB extends BDManager{
    public static ConexionDB mgr = new ConexionDB();

    public ConexionDB() {
    }
    
    public void addObject(ArrayList v, ResultSet rs) {
        v.add(new Conexion(rs));
    }
    
    public ArrayList<Conexion> getTop10(){
        return executeQuery("SELECT jugadores.nick, conexiones.ip, conexiones.fecha_hora, "
                + "conexiones.puntaje FROM jugadores JOIN conexiones "
                + "ON jugadores.id = conexiones.jugadores_id order by "
                + "conexiones.puntaje desc Limit 10");
    }
    
    public void save(Conexion c, String s){
        switch (s){
            case "add":
                mgr.execute("insert into conexiones(ip, fecha_hora, puntaje, jugadores_id) values('"
                        + c.getIpCon() + "','"
                        + c.getFechaHora() + "','"
                        + c.getPuntos() + "','"
                        + c.getIdJugador() + "')");
                break;
            case "updateMaxPts":
                mgr.execute("update jugadores set max_pts ='" + c.getPuntos()
                        + "'where id = " + c.getId() +"");
                break;
        }
    }
    
}
