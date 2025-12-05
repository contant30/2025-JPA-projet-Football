package fr.utilitaire;

import fr.entites.Equipe;

import javax.persistence.EntityManager;
import java.util.List;

public class EquipeService {

    private EntityManager em;

    /**
     * Constructeur qui initialise le service avec l'EntityManager
     * @param em  L'entityManager pour accéder à la base de donnée
     */
    public EquipeService(EntityManager em){
        this.em = em;
    }

    /**
     * Methode pour créer une équipe si elle n'existe pas
     */
    public void creerNouvelleEquipe(String nom){
        Equipe existe = getByNom(nom);
        if (existe ==null){ // si l'équipe n'existe pas
            try {
                em.getTransaction().begin();
                Equipe nouvelleEquipe = new Equipe(); // on crée une nouvelle équipe
                nouvelleEquipe.setPaysEquipe(nom);// on définit le nom de l'équipe
                em.persist(nouvelleEquipe);// on envoie à la base de donnée
                em.getTransaction().commit();// on valide la transaction avec un message
                System.out.println("Nouvelle equipe créée : "+nom);
            } catch (Exception e){ // s'il y a une erreur, la transaction est annulé
                em.getTransaction().rollback();
                System.out.println("Problème lors de la création d'équipe");
            }
        }
    }


    /**
     * Cherche une équipe par son ID en base de données
     * @param id l'ID de l'équipe recherchée
     * @return l'équipe trouvée
     */
    public Equipe getById(Integer id){
        return em.find(Equipe.class, id);
    }

    /**
     * Cherche une équipe par son nom en base de données
     * @param nom le nom du pays de l'équipe à rechercher
     * @return l'équipe trouvée
     */
    public Equipe getByNom(String nom){
        try {// on crée une requête, on remplace le nom par le pays recherché, on ne veut qu'un résultat
            return em.createQuery("SELECT e FROM Equipe e WHERE e.paysEquipe = :nom",Equipe.class).setParameter("nom",nom).getSingleResult();
        } catch (Exception e) {// Si on a une exception, on l'attrape et on retourne null
            return null;
        }
    }

    /**
     * On récupère toutes les équipes
     * @return une liste de toutes les équipes
     */
    public List<Equipe> getAll(){// On crée une requête vers la BD, on sélectionne dans une liste toutes les équipes
        return em.createQuery("SELECT e FROM Equipe e",Equipe.class).getResultList();
    }

    /**
     * Vérification si une équipe existe
     */
    public boolean existeEquipe(String nom){
        return getByNom(nom) !=null;
    }


    /**
     * Modification d'une équipe
     */
    public void modifierEquipe(Equipe equipe) {
        try {
            em.getTransaction().begin();
            em.merge(equipe);
            em.getTransaction().commit();
            System.out.println("L'équipe est modifiée");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Problème lors de la modification équipe : " + e.getMessage());
        }
    }

    /**
     * Suppression d'une équipe
     */
    public void supprimerEquipe(Integer id) {
        try {
            Equipe equipe = getById(id);
            if (equipe != null) {
                em.getTransaction().begin();
                em.remove(equipe);
                em.getTransaction().commit();
                System.out.println("L'équipe est supprimée");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Problème lors de la suppression de l'équipe : " + e.getMessage());
        }
    }



}
