package fr.importer;

import fr.entitesCsv.TirAuButCsv;
import fr.entitesData.TirAuButData;
import fr.lireCSV.TirAuButCsvLire;
import fr.logique.LogiqueImportTirAuBut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ImportCsvTirAuBut {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("resultats_football");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        String filePath = ClassLoader.getSystemClassLoader().getResource("testtiraubut.csv").getFile();
        TirAuButCsv LireTirAuBut = TirAuButCsvLire.LireTirAuBut(filePath);

        tx.begin();

        for (TirAuButData tbd : LireTirAuBut.getTirAuButData()) {
            System.out.println("[DEBUG] TirAuButData : " + tbd);
            LogiqueImportTirAuBut.importerTirAuBut(em, tbd);
        }
        tx.commit();

    }
}

