package fr.uga.l3miage.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "partie")
@Getter
@Setter
public class Partie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long codePartie;

    @Column
    private String nom;


    @OneToOne
    private Miahoot miahoot;
}
