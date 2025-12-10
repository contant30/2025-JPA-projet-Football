package fr.logique;

import fr.entites.Equipe;
import fr.entites.Lieu;
import fr.entites.Match;
import fr.entites.Tournoi;
import fr.entites_data.ResultatData;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class LogiqueImportMatch {

    // Constructeur privé pour empêcher une instanciation
    private LogiqueImportMatch() {   }

    public static void importerResultat(EntityManager em, ResultatData rd) {

        // Info extraite et convertie
        LocalDate date = rd.getDate();
        Integer scoreHote = Integer.parseInt(rd.getScoreHote());
        Integer scoreInvite = Integer.parseInt(rd.getScoreInvite());
        Boolean neutre = rd.getTerrainNeutre();

        // info extraite
        String nomEquipeHote = rd.getEquipeHote();
        String nomEquipeInvite = rd.getEquipeInvite();
        String nomTournoi = rd.getNomTournoi();
        String ville = rd.getVille();
        String pays = rd.getPays();

        // appel de plusieurs méthodes pour chercher ou alors créer
        Equipe equipeHote = trouverOuCreerEquipe(em, nomEquipeHote);
        Equipe equipeInvite = trouverOuCreerEquipe(em, nomEquipeInvite);
        Tournoi tournoi = trouverOuCreerTournoi(em, nomTournoi);
        Lieu lieu = trouverOuCreerLieu(em, ville, pays);

        //  Vérifier si un match identique existe déjà
        Match match = em.createQuery(
                        "SELECT m FROM Match m " +
                                "WHERE m.dateMatch = :date " +
                                "AND m.scoreHote = :scoreHote " +
                                "AND m.scoreInvite = :scoreInvite " +
                                "AND m.neutre = :neutre " +
                                "AND m.equipeHote = :equipeHote " +
                                "AND m.equipeInvite = :equipeInvite " +
                                "AND m.tournoi = :tournoi " +
                                "AND m.lieu = :lieu ",
                        Match.class)
                .setParameter("date", date)
                .setParameter("scoreHote", scoreHote)
                .setParameter("scoreInvite", scoreInvite)
                .setParameter("neutre", neutre)
                .setParameter("equipeHote", equipeHote)
                .setParameter("equipeInvite", equipeInvite)
                .setParameter("tournoi", tournoi)
                .setParameter("lieu", lieu)
                .getResultStream()
                .findFirst()
                .orElse(null);

        //  Si pas match trouvé, un nouvel objet Match est créé
        if (match == null) {
            match = new Match();
            match.setDateMatch(date);
            match.setScoreHote(scoreHote);
            match.setScoreInvite(scoreInvite);
            match.setNeutre(neutre);
            match.setEquipeHote(equipeHote);
            match.setEquipeInvite(equipeInvite);
            match.setTournoi(tournoi);
            match.setLieu(lieu);

            em.persist(match);// envoie en base de donnée

            // Si un match est trouvé, on complète les infos manquantes si besoin
        } else {
            if (match.getTournoi() == null) {
                match.setTournoi(tournoi);
            }
            if (match.getEquipeHote() == null) {
                match.setEquipeHote(equipeHote);
            }
            if (match.getEquipeInvite() == null) {
                match.setEquipeInvite(equipeInvite);
            }
            if (match.getLieu() == null) {
                match.setLieu(lieu);
            }

        }
    }

    /* Méthode cherche une équipe par son nom,
     * ou la crée si elle n'existe pas encore.
     */
    static Equipe trouverOuCreerEquipe(EntityManager em, String nomEquipe) {
         Equipe equipe = em.createQuery("SELECT e FROM Equipe e WHERE e.paysEquipe = :nom ", Equipe.class)
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

    /* Méthode cherche un tournoi par son nom,
     * ou le crée s'il n'existe pas encore.
     */
    private static Tournoi trouverOuCreerTournoi(EntityManager em, String nomTournoi) {
        Tournoi tournoi = em.createQuery("SELECT t FROM Tournoi t WHERE t.nomTournoi = :nom ", Tournoi.class)
            .setParameter("nom", nomTournoi)
            .getResultStream()
            .findFirst()
            .orElse(null);

        if (tournoi == null) {
            tournoi = new Tournoi();
            tournoi.setNomTournoi(nomTournoi);
            em.persist(tournoi);
         }
         return tournoi;
    }

    /* Méthode cherche un lieu par sa ville et son pays,
     * ou le crée s'il n'existe pas encore.
     */
    private static Lieu trouverOuCreerLieu(EntityManager em, String ville, String pays) {
        Lieu lieu = em.createQuery("SELECT l FROM Lieu l WHERE l.ville =:ville AND l.pays = :pays ", Lieu.class)
            .setParameter("ville", ville)
            .setParameter("pays", pays)
            .getResultStream()
            .findFirst()
            .orElse(null);

        if (lieu == null) {
            lieu = new Lieu();
            lieu.setVille(ville);
            lieu.setPays(pays);
            em.persist(lieu);
        }
        return lieu;
    }
}


