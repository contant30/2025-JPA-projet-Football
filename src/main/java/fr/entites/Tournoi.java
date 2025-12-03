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
     * Relation One to Many avec Match
     */
    @OneToMany(mappedBy = "tournoi")
    private Set<Match> matchs;

    /**
     * Constructeur sans paramètre pour JPA
     */
    public Tournoi() { }

    /**
     * Constructeur qui permet de creer un tournoi
     * @param idTournoi identifiant du tournoi
     * @param nomTournoi nom du tournoi
     * @param matchs     ensemble des matchs du tournoi
     */
    public Tournoi(Integer idTournoi, String nomTournoi, Set<Match> matchs) {
        this.idTournoi = idTournoi;
        this.nomTournoi = nomTournoi;
        this.matchs = matchs;
    }

    /**
     * Getter qui retourne l'id du tournoi
     * @return l'id d'un tournoi
     */
    public Integer getIdTournois() {
        return idTournoi;
    }

    /**
     * Getter qui retourne le nom du tournoi
     * @return le nom du tournoi
     */
    public String getNomTournoi() {
        return nomTournoi;
    }

    /**
     * Setter qui permet de modifier le nom du tournoi
     * @param nomTournoi le nouveau nom du tournoi
     */
    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    /**
     * Getter qui retourne l'ensemble des matchs associés à un tournoi
     * @return un Set contenant les matchs du tournoi
     */
    public Set<Match> getMatchs() {
        return matchs;
    }

    /**
     * Setter qui permet de modifier les matchs d'un tournoi
     * @param matchs un Set avec les nouveaux matchs d'un tournoi
     */
    public void setMatchs(Set<Match> matchs) {
        this.matchs = matchs;
    }
}
