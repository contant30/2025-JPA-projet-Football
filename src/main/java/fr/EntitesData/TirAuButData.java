package fr.EntitesData;

import java.time.LocalDate;

/*
 * class correspondants aux éléments du fichier csv shootouts
 */
public class TirAuButData {

    private LocalDate date;
    private String equipeHote;
    private String equipeInvite;
    private String equipeGagnanteTir;
    private String equipeCommenceTir;


    public TirAuButData(){
        super();
    }

    public TirAuButData(LocalDate date, String equipeHote, String equipeInvite, String equipeGagnanteTir, String equipeCommenceTir) {
        this.date = date;
        this.equipeHote = equipeHote;
        this.equipeInvite = equipeInvite;
        this.equipeGagnanteTir = equipeGagnanteTir;
        this.equipeCommenceTir = equipeCommenceTir;
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

    public String getEquipeGagnanteTir() {
        return equipeGagnanteTir;
    }

    public void setEquipeGagnanteTir(String equipeGagnanteTir) {
        this.equipeGagnanteTir = equipeGagnanteTir;
    }

    public String getEquipeCommenceTir() {
        return equipeCommenceTir;
    }

    public void setEquipeCommenceTir(String equipeCommenceTir) {
        this.equipeCommenceTir = equipeCommenceTir;
    }

    @Override
    public String toString() {
        return "TirAuButData{" +
                "date=" + date +
                ", equipeHote='" + equipeHote + '\'' +
                ", equipeInvite='" + equipeInvite + '\'' +
                ", equipeGagnanteTir='" + equipeGagnanteTir + '\'' +
                ", equipeCommenceTir='" + equipeCommenceTir + '\'' +
                '}';
    }
}
