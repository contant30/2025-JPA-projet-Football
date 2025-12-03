package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe qui représente un but lors d'un match de foot
 *
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

}
