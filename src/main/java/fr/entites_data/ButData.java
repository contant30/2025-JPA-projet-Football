package fr.entites_data;

import java.time.LocalDate;

/*
 * class qui représente une ligne du fichier CSV goalscorers.csv
 */
public class ButData {

    private LocalDate date;
    private String equipeHote;
    private String equipeInvite;
    private String equipeButeur;
    private String buteur;
    private String minuteScore;
    private Boolean csc;
    private Boolean penalty;

    public ButData(){
        super();
    }

    public ButData(Boolean penalty, Boolean csc, String minuteScore, String buteur, String equipeButeur, String equipeInvite, String equipeHote, LocalDate date) {
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

    public String getButeur() {
        return buteur;
    }

    public String getMinuteScore() {
        return minuteScore;
    }

    public Boolean getCsc() {
        return csc;
    }

    public Boolean getPenalty() {
        return penalty;
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
