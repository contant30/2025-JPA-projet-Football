package fr.importer;

import fr.entites_csv.ResultatCSV;
import fr.entites_data.ResultatData;
import fr.lire_csv.ResultatCsvLire;
import fr.logique.LogiqueImportMatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe qui permet d'importer les resultats de match
 * depuis un fichier CSV results
 */

public class ImportCsvMatch {
    private static final Logger logger = LoggerFactory.getLogger(ImportCsvMatch.class);


    public static void main(String[] args) {

        // Permet de mesurer le temps d'import
        long debut = System.currentTimeMillis();
        // Permet de mesurer à chaque 500 ligne
        long debut500= debut;

        EntityManagerFactory emf = null;

        // Permet de manipuler les entités liées à la base de donnée
        EntityManager em = null;

        //Permet de regrouper plusieurs opérations en une
        EntityTransaction tx = null;

        // Permet de calculer le nombre de match importé
        int nbMatchImportes = 0;

        int numLigne = 0;

        int batchSize = 1000;

        try {
            emf =Persistence.createEntityManagerFactory("resultats_footballs");
            em= emf.createEntityManager();
            tx = em.getTransaction();

            // Récupération du chemin du fichier CSV depuis le dossier resources
            String filePath = ClassLoader.getSystemClassLoader().getResource("results.csv").getFile();
            // Lecture du fichier CSV et récupération des données à importer
            ResultatCSV lireResultat = ResultatCsvLire.LireResultat(filePath);

        // démarrage de la transaction et regroupe toutes les opérations en une
        tx.begin();

        // Boucle sur chaque ligne du CSV et qui appel une methode pour
        // créer ou mettre à jour une entité sur la base de donnée
        for (ResultatData rd : lireResultat.getResultatData()) {
            numLigne++;
            LogiqueImportMatch.importerResultat(em, rd, numLigne);
                nbMatchImportes++;

            // permet d'afficher tous les 500 matchs importés
            if (nbMatchImportes % 500 == 0) {
                long finBatch = System.currentTimeMillis();
                long dureeBatchMs = finBatch - debut500;
                double dureeBatchSec = dureeBatchMs / 1000.0;

                logger.info("Lignes traitées : {} | temps pour les 500 dernières : {} s", nbMatchImportes
                        ,dureeBatchSec);

                // relance le temps pour le prochain bloc de 500
                debut500 = finBatch;
             }
            if (nbMatchImportes % batchSize == 0) {
                em.flush();   // exécute les INSERT/UPDATE en base
                em.clear();   // vide le contexte de persistance
            }
        }

        // Toute les operations sont validé en basse
        tx.commit();

            // fin de l'enregistrement de la mesure
            long fin = System.currentTimeMillis();
            long dureeMs = fin - debut;
            double dureeSec = dureeMs / 1000.0;

            // Affiche les statistique d'import
            logger.info("Nombre de match importés : {}",nbMatchImportes);
            logger.info("Temps d'import : {} ms {} s" , dureeMs ,dureeSec);

        } catch (Exception e){
            // Si une erreur, un rollback est effectué
            if (tx != null && tx.isActive()){
                tx.rollback();
            } e.printStackTrace();// Affichage de l'erreur
        }finally {
            // Fermeture de l'EntityManager
            if (em != null && em.isOpen()){
                em.close();
            }
            // Fermeture de EntityManagerFactory
            if (emf != null && emf.isOpen()){
                emf.close();
            }
        }
    }
}