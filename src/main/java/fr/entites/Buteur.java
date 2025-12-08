package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Classe qui représente un buteur lors d'un match de foot
 */
@Entity
@Table(name = "buteur")

public class Buteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_buteur")
    private String nomButeur;

    /**
     * Relation Many-to-One avec l'entité equipe
     * représente les buteurs d'une équipe
     */
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    /**
     * Relation One-To-Many avec But
     * représentant les buts marqués par le buteur
     */
    @OneToMany (mappedBy = "buteur")
    private Set<But> buts;

    /**
     * Constructeur sans paramètre pour JPA
     */
    public Buteur() {}

    /**
     * Constructeur qui permet de un buteur
     * @param nomButeur     nom du buteur
     * @param equipe        nom de l'équipe du buteur
     */
    public Buteur(String nomButeur, Equipe equipe) {
        this.nomButeur = nomButeur;
        this.equipe = equipe;
    }

    /**
     * @return l'id du buteur
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return le nom buteur
     */
    public String getNomButeur() {
        return nomButeur;
    }

    /**
     * @param nomButeur le nouveau nom du buteur
     */
    public void setNomButeur(String nomButeur) {
        this.nomButeur = nomButeur;
    }

    /**
     * @return le nom de l'équipe du buteur
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * @param equipe le nouveau nom de l'équipe du buteur
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
     * @return un Set contenant les buteurs d'une équipe
     */
    public Set<But> getButs() {
        return buts;
    }
    /**
     * @param buts un Set avec les nouveaux buteurs d'une équipe
     */
    public void setButs(Set<But> buts) {
        this.buts = buts;
    }


}
