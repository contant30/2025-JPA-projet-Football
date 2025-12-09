package fr.importer;

import fr.entites_csv.ButCsv;
import fr.entites_data.ButData;
import fr.lire_csv.ButCsvLire;
import fr.logique.LogiqueImportBut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Classe qui permet d'importer les buts de match
 * depuis un fichier CSV goalscorers
 */
public class ImportCsvBut {

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

        // Permet de calculer le nombre de buts importé
        int nbImportes = 0;

        int numLigne = 0;

        int batchSize = 1000;


        try{
            emf = Persistence.createEntityManagerFactory("resultats_footballs");
            em = emf.createEntityManager();
            tx = em.getTransaction();

            // Récupération du chemin du fichier CSV depuis le dossier resources
            String filePath = ClassLoader.getSystemClassLoader().getResource("goalscorers.csv").getFile();
            // Lecture du fichier CSV et récupération des données à importer
            ButCsv LireBut = ButCsvLire.LireBut(filePath);

        // démarrage de la transaction et regroupe toutes les opérations en une
        tx.begin();

        // Boucle sur chaque ligne du CSV et qui appel une methode pour
        // créer ou mettre à jour une entité sur la base de donnée
        for (ButData bd : LireBut.getButData()){
            numLigne++;
            LogiqueImportBut.importerBut(em, bd, numLigne);
            nbImportes++;

            // permet d'afficher tous les 500 buts importés
            if (nbImportes % 500 == 0) {
                long fin = System.currentTimeMillis();
                long dureeBatchMs = fin - debut500;
                double dureeBatchSec = dureeBatchMs / 1000.0;


                System.out.println("Lignes traitées : " + nbImportes
                        + " | temps pour les 500 dernières : "
                        + dureeBatchSec + " s");
                // relance le temps pour le prochain bloc de 500
                debut500 = fin;
            }
            if (nbImportes % batchSize == 0) {
                em.flush();   // exécute les INSERT/UPDATE en base
                em.clear();   // vide le contexte de persistance
            }
        }

        // Toute les operations sont validé en basse
        tx.commit();

            // fin de l'enregistrement de la mesure total
            long fin = System.currentTimeMillis();
            long dureeMs = fin - debut;
            double dureeSec = dureeMs / 1000.0;
            double dureeMin = dureeSec / 60.0;

            // Affiche les statistique d'import
            System.out.println("Nombre de buts importés : " + nbImportes);
            System.out.println("Temps d'import : " + dureeMin + " minute (" + dureeSec + " s)");

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
