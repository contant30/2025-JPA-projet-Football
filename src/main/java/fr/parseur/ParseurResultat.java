package fr.parseur;

import fr.entites_csv.ResultatCSV;
import fr.entites_data.ResultatData;

import java.time.LocalDate;

public class ParseurResultat {

    /** Ajoute un resultat, construit à partir d'une ligne du CSV,
     *  dans l'objet ResultatCSV passé en paramètre.
     * @param resultatLigne conteneur qui stocke la liste des buts
     * @param ligne    ligne du fichier CSV à parser
     */
    public static void ajoutLigneResultat(ResultatCSV resultatLigne, String ligne){

        /*le split permet de diviser une ligne dans un tableau à chaque , */
        String [] bout = ligne.split(",");
        String dateMatchStr = bout[0];
        LocalDate date = LocalDate.parse(dateMatchStr);
        String equipeHote  = bout[1];
        String equipeInvite = bout[2];
        String scoreHote = bout[3];
        String scoreInvite = bout[4];
        String nomTournoi = bout[5];
        String ville = bout[6];
        String pays = bout[7];
        Boolean terrainNeutre = Boolean.parseBoolean(bout[8]);

        /*On crée un objet resultat avec toutes les infos*/
        ResultatData resultat = new ResultatData(date,equipeHote,equipeInvite,scoreHote,scoreInvite,nomTournoi,ville,pays,terrainNeutre);

        /*On ajoute le resultat a la liste des résultats*/
        resultatLigne.getResultatData().add(resultat);
    }
}

