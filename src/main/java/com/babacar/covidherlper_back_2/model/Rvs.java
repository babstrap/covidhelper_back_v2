package com.babacar.covidherlper_back_2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Rvs extends RepresentationModel<Rvs> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rv_id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date_rv;
    private int resultat;
    private boolean objet;
    private int etat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "struct_id")
    private Structures structures;

}
