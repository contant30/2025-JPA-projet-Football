package fr.lire_csv;

import fr.entites_csv.ResultatCSV;
import fr.parseur.ParseurResultat;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** Classe qui permet de lire le contenu du fichier CSV results.csv
 * et le convertir en objet ResultatCsv
 */
public class ResultatCsvLire {

    /** Lit le contenu du fichier en paramétrée de la méthode
     * et retourne une instance de ResultatCsv avec toutes les informations lues
     * @param cheminFichierBut chemin du fichier CSV results.csv
     * @return un objet ResultatCsv rempli avec les données du fichier,
     *  ou null en cas d'erreur de lecture
     */
    public static ResultatCSV LireResultat(String cheminFichierBut){
        ResultatCSV resultatCSV = new ResultatCSV();

        List<String> lignes = null;
        try{
            File file = new File(cheminFichierBut);
            lignes = FileUtils.readLines(file,"UTF-8");

            // Supprime la première ligne qui est l'entête des noms des colonnes
            lignes.remove(0);

            for (String ligne: lignes){
                ParseurResultat.ajoutLigneResultat(resultatCSV, ligne);
            }
            return resultatCSV;
            // Si une erreur se produit lors de la lecture du fichier, renvoie null
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}