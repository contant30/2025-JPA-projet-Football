package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Classe qui représente le nom du tournoi
 */
@Entity
@Table(name = "nom_tournoi")

public class Tournoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTournoi;

    @Column(name = "nom")
    private String nomTournoi;

    /**
     * Relation One-to-Many avec Match.
     * Un tournoi peut contenir plusieurs matchs.
     */
    @OneToMany(mappedBy = "tournoi")
    private Set<Match> matchs;

    /**
     * Constructeur sans paramètre pour JPA
     */
    public Tournoi() { }

    /**
     * Constructeur qui permet de creer un tournoi
     * @param nomTournoi nom du tournoi
     * @param matchs     ensemble des matchs du tournoi
     */
    public Tournoi(String nomTournoi, Set<Match> matchs) {
        this.nomTournoi = nomTournoi;
        this.matchs = matchs;
    }

    /**
     * @return l'id d'un tournoi
     */
    public Integer getIdTournoi() {
        return idTournoi;
    }

    /**
     * @return le nom du tournoi
     */
    public String getNomTournoi() {
        return nomTournoi;
    }

    /**
     * @param nomTournoi le nouveau nom du tournoi
     */
    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    /**
     * @return un Set contenant les matchs du tournoi
     */
    public Set<Match> getMatchs() {
        return matchs;
    }

    /**
     * @param matchs un Set avec les nouveaux matchs d'un tournoi
     */
    public void setMatchs(Set<Match> matchs) {
        this.matchs = matchs;
    }

    /**
     * @return les attributs de l'instance.
     */
    @Override
    public String toString() {
        return "Tournoi{" +
                "idTournoi=" + idTournoi +
                ", nomTournoi='" + nomTournoi + '\'' +
                ", matchs=" + matchs +
                '}';
    }
}
