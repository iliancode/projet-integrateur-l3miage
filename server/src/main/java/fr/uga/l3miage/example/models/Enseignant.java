package fr.uga.l3miage.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enseignant")
@Getter
@Setter
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String mail;

    @Column
    private String mdp;

    @Column
    private String pseudo;

    @OneToMany
    private List<Miahoot> miahoots;

    @OneToMany
    private List<Partie> parties;

}