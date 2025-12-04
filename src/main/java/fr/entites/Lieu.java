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
     * @param ville     nom de la ville
     * @param pays      nom du pays
     * @param matchs    ensemble des matchs d'un lieu
     */
    public Lieu( String ville, String pays, Set<Match> matchs) {
        this.ville = ville;
        this.pays = pays;
        this.matchs = matchs;
    }

    /**
     * @return l'id d'un lieu
     */
    public Integer getIdLieu() {
        return idLieu;
    }

    /**
     * @return le nom de la ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville le nouveau nom de la ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return le nom du pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * @param pays le nouveau nom du pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * @return un Set contenant les matchs du lieu
     */
    public Set<Match> getMatchs() {
        return matchs;
    }

    /**
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
