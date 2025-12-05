package fr.parseur;

import fr.entitesCsv.ButCsv;
import fr.entitesData.ButData;

import java.time.LocalDate;

/** Permet de constituer la classe butData
 * partir des lignes du fichier csv goalscorers.csv
 */
public class ParseurBut {


    /** Ajoute un but, construit à partir d'une ligne du CSV,
     *  dans l'objet ButCsv passé en paramètre.
     * @param butLigne conteneur qui stocke la liste des buts
     * @param ligne    ligne du fichier CSV à parser
     */
    public static void ajoutLigneBut(ButCsv butLigne, String ligne){

        /*le split permet de diviser une ligne dans un tableau à chaque , */
        String [] bout = ligne.split(",");
        String dateMatchStr = bout[0];
        LocalDate date = LocalDate.parse(dateMatchStr);
        String equipeHote  = bout[1];
        String equipeInvite = bout[2];
        String equipeButeur = bout[3];
        String buteur = bout[4];
        String minuteBut = bout[5];
        Boolean csc = Boolean.parseBoolean(bout[6]);
        Boolean penalty = Boolean.parseBoolean(bout[7]);

        /*On crée un objet but avec toutes les infos*/
        ButData but = new ButData(penalty,csc,minuteBut,buteur,equipeButeur,equipeInvite,equipeHote,date);

        /*On ajoute le but à la liste des but*/
        butLigne.getButData().add(but);
    }
}
