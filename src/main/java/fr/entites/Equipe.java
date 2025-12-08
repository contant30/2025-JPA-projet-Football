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
 * Classe qui représente une équipe de foot
 * une équipe représente son pays
 */
@Entity
@Table(name = "equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipe;

     @Column(name = "pays_equipe")
    private String paysEquipe;

    /**
     * Relation OneToMany avec l'entité TirBut
     * représentant l'équipe qui commence la séance de tirs au but
     */
    @OneToMany(mappedBy = "equipeCommenceTir")
    private Set<TirBut> tirButCommences;

    /**
     * Relation OneToMany avec l'entité TirBut
     * représentant l'équipe qui gagne la séance de tir au but
     */
    @OneToMany(mappedBy = "equipeGagnanteTir")
    private Set<TirBut> tirButGagnant;

    /**
     * Relation One-to-Many avec l'entité Match.
     * représente les matchs d'une équipe hote
     */
    @OneToMany(mappedBy = "equipeHote")
    private Set<Match> matchsHote;

    /**
     * Relation One-to-Many avec l'entité Match.
     * représente les matchs d'une équipe invité
     */
    @OneToMany(mappedBy = "equipeInvite")
    private Set<Match> matchsInvite;

    /**
     * Relation One-to-Many avec buteur.
     * Une équipe peut avoir plusieurs buteurs.
     */
    @OneToMany(mappedBy = "equipe")
    private Set<Buteur> buteurs;

    /**
     * Constructeur sans paramètre pour JPA
     */
    public Equipe() {    }

    /**
     * Constructeur qui permet de creer une équipe
     * @param paysEquipe nom de l'équipe
     */
    public Equipe(String paysEquipe) {
        this.paysEquipe = paysEquipe;
    }

    /**
     * @return l'id d'une équipe
     */
    public Integer getIdEquipe() {
        return idEquipe;
    }

    /**
     * @return le nom du pays de l'équipe
     */
    public String getPaysEquipe() {
        return paysEquipe;
    }

    /**
     * @param paysEquipe le nouveau nom de l'équipe
     */
    public void setPaysEquipe(String paysEquipe) {
        this.paysEquipe = paysEquipe;
    }

    /**
     * @return l'équipe qui commence la séance de tir au but
     */
    public Set<TirBut> getTirButCommences() {
        return tirButCommences;
    }

    /**
     * @param tirButCommences un Set avec la nouvelle équipe qui commence
     */
    public void setTirButCommences(Set<TirBut> tirButCommences) {
        this.tirButCommences = tirButCommences;
    }

    /**
     * @return l'équipe qui gagne la séance de tir au but
     */
    public Set<TirBut> getTirButGagnant() {
        return tirButGagnant;
    }

    /**
     * @param tirButGagnant un Set avec la nouvelle équipe qui gagne la séance
     */
    public void setTirButGagnant(Set<TirBut> tirButGagnant) {
        this.tirButGagnant = tirButGagnant;
    }

    /**
     * @return un Set contenant les matchs d'une équipe hôte
     */
    public Set<Match> getMatchsHote() {
        return matchsHote;
    }

    /**
     * @param matchsHote un Set avec les nouveaux matchs d'une équipe hôte
     */
    public void setMatchsHote(Set<Match> matchsHote) {
        this.matchsHote = matchsHote;
    }

    /**
     * @return un Set contenant les matchs d'une équipe invité
     */
    public Set<Match> getMatchsInvite() {
        return matchsInvite;
    }

    /**
     * @param matchsInvite un Set avec les nouveaux matchs d'une équipe invité
     */
    public void setMatchsInvite(Set<Match> matchsInvite) {
        this.matchsInvite = matchsInvite;
    }

    /**
     * @return un Set contenant les buteurs d'une équipe
     */
    public Set<Buteur> getButeurs() {
        return buteurs;
    }

    /**
     * @param buteurs un Set avec les nouveaux buteurs d'une équipe
     */
    public void setButeurs(Set<Buteur> buteurs) {
        this.buteurs = buteurs;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "idEquipe=" + idEquipe +
                ", paysEquipe='" + paysEquipe + '\'' +
                ", tirButCommences=" + tirButCommences +
                ", tirButGagnant=" + tirButGagnant +
                ", matchsHote=" + matchsHote +
                ", matchsInvite=" + matchsInvite +
                ", buteurs=" + buteurs +
                '}';
    }
}
