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
public class Conexion10 {
    private String id;
    private String ipCon;
    private String fechaHora;
    private String puntos;
    private String nick;

    public Conexion10() {
    }

    public Conexion10(String ipCon, String fechaHora, String puntos, String nick) {
        this.ipCon = ipCon;
        this.fechaHora = fechaHora;
        this.puntos = puntos;
        this.nick = nick;
    }

    public Conexion10(ResultSet rs) {
        try{
            nick=rs.getString("nick");
            ipCon=rs.getString("ip");
            fechaHora=rs.getString("fecha_hora");
            puntos=rs.getString("puntaje");
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
    
    @Override
    public String toString() {
        return "Conexion{" + "id=" + id + ", ipCon=" + ipCon + ", fechaHora=" + fechaHora + ", puntos=" + puntos + ", nick=" + nick + '}';
    }
}
