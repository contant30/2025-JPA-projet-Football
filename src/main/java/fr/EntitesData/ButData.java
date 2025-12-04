package fr.EntitesData;

import java.time.LocalDate;

/*
 * class correspondants aux éléments du fichier csv goalscorers
 */
public class ButData {

    private LocalDate date;
    private String equipeHote;
    private String equipeInvite;
    private String equipeButeur;
    private String buteur;
    private Integer minuteScore;
    private Boolean csc;
    private Boolean penalty;

    public ButData(){
        super();
    }

    public ButData(Boolean penalty, Boolean csc, Integer minuteScore, String buteur, String equipeButeur, String equipeInvite, String equipeHote, LocalDate date) {
        this.penalty = penalty;
        this.csc = csc;
        this.minuteScore = minuteScore;
        this.buteur = buteur;
        this.equipeButeur = equipeButeur;
        this.equipeInvite = equipeInvite;
        this.equipeHote = equipeHote;
        this.date = date;
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

    public String getEquipeButeur() {
        return equipeButeur;
    }

    public void setEquipeButeur(String equipeButeur) {
        this.equipeButeur = equipeButeur;
    }

    public String getButeur() {
        return buteur;
    }

    public void setButeur(String buteur) {
        this.buteur = buteur;
    }

    public Integer getMinuteScore() {
        return minuteScore;
    }

    public void setMinuteScore(Integer minuteScore) {
        this.minuteScore = minuteScore;
    }

    public Boolean getCsc() {
        return csc;
    }

    public void setCsc(Boolean csc) {
        this.csc = csc;
    }

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "ButData{" +
                "date=" + date +
                ", equipeHote='" + equipeHote + '\'' +
                ", equipeInvite='" + equipeInvite + '\'' +
                ", equipeButeur='" + equipeButeur + '\'' +
                ", buteur='" + buteur + '\'' +
                ", minuteScore=" + minuteScore +
                ", csc=" + csc +
                ", penalty=" + penalty +
                '}';
    }
}
