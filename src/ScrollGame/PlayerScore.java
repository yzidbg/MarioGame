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
    private int totalScore;
    private static int scorePlayer;

    public PlayerScore() {
    }

    public PlayerScore(int codPlayer, String nombrePlayer, int totalScore) {
        this.codPlayer = codPlayer;
        this.nombrePlayer = nombrePlayer;
        this.totalScore = totalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
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
    
    public static void addScore(int sc){
        PlayerScore.scorePlayer+=sc;
    }
    
    public void calcTotalCoins(){
        this.totalScore=scorePlayer;
    }
    
}
