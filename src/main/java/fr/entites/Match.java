package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

/**
 * Classe qui représente un match de foot
 * Le match est constitué de deux équipes internationales
 */
@Entity
@Table(name = "match_foot")
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatch;

    @Column(name = "date_match")
    private LocalDate dateMatch;

    @Column (name = "score_hote")
    private Integer scoreHote;

     @Column (name = "score_invite")
    private Integer scoreInvite;

     @Column(name = "terrain_neutre")
    private Boolean neutre;

    /**
     * Relation Many-to-One avec l'entité Tournoi.
     * tournoi_id est la cle étrangère de la table Tournoi.
     */
    @ManyToOne
    @JoinColumn(name = "tournoi_id")
    private Tournoi tournoi;

    /**
     * Relation Many-to-One avec l'entité Lieu
     * lieu_id est la clé étrangère de la table Lieu
     */
    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    /**
     * Relation One-to-Many avec TirBut.
     * Un match peut contenir plusieurs matchs.
     */
    @OneToMany(mappedBy = "tournoi")
    private Set<Match> matchs;

    /**
     * Relation One-to-One avec TirBut.
     * Une séance de tir au but doit avoir un match.
     */
    @OneToOne(mappedBy = "match")
    private TirBut tirBut;



    /**
     * Constructeur sans paramètre pour JPA
     */
    public Match() {  }




}
