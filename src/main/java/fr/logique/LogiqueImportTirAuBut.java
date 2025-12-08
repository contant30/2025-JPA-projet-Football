package fr.logique;

import fr.entites.Equipe;
import fr.entites.Match;
import fr.entites.TirBut;
import fr.entitesData.TirAuButData;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class LogiqueImportTirAuBut {

    public static void importerTirAuBut(EntityManager em, TirAuButData tbd,int numLigne) {


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
            // soit tu crées le match ici, soit tu considères que c'est une erreur
            // pour l'instant on sort si pas de match
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
            // Sinon, on complète éventuellement les infos manquantes
            if (tirBut.getEquipeCommenceTir() == null) {
                tirBut.setEquipeCommenceTir(equipeCommence);
            }
            if (tirBut.getEquipeGagnanteTir() == null) {
                tirBut.setEquipeGagnanteTir(equipeGagnante);
            }
        }

    }

    // même méthode utilitaire que dans ta logique d'import de match
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









