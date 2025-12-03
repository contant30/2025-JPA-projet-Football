package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Classe qui représente une séance de tir au but lors match de foot
 */
@Entity
@Table(name = "tir_but")

public class TirBut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTirBut;

    @Column(name = "equipe_commence_tir")
    private String equipeCommenceTir;

    @Column(name = "equipe_gagante_tir")
    private String equipeGagnanteTir;

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
     * @param idTirBut              identifiant de la séance de tir au but
     * @param equipeCommenceTir     nom de la première équipe qui commence la séance
     * @param equipeGagnanteTir     nom de l'équipe qui gagne la séance de tir au but
     * @param match                 nom du match associé à la séance de tir au but
     */
    public TirBut(Integer idTirBut, String equipeCommenceTir, String equipeGagnanteTir, Match match) {
        this.idTirBut = idTirBut;
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
    public String getEquipeCommenceTir() {
        return equipeCommenceTir;
    }

    /**
     * @param equipeCommenceTir le nouveau nom de l'équipe
     */
    public void setEquipeCommenceTir(String equipeCommenceTir) {
        this.equipeCommenceTir = equipeCommenceTir;
    }

    /**
     * @return le nom de l'équipe qui a gagné la séance de tir au but
     */
    public String getEquipeGagnanteTir() {
        return equipeGagnanteTir;
    }

    /**
     * @param equipeGagnanteTir le nouveau nom de l'équipe gagnante
     */
    public void setEquipeGagnanteTir(String equipeGagnanteTir) {
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
