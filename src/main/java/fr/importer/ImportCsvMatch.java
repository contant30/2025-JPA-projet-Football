package fr.importer;

import fr.entitesCsv.ResultatCSV;
import fr.entitesData.ResultatData;
import fr.lireCSV.ResultatCsvLire;
import fr.logique.LogiqueImportMatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ImportCsvMatch {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("resultats_football");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        String filePath = ClassLoader.getSystemClassLoader().getResource("testResultat.csv").getFile();
        ResultatCSV LireResultat = ResultatCsvLire.LireResultat(filePath);



        tx.begin();

        for (ResultatData rd : LireResultat.getResultatData()) {
                LogiqueImportMatch.importerResultat(em, rd);
            }
        tx.commit();



    }
}