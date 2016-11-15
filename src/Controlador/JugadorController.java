/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB.JugadorDB;
import Modelo.Jugador;
import java.util.ArrayList;

/**
 *
 * @author yz
 */
public class JugadorController {
    ArrayList jugadores = new ArrayList();
    Jugador jugador=null;

    public JugadorController() {
    }

    public ArrayList getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public void agregarJugador(String nick, String maxPts, String idTipoUsuario, String password){
        Jugador jugador = new Jugador(nick, maxPts, idTipoUsuario, password);
        JugadorDB.mgr.save(jugador, "add");
    }
    
    public ArrayList consultarJugadores(){
        return JugadorDB.mgr.getJugadores();
    }
    
    public Jugador consultarUnJugador(String campo, String dato){
        return JugadorDB.mgr.getJugador(campo, dato);
    }
    
    public void modificarMaxPts(String id, String nick, String maxPts){
        jugador.setId(id);
        jugador.setNick(nick);
        jugador.setMaxPts(maxPts);
        JugadorDB.mgr.save(jugador, "updateMaxPts");
    }
    
    public void modificarJugador(Jugador j){
        JugadorDB.mgr.save(j, "updateJugador");
    }
    
    public void eliminarJugador(Jugador j){
        JugadorDB.mgr.save(j, "deleteJugador");
    }
}
