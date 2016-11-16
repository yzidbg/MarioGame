/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;

/**
 *
 * @author yz
 */
public class Conexion {
    private String id;
    private String ipCon;
    private String fechaHora;
    private String puntos;
    private String nick;
    private String idJugador;

    public Conexion() {
    }

    public Conexion(String id, String ipCon, String fechaHora, String puntos, String nick, String idJugador) {
        this.id = id;
        this.ipCon = ipCon;
        this.fechaHora = fechaHora;
        this.puntos = puntos;
        this.nick = nick;
        this.idJugador = idJugador;
    }

       public Conexion(ResultSet rs) {
        try{
            nick=rs.getString("nick");
            ipCon=rs.getString("ip");
            fechaHora=rs.getString("fecha_hora");
            puntos=rs.getString("puntaje");
            idJugador=rs.getString("jugadores_id");
        }catch(Exception e){}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpCon() {
        return ipCon;
    }

    public void setIpCon(String ipCon) {
        this.ipCon = ipCon;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    @Override
    public String toString() {
        return "Conexion{" + "id=" + id + ", ipCon=" + ipCon + ", fechaHora=" + fechaHora + ", puntos=" + puntos + ", nick=" + nick + ", idJugador=" + idJugador + '}';
    }
    
}
