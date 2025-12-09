package fr.importer;

import fr.entites_csv.TirAuButCsv;
import fr.entites_data.TirAuButData;
import fr.lire_csv.TirAuButCsvLire;
import fr.logique.LogiqueImportTirAuBut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Classe qui permet d'importer les séances de tires buts
 * depuis un fichier CSV shootouts
 */
public class ImportCsvTirAuBut {

    public static void main(String[] args) {

        // Permet de mesurer le temps d'import
        long debut = System.currentTimeMillis();
        // Permet de mesurer le temps d'import à chaque 500 ligne
        long debut500= debut;

        EntityManagerFactory emf = null;
        // Permet de manipuler les entités liées à la base de donnée
        EntityManager em = null;
        //Permet de regrouper plusieurs opérations en une
        EntityTransaction tx = null;

        // Permet de calculer le nombre de séances de tirs buts importé
        int nbSeanceTirAuBut= 0;

        int numLigne = 0;

        int batchSize = 1000;


        try{
        emf = Persistence.createEntityManagerFactory("resultats_footballs");
        em = emf.createEntityManager();
        tx = em.getTransaction();

        // Récupération du chemin du fichier CSV depuis le dossier resources
        String filePath = ClassLoader.getSystemClassLoader().getResource("shootouts.csv").getFile();
        // Lecture du fichier CSV et récupération des données à importer
        TirAuButCsv LireTirAuBut = TirAuButCsvLire.LireTirAuBut(filePath);

        // démarrage de la transaction et regroupe toutes les opérations en une
        tx.begin();

        // Boucle sur chaque ligne du CSV et qui appel une methode pour
        // créer ou mettre à jour une entité sur la base de donnée
        for (TirAuButData tbd : LireTirAuBut.getTirAuButData()) {
            numLigne++;
            LogiqueImportTirAuBut.importerTirAuBut(em, tbd,numLigne);
            nbSeanceTirAuBut++;

            // permet d'afficher toutes les 500 tires au butes importés
            if (nbSeanceTirAuBut % 500 == 0) {

                long finBatch = System.currentTimeMillis();
                long dureeBatchMs = finBatch - debut500;
                double dureeBatchSec = dureeBatchMs / 1000.0;

                System.out.println("Lignes traitées : " + nbSeanceTirAuBut+ " | temps pour les 500 dernières : "
                        + dureeBatchSec + " s");

                // relance le temps pour le prochain bloc de 500
                debut500 = finBatch;
            }
            if (nbSeanceTirAuBut % batchSize == 0) {
                em.flush();   // exécute les INSERT/UPDATE en base
                em.clear();   // vide le contexte de persistance
            }
        }


        tx.commit(); // Toute les operations sont validé en basse
            long fin = System.currentTimeMillis(); // fin mesure
            long dureeMs = fin - debut;
            double dureeSec = dureeMs / 1000.0;

            System.out.println("Nombre de séance importés : " + nbSeanceTirAuBut);
            System.out.println("Temps d'import : " + dureeMs + " ms (" + dureeSec + " s)");

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