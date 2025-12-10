package fr.logique;

import fr.entites.Equipe;
import fr.entites.Match;
import fr.entites.TirBut;
import fr.entites_data.TirAuButData;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class LogiqueImportTirAuBut {

    // Constructeur privé pour empêcher une instanciation
    private LogiqueImportTirAuBut() {  }

    public static void importerTirAuBut(EntityManager em, TirAuButData tbd) {

        // Info extraite et convertie
        LocalDate date = tbd.getDate();

        // info extraite
        String nomEquipeHote = tbd.getEquipeHote();
        String nomEquipeInvite = tbd.getEquipeInvite();
        String nomEquipeGagnanteTir = tbd.getEquipeGagnanteTir();
        String nomEquipeCommenceTir = tbd.getEquipeCommenceTir();

        // appel de plusieurs méthodes pour chercher ou alors créer
        Equipe equipeHote       = trouverOuCreerEquipe(em, nomEquipeHote);
        Equipe equipeInvite     = trouverOuCreerEquipe(em, nomEquipeInvite);
        Equipe equipeGagnante  = trouverOuCreerEquipe(em, nomEquipeGagnanteTir);
        Equipe equipeCommence  = trouverOuCreerEquipe(em, nomEquipeCommenceTir);

        //  Recherche d'un match correspondant à la séance de tir au but
        Match match = em.createQuery(
                        "SELECT m FROM Match m " +
                                "WHERE m.dateMatch = :date " +
                                "AND m.equipeHote = :equipeHote " +
                                "AND m.equipeInvite = :equipeInvite",
                        Match.class)
                .setParameter("date", date)
                .setParameter("equipeHote", equipeHote)
                .setParameter("equipeInvite", equipeInvite)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (match == null) {
            return;
        }

        // Vérifier si une séance de tir au but existe déjà pour ce match
        TirBut tirBut = em.createQuery(
                        "SELECT t FROM TirBut t WHERE t.match = :match",
                        TirBut.class)
                .setParameter("match", match)
                .getResultStream()
                .findFirst()
                .orElse(null);

        // Si aucune séance trouvée, on la crée
        if (tirBut == null) {
            tirBut = new TirBut();
            tirBut.setMatch(match);
            tirBut.setEquipeCommenceTir(equipeCommence);
            tirBut.setEquipeGagnanteTir(equipeGagnante);
            em.persist(tirBut);
        } else {

            // Si une séance excite, on complète les infos manquantes si besoin
            if (tirBut.getEquipeCommenceTir() == null) {
                tirBut.setEquipeCommenceTir(equipeCommence);
            }
            if (tirBut.getEquipeGagnanteTir() == null) {
                tirBut.setEquipeGagnanteTir(equipeGagnante);
            }
        }
    }

     /* Méthode cherche une équipe par son nom,
      * ou la crée si elle n'existe pas encore.
      */
    private static Equipe trouverOuCreerEquipe(EntityManager em, String nomEquipe) {
        Equipe equipe = em.createQuery(
                        "SELECT e FROM Equipe e WHERE e.paysEquipe = :nom",
                        Equipe.class)
                .setParameter("nom", nomEquipe)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (equipe == null) {
            equipe = new Equipe();
            equipe.setPaysEquipe(nomEquipe);
            em.persist(equipe);
        }
        return equipe;
    }
}









