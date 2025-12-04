package fr.EntitesData;

import java.time.LocalDate;

/*
 * class correspondants aux élément du fichier csv résults
 */
public class ResultatData {

    private LocalDate date;
    private String equipeHote;
    private String equipeInvite;
    private String scoreHote;
    private String scoreInvite;
    private String nomTournoi;
    private String ville;
    private String Pays;
    private Boolean terrainNeutre;

    public ResultatData(){
        super();
    }

    public ResultatData(LocalDate date, String equipeHote, String equipeInvite, String scoreHote, String scoreInvite, String nomTournoi, String ville, String pays, Boolean terrainNeutre) {
        this.date = date;
        this.equipeHote = equipeHote;
        this.equipeInvite = equipeInvite;
        this.scoreHote = scoreHote;
        this.scoreInvite = scoreInvite;
        this.nomTournoi = nomTournoi;
        this.ville = ville;
        Pays = pays;
        this.terrainNeutre = terrainNeutre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEquipeHote() {
        return equipeHote;
    }

    public void setEquipeHote(String equipeHote) {
        this.equipeHote = equipeHote;
    }

    public String getEquipeInvite() {
        return equipeInvite;
    }

    public void setEquipeInvite(String equipeInvite) {
        this.equipeInvite = equipeInvite;
    }

    public String getScoreHote() {
        return scoreHote;
    }

    public void setScoreHote(String scoreHote) {
        this.scoreHote = scoreHote;
    }

    public String getScoreInvite() {
        return scoreInvite;
    }

    public void setScoreInvite(String scoreInvite) {
        this.scoreInvite = scoreInvite;
    }

    public String getNomTournoi() {
        return nomTournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    public Boolean getTerrainNeutre() {
        return terrainNeutre;
    }

    public void setTerrainNeutre(Boolean terrainNeutre) {
        this.terrainNeutre = terrainNeutre;
    }

    @Override
    public String toString() {
        return "ResultatData{" +
                "date=" + date +
                ", equipeHote='" + equipeHote + '\'' +
                ", equipeInvite='" + equipeInvite + '\'' +
                ", scoreHote='" + scoreHote + '\'' +
                ", scoreInvite='" + scoreInvite + '\'' +
                ", nomTournoi='" + nomTournoi + '\'' +
                ", ville='" + ville + '\'' +
                ", Pays='" + Pays + '\'' +
                ", terrainNeutre=" + terrainNeutre +
                '}';
    }
}
