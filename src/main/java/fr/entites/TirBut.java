package fr.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe qui représente une séance de tir au but lors match de foot
 *
 */

@Entity
@Table(name = "tir_but")

public class TirBut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "equipe_commence_tir")
    private String equipeCommenceTir;

    @Column(name = "equipe_gagante_tir")
    private String equipeGagnanteTir;




}
