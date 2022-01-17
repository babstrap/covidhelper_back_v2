package com.babacar.covidherlper_back_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Structures extends RepresentationModel<Structures> implements Comparable<Structures> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long struct_id;
    private String nom;
    private String adresse;
    private int contact;
    private double latitude;
    private double longitude;
    private int nbre_test;
    private int nbre_vac;
    private int nbr_test_dispo;
    private int nbr_vaccin_dispo;

    @Override
    public int compareTo(Structures o) {
        if(this.nbr_vaccin_dispo < o.nbr_vaccin_dispo || this.nbr_test_dispo < o.nbr_test_dispo)
            return 1;
        else
            return -1;
    }
}
