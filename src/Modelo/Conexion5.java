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
public class Conexion5 {
    private String id;
    private String ipCon;
    private String fechaHora;
    private String conexiones;
    private String nick;

    public Conexion5() {
    }

    public Conexion5(String id, String ipCon, String fechaHora, String conexiones, String nick) {
        this.id = id;
        this.ipCon = ipCon;
        this.fechaHora = fechaHora;
        this.conexiones = conexiones;
        this.nick = nick;
    }
    
    public Conexion5(String ipCon, String fechaHora, String conexiones, String nick) {
        this.ipCon = ipCon;
        this.fechaHora = fechaHora;
        this.conexiones = conexiones;
        this.nick = nick;
    }

    public Conexion5(ResultSet rs) {
        try{
            nick=rs.getString("nick");
            ipCon=rs.getString("ip");
            fechaHora=rs.getString("fecha_hora");
            conexiones=rs.getString("conn");
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

    public String getConexiones() {
        return conexiones;
    }

    public void setConexiones(String conexiones) {
        this.conexiones = conexiones;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "Conexion5{" + "id=" + id + ", ipCon=" + ipCon + ", fechaHora=" + fechaHora + ", conexiones=" + conexiones + ", nick=" + nick + '}';
    }
    
    
}
