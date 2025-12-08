package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe qui représente un but lors d'un match de foot
 */
@Entity
@Table(name = "but")

public class But {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "penalty")
    private Boolean penalty;

    @Column(name = "contre_son_camp")
    private Boolean csc;

    @Column(name = "minute_but")
    private Integer minuteBut;

    /**
     * Relation Many-to-One avec l'entité buteur
     * represente les buteurs d'un but
     */
    @ManyToOne
    @JoinColumn(name = "buteur_id")
    private Buteur buteur;

    /**
     * Relation Many-to-One avec l'entité match
     * représente un match
     */
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    /**
     * Constructeur sans paramètre pour JPA
     */
    public But() { }

    /**
     * Constructeur qui permet de creer un but
     * @param minuteBut     minute à laquelle le but a été marqué
     * @param buteur        nom du buteur
     * @param match         nom du match
     * @param csc           si le but a été marqué contre son camp
     * @param penalty       si le but a été marqué sur penalty
     */
    public But(Integer minuteBut, Buteur buteur, Match match, Boolean csc, Boolean penalty) {
        this.minuteBut = minuteBut;
        this.buteur = buteur;
        this.match = match;
        this.csc = csc;
        this.penalty = penalty;
    }

    /**
     * @return l'id d'un but
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return un match
     */
    public Match getMatch() {
        return match;
    }

    /**
     * @param match un nouveau match
     */
    public void setMatch(Match match) {
        this.match = match;
    }

    /**
     * @return un buteur
     */
    public Buteur getButeur() {
        return buteur;
    }

    /**
     * @param buteur un nouveau buteur
     */
    public void setButeur(Buteur buteur) {
        this.buteur = buteur;
    }

    /**
     * @return la minute d'un but
     */
    public Integer getMinuteBut() {
        return minuteBut;
    }

    /**
     * @param minuteBut la nouvelle minute d'un but
     */
    public void setMinuteBut(Integer minuteBut) {
        this.minuteBut = minuteBut;
    }

    /**
     * @return un but contre son camp
     */
    public Boolean getCsc() {
        return csc;
    }

    /**
     * @param csc un nouveau but contre son camp
     */
    public void setCsc(Boolean csc) {
        this.csc = csc;
    }

    /**
     * @return un but sur penalty
     */
    public Boolean getPenalty() {
        return penalty;
    }

    /**
     * @param penalty un nouveau but sur penalty
     */
    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "But{" +
                "id=" + id +
                ", penalty=" + penalty +
                ", csc=" + csc +
                ", minuteBut=" + minuteBut +
                ", buteur=" + buteur +
                ", match=" + match +
                '}';
    }
}
