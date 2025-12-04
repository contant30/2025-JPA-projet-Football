package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe qui représente une séance de tir au but lors match de foot
 */
@Entity
@Table(name = "tir_but")
public class TirBut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTirBut;


    /**
     * Relation Many-to-One avec l'entité Equipe
     * Définit quelle equipe commence la séance tir au but
     */
    @ManyToOne
    @JoinColumn(name = "equipe_commence_tir")
    private Equipe equipeCommenceTir;

    /**
     * Relation Many-to-One avec l'entité Equipe
     * Définit quelle equipe gagne la séance de tir au but
     */
    @ManyToOne
    @JoinColumn(name = "equipe_Gagnant_tir")
    private Equipe equipeGagnanteTir;


    /**
     * Relation One-to-One avec Match.
     * Un match peut avoir une séance de tir au but.
     */
    @OneToOne
    @JoinColumn(name = "match_id")
    private Match match;


    /**
     * Constructeur sans paramètre pour JPA
     */
    public TirBut() { }

    /**
     * Constructeur qui permet de creer une séance de tir au but.
     *
     * @param equipeCommenceTir     nom de la première équipe qui commence la séance
     * @param equipeGagnanteTir     nom de l'équipe qui gagne la séance de tir au but
     * @param match                 nom du match associé à la séance de tir au but
     */
    public TirBut( Equipe equipeCommenceTir, Equipe equipeGagnanteTir, Match match) {
        this.equipeCommenceTir = equipeCommenceTir;
        this.equipeGagnanteTir = equipeGagnanteTir;
        this.match = match;
    }

    /**
     * @return l'identifiant de la séance de tir au but
     */
    public Integer getIdTirBut() {
        return idTirBut;
    }

    /**
     * @return le nom de l'équipe qui commence la séance le tir au but
     */
    public Equipe getEquipeCommenceTir() {
        return equipeCommenceTir;
    }

    /**
     * @param equipeCommenceTir le nouveau nom de l'équipe
     */
    public void setEquipeCommenceTir(Equipe equipeCommenceTir) {
        this.equipeCommenceTir = equipeCommenceTir;
    }

    /**
     * @return le nom de l'équipe qui a gagné la séance de tir au but
     */
    public Equipe getEquipeGagnanteTir() {
        return equipeGagnanteTir;
    }

    /**
     * @param equipeGagnanteTir le nouveau nom de l'équipe gagnante
     */
    public void setEquipeGagnanteTir(Equipe equipeGagnanteTir) {
        this.equipeGagnanteTir = equipeGagnanteTir;
    }

    /**
     * @return le match associé à la séance de tir au but
     */
    public Match getMatch() {
        return match;
    }

    /**
     * @param match le nouveau match associé à la séance de tir au but
     */
    public void setMatch(Match match) {
        this.match = match;
    }

    /**
     * @return les attributs de l'instance.
     */
    @Override
    public String toString() {
        return "TirBut{" +
                "idTirBut=" + idTirBut +
                ", equipeCommenceTir='" + equipeCommenceTir + '\'' +
                ", equipeGagnanteTir='" + equipeGagnanteTir + '\'' +
                ", match=" + match +
                '}';
    }
}
