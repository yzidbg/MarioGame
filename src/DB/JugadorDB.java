/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Modelo.Jugador;
import Servicio.BDManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class JugadorDB extends BDManager{
    
    public static JugadorDB mgr = new JugadorDB();

    public JugadorDB() {
    }

    public void addObject(ArrayList v, ResultSet rs) {
        v.add(new Jugador(rs));
    }
    
    public Jugador getJugador(String campo, String dato){
        ArrayList c = executeQuery("select * from jugadores where "+campo+" = '"+dato+"'");
        if(c.size()>0){
            return (Jugador) c.get(0);
        }
        Jugador j = null;
        return j;
    }
    
    public void save(Jugador j, String s){
        switch (s){
            case "add":
                mgr.execute("insert into jugadores(nick, max_pts, tipo_usuario_id, password) values('"
                        + j.getNick() + "','"
                        + j.getMaxPts() + "','"
                        + j.getIdTipoJug() + "','"
                        + j.getPassword() + "')");
                break;
            case "updateMaxPts":
                mgr.execute("update jugadores set max_pts ='" + j.getMaxPts()
                        + "'where id = " + j.getId() +"");
                break;
            case "updateJugador":
                mgr.execute("update jugadores set nick ='" + j.getNick()
                        + "', tipo_usuario_id='" + j.getIdTipoJug()
                        + "', password='" + j.getPassword()
                        + "'where id = " + j.getId() +"");
                break;
            case "deleteJugador":
                mgr.execute("delete from jugadores WHERE id =" + j.getId() +"");
                break;
        }
    }
    
    public ArrayList<Jugador> getJugadores(){
        return executeQuery("select * from jugadores");
    }
    //id, nick, max_pts, tipo_usuario_id, password
}
