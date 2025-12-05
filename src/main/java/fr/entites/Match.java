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

    @Column(name = "score_hote")
    private Integer scoreHote;

    @Column(name = "score_invite")
    private Integer scoreInvite;

    @Column(name = "terrain_neutre")
    private Boolean neutre;

    /**
     * Relation Many-to-One avec l'entité Tournoi.
     * represente un tournoi
     */
    @ManyToOne
    @JoinColumn(name = "tournoi_id")
    private Tournoi tournoi;

    /**
     * Relation Many-to-One avec l'entité Lieu
     * represente un lieu
     */
    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    /**
     * Relation Many-to-One avec l'entité equipe.
     * représente une équipe hôte.
     */
    @ManyToOne
    @JoinColumn(name = "equipe_hote")
    private Equipe equipeHote;

    /**
     * Relation Many-to-One avec l'entité equipe.
     * représente une équipe invité.
     */
    @ManyToOne
    @JoinColumn(name = "equipe_invite")
    private Equipe equipeInvite;

    /**
     * Relation One-to-One avec TirBut.
     * Une séance de tir au but doit avoir un match.
     */
    @OneToOne(mappedBy = "match")
    private TirBut tirBut;

    /**
     * Relation One-to-Many avec but.
     * Un match peut avoir plusieurs but.
     */
    @OneToMany(mappedBy = "match")
    private Set<But> buts;


    /**
     * Constructeur sans paramètre pour JPA
     */
    public Match() {
    }

    /**
     * Constructeur qui permet de creer un match
     *
     * @param dateMatch    représente la date du match
     * @param scoreHote    score de l'équipe hote
     * @param scoreInvite  score de l'équipe invité
     * @param neutre       si le match ce joue en terrain neutre
     * @param tournoi      le nom du tournoi
     * @param lieu         le lieu du match
     * @param equipeHote   le nom de l'équipe hote
     * @param equipeInvite le nom de l'équipe invité
     */
    public Match(LocalDate dateMatch, Integer scoreHote, Integer scoreInvite, Boolean neutre, Tournoi tournoi, Lieu lieu, Equipe equipeHote, Equipe equipeInvite) {
        this.dateMatch = dateMatch;
        this.scoreHote = scoreHote;
        this.scoreInvite = scoreInvite;
        this.neutre = neutre;
        this.tournoi = tournoi;
        this.lieu = lieu;
        this.equipeHote = equipeHote;
        this.equipeInvite = equipeInvite;
    }

    /**
     * @return l'id d'un match
     */
    public Integer getIdMatch() {
        return idMatch;
    }

    /**
     * @return la date du match
     */
    public LocalDate getDateMatch() {
        return dateMatch;
    }

    /**
     * @param dateMatch la nouvelle date d'un match
     */
    public void setDateMatch(LocalDate dateMatch) {
        this.dateMatch = dateMatch;
    }

    /**
     * @return le score de l'équipe hote
     */
    public Integer getScoreHote() {
        return scoreHote;
    }

    /**
     * @param scoreHote le nouveau score de l'équipe hote
     */
    public void setScoreHote(Integer scoreHote) {
        this.scoreHote = scoreHote;
    }

    /**
     * @return le score de l'équipe invité
     */
    public Integer getScoreInvite() {
        return scoreInvite;
    }

    /**
     * @param scoreInvite le nouveau score de l'équipe invité
     */
    public void setScoreInvite(Integer scoreInvite) {
        this.scoreInvite = scoreInvite;
    }

    /**
     * @return si le terrain est neutre
     */
    public Boolean getNeutre() {
        return neutre;
    }

    /**
     * @param neutre le nouveau terrain si neutre
     */
    public void setNeutre(Boolean neutre) {
        this.neutre = neutre;
    }

    /**
     * @return le nom du tournoi
     */
    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Equipe getEquipeHote() {
        return equipeHote;
    }

    public void setEquipeHote(Equipe equipeHote) {
        this.equipeHote = equipeHote;
    }

    public Equipe getEquipeInvite() {
        return equipeInvite;
    }

    public void setEquipeInvite(Equipe equipeInvite) {
        this.equipeInvite = equipeInvite;
    }

    public TirBut getTirBut() {
        return tirBut;
    }

    public void setTirBut(TirBut tirBut) {
        this.tirBut = tirBut;
    }

    public Set<But> getButs() {
        return buts;
    }

    public void setButs(Set<But> buts) {
        this.buts = buts;
    }
}
