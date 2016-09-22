/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScrollGame;

/**
 *
 * @author yz
 */
public class PlayerScore {
    private int codPlayer;
    private String nombrePlayer;
    private int scorePlayer;

    public PlayerScore() {
    }

    public PlayerScore(int codPlayer, String nombrePlayer, int scorePlayer) {
        this.codPlayer = codPlayer;
        this.nombrePlayer = nombrePlayer;
        this.scorePlayer = scorePlayer;
    }

    public int getCodPlayer() {
        return codPlayer;
    }

    public void setCodPlayer(int codPlayer) {
        this.codPlayer = codPlayer;
    }

    public String getNombrePlayer() {
        return nombrePlayer;
    }

    public void setNombrePlayer(String nombrePlayer) {
        this.nombrePlayer = nombrePlayer;
    }

    public int getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(int scorePlayer) {
        this.scorePlayer = scorePlayer;
    }
    
    public void addScore(int sc){
        this.scorePlayer+=sc;
    }
    
}
