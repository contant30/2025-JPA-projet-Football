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
 * Classe qui représente le lieu où le match a lieu
 */
@Entity
@Table(name = "lieu")

public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLieu;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    /**
     * Relation One-to-Many avec Match.
     * Un lieu peut accueillir plusieurs matchs.
     */
    @OneToMany(mappedBy = "lieu")
    private Set<Match> matchs;

    /**
     * Constructeur sans paramètre pour JPA
     */
    public Lieu() {}

    /**
     * Constructeur qui permet de creer un lieu
     * @param idLieu    identifiant du lieu
     * @param ville     nom de la ville
     * @param pays      nom du pays
     * @param matchs    ensemble des matchs d'un lieu
     */
    public Lieu(Integer idLieu, String ville, String pays, Set<Match> matchs) {
        this.idLieu = idLieu;
        this.ville = ville;
        this.pays = pays;
        this.matchs = matchs;
    }

    /**
     * Getter qui retourne l'id du lieu
     * @return l'id d'un lieu
     */
    public Integer getIdLieu() {
        return idLieu;
    }

    /**
     * Getter qui retourne le nom d'une ville
     * @return le nom de la ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter qui permet de modifier le nom d'une ville
     * @param ville le nouveau nom de la ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Getter qui retourne le nom d'un pays
     * @return le nom du pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter qui permet de modifier le nom d'un pays
     * @param pays le nouveau nom du pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Getter qui retourne l'ensemble des matchs associé à un lieu
     * @return un Set contenant les matchs du lieu
     */
    public Set<Match> getMatchs() {
        return matchs;
    }

    /**
     * Setter qui permet de modifier les matchs d'un lieu
     * @param matchs un Set avec les nouveaux matchs d'un lieu
     */
    public void setMatchs(Set<Match> matchs) {
        this.matchs = matchs;
    }

    /**
     * @return les attributs de l'instance.
     */
    @Override
    public String toString() {
        return "Lieu{" +
                "idLieu=" + idLieu +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", matchs=" + matchs +
                '}';
    }
}
