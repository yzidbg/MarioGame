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
public class Jugador {
    private String id;
    private String nick;
    private String maxPts;
    private String idTipoJug;
    private String password;

    public Jugador() {
    }
    
    public Jugador(String id, String nick, String maxPts, String idTipoJug, String password) {
        this.id = id;
        this.nick = nick;
        this.maxPts = maxPts;
        this.idTipoJug = idTipoJug;
        this.password = password;
    }
    
    public Jugador(String nick, String maxPts, String idTipoJug, String password) {
        this.nick = nick;
        this.maxPts = maxPts;
        this.idTipoJug = idTipoJug;
        this.password = password;
    }
    
    public Jugador(ResultSet rs){
        try{
            id=rs.getString("id");
            nick=rs.getString("nick");
            maxPts=rs.getString("max_pts");
            idTipoJug=rs.getString("tipo_usuario_id");
            password=rs.getString("password");
        }catch(Exception e){}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMaxPts() {
        return maxPts;
    }

    public void setMaxPts(String maxPts) {
        this.maxPts = maxPts;
    }

    public String getIdTipoJug() {
        return idTipoJug;
    }

    public void setIdTipoJug(String idTipoJug) {
        this.idTipoJug = idTipoJug;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nick=" + nick + ", maxPts=" + maxPts + ", idTipoJug=" + idTipoJug + ", password=" + password + '}';
    }
    
}
