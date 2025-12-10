package fr.parseur;

import fr.entites_csv.TirAuButCsv;
import fr.entites_data.TirAuButData;

import java.time.LocalDate;

/** Permet de constituer la classe TirAuButData
 * à partir des lignes du fichier csv shootouts.csv
 */
public class ParseurTirAuBut {

    // Constructeur privé pour empêcher une instanciation
    private ParseurTirAuBut() {    }

    /** Ajoute une séance de tir but, construit à partir d'une ligne du CSV,
     *  dans l'objet TirAuButCsv passé en paramètre.
     * @param tirAuButLigne conteneur qui stocke la liste des buts
     * @param ligne    ligne du fichier CSV à parser
     */
    public static void ajoutLigneTirAuBut(TirAuButCsv tirAuButLigne, String ligne){

        /*le split permet de diviser une ligne dans un tableau à chaque , */
        String [] bout = ligne.split(",",-1);
        String dateMatchStr = bout[0];
        LocalDate date = LocalDate.parse(dateMatchStr);
        String equipeHote  = bout[1];
        String equipeInvite = bout[2];
        String equipeVainqueurTir = bout[3];
        String equipeCommnceTir = bout[4];


        /*On crée un objet tirBut avec toutes les infos*/
        TirAuButData tirBut = new TirAuButData(date,equipeHote,equipeInvite,equipeVainqueurTir,equipeCommnceTir);

        /*On ajoute la séance de tir au but à la liste des séances de tir but*/
        tirAuButLigne.getTirAuButData().add(tirBut);
    }
}
