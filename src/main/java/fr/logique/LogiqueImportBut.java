package fr.logique;

import fr.entites.But;
import fr.entites.Buteur;
import fr.entites.Equipe;
import fr.entites.Match;
import fr.entites_data.ButData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;


import static fr.logique.LogiqueImportMatch.trouverOuCreerEquipe;

public class LogiqueImportBut {
    private static final Logger logger = LoggerFactory.getLogger(LogiqueImportBut.class);

    // Constructeur privé pour empêcher une instanciation
    private LogiqueImportBut() {   }

    public static void importerBut(EntityManager em, ButData bd, int numLigne) {

        // Info extraite et convertie
        LocalDate date = bd.getDate();
        Boolean csc = bd.getCsc();
        Boolean penalty = bd.getPenalty();
        String minuteBute = bd.getMinuteScore();
        Integer minute = null;

        // info extraite
        String nomEquipeHote = bd.getEquipeHote();
        String nomEquipeInvite = bd.getEquipeInvite();
        String nomEquipeButeur = bd.getEquipeButeur();
        String nomButeur = bd.getButeur();


        // appel de plusieurs méthodes pour chercher ou alors créer
        Equipe equipeHote = trouverOuCreerEquipe(em, nomEquipeHote);
        Equipe equipeInvite = trouverOuCreerEquipe(em, nomEquipeInvite);
        Equipe equipeButeur = trouverOuCreerEquipe(em, nomEquipeButeur);

        //  Vérifier si un match identique existe déjà
        Match match = em.createQuery(
                        "SELECT m FROM Match m " +
                                "WHERE m.dateMatch = :date " +
                                "AND m.equipeHote = :equipeHote " +
                                "AND m.equipeInvite = :equipeInvite ",
                        Match.class)
                .setParameter("date", date)
                .setParameter("equipeHote", equipeHote)
                .setParameter("equipeInvite", equipeInvite)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (match == null) {
            logger.info("Pas de match trouvé");
            logger.info("Ligne {} ignorée : pas de match trouvé pour {} {} - {} | buteur = {}",
                    numLigne,date,nomEquipeHote ,nomEquipeInvite ,nomButeur);
            return;
        }

        // Recherche d'un buteur
        Buteur buteur = em.createQuery(
                        "SELECT b FROM Buteur b " +
                                "WHERE b.nomButeur = :nomButeur " +
                                "AND b.equipe = :equipe ",
                        Buteur.class)
                .setParameter("nomButeur", nomButeur)
                .setParameter("equipe", equipeButeur)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (buteur == null) {
            buteur = new Buteur();
            buteur.setNomButeur(nomButeur);
            buteur.setEquipe(equipeButeur);
            em.persist(buteur);
        }
            // Conversion de la minute en Integer
            if (minuteBute != null && !minuteBute.isBlank()
                    && !"NA".equalsIgnoreCase(minuteBute)) {

                // Simple numéro
                minute = Integer.parseInt(minuteBute);

            }
            // Création de l'entité But
            But but = new But();
            but.setMatch(match);
            but.setButeur(buteur);
            but.setCsc(csc);
            but.setPenalty(penalty);
            but.setMinuteBut(minute);

            // Sauvegarde en base
            em.persist(but);
        }
    }

