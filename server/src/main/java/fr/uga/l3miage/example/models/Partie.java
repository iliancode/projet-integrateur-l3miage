package fr.uga.l3miage.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partie")
@Getter
@Setter
public class Partie {

    @Id
    @GeneratedValue
    private long codePartie;

    @Column
    private String nom;


    @OneToOne
    private Miahoot miahoot;
}
