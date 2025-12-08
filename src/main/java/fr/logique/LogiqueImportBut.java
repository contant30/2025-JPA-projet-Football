package fr.logique;

import fr.entites.But;
import fr.entites.Buteur;
import fr.entites.Equipe;
import fr.entites.Match;
import fr.entitesData.ButData;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static fr.logique.LogiqueImportMatch.trouverOuCreerEquipe;

public class LogiqueImportBut {

    //private static final Map<String, Buteur> cacheButeurs = new HashMap<>();

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


        // Conversion des noms en entités Equipe
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
            System.err.println("Pas de match trouvé");
            System.err.println("Ligne " + numLigne + " ignorée : pas de match trouvé pour "
                    + date + " " + nomEquipeHote + " - " + nomEquipeInvite
                    + " | buteur = " + nomButeur);
            return;
        }

       //Buteur buteur = trouverOuCreerButeur(em, nomButeur, equipeButeur);

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

//    private static Buteur trouverOuCreerButeur(EntityManager em, String nomButeur, Equipe equipeButeur) {
//        String cle = nomButeur + "|" + equipeButeur.getIdEquipe();
//
//        Buteur buteur = cacheButeurs.get(cle);
//        if (buteur != null) {
//            return buteur;
//        }
//
//        buteur = em.createQuery(
//                        "SELECT b FROM Buteur b " +
//                                "WHERE b.nomButeur = :nomButeur " +
//                                "AND b.equipe = :equipe",
//                        Buteur.class)
//                .setParameter("nomButeur", nomButeur)
//                .setParameter("equipe", equipeButeur)
//                .getResultStream()
//                .findFirst()
//                .orElse(null);
//
//        if (buteur == null) {
//            buteur = new Buteur();
//            buteur.setNomButeur(nomButeur);
//            buteur.setEquipe(equipeButeur);
//            em.persist(buteur);
//        }
//
//        cacheButeurs.put(cle, buteur);
//        return buteur;
//    }
    }

