package fr.lireCSV;

import fr.entitesCsv.TirAuButCsv;
import fr.parseur.ParseurTirAuBut;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** Classe qui permet de lire le contenu du fichier CSV shootouts.csv
 * et le convertir en objet TirAuButCsv
 */
public class TirAuButCsvLire {
 /** Lit le contenu du fichier en paramétrée de la méthode
 * et retourne une instance de TirAuButCsv avec toutes les informations lues
 * @param cheminFichierBut chemin du fichier CSV shootouts.csv
 * @return un objet TirAuButCsv rempli avec les données du fichier,
 *  ou null en cas d'erreur de lecture
 */

public static TirAuButCsv LireTirAuBut(String cheminFichierBut){
    TirAuButCsv tirAuButCsvCSV = new TirAuButCsv();

    List<String> lignes = null;
    try{
        File file = new File(cheminFichierBut);
        lignes = FileUtils.readLines(file,"UTF-8");

        // Supprime la première ligne qui est l'entête des noms des colonnes
        lignes.remove(0);

        for (String ligne: lignes){
            ParseurTirAuBut.ajoutLigneTirAuBut(tirAuButCsvCSV, ligne);
        }
        return tirAuButCsvCSV;
        // Si une erreur se produit lors de la lecture du fichier, renvoie null
    } catch (IOException e){
        System.out.println(e.getMessage());
        return null;
    }
}
}