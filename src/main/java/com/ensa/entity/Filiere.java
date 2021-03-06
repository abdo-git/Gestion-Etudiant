package com.ensa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
public class Filiere implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "label is required")
    @Size(min = 4, message = "label of filiere must be greater than 4 char")
    @Column(name = "libelle_filiere",nullable = false,length = 50)
    private String libelle;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "filiere",targetEntity = Niveau.class)
    private List<Niveau> niveauList;

}
