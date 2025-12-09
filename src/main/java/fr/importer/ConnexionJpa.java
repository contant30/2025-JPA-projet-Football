package fr.importer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnexionJpa {
    private static final Logger logger = LoggerFactory.getLogger(ConnexionJpa.class);

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("resultats_footballs");
            em = emf.createEntityManager();

            logger.info("Connexion JPA établie !");
        } catch (Exception e) {
            logger.error("Erreur lors de la connexion JPA", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
