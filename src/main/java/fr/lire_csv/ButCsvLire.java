package fr.lire_csv;

import fr.entites_csv.ButCsv;
import fr.parseur.ParseurBut;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Classe qui permet de lire le contenu du fichier CSV goalscorers.csv
 * et le convertir en objet ButCsv
 */
public class ButCsvLire {
    private static final Logger logger = LoggerFactory.getLogger(ButCsvLire.class);


    // Constructeur privé pour empêcher une instanciation
    private ButCsvLire() {   }

    /** Lit le contenu du fichier en paramétrée de la méthode
     * et retourne une instance de ButCsv avec toutes les informations lues
     * @param cheminFichierBut chemin du fichier CSV goalscorers.csv
     * @return un objet ButCsv rempli avec les données du fichier,
     *  ou null en cas d'erreur de lecture
     */
    public static ButCsv LireBut(String cheminFichierBut){
        ButCsv butCsv = new ButCsv();

        List<String> lignes = null;
        try{
            File file = new File(cheminFichierBut);
            lignes = FileUtils.readLines(file,"UTF-8");

            // Supprime la première ligne qui est l'entête des noms des colonnes
            lignes.remove(0);

            for (String ligne: lignes){
                ParseurBut.ajoutLigneBut(butCsv, ligne);
            }
            return butCsv;
            // Si une erreur se produit lors de la lecture du fichier, renvoie null
        } catch (IOException e){
            logger.info("Erreur : {}", e.getMessage());
        return null;
        }
    }
}
