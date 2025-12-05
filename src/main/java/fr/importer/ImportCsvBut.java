package fr.importer;

import fr.entitesCsv.ButCsv;
import fr.entitesData.ButData;
import fr.lireCSV.ButCsvLire;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ImportCsvBut {
    public static void main(String[] args) {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("resultats_football");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();

        String filePath = ClassLoader.getSystemClassLoader().getResource("goalscorers.csv").getFile();
        ButCsv LireBut = ButCsvLire.LireBut(filePath);

        for (int i=0; i<LireBut.getButData().size();i++){
            ButData but= LireBut.getButData().get(i);
            System.out.println(but);
        }
//        System.out.println(LireBut);


//        em.close();
//        emf.close();




    }


    }

