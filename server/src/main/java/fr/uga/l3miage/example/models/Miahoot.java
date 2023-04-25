package fr.uga.l3miage.example.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "miahoot")
public class Miahoot {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String nom;

    @OneToMany
    private List<Question> questions;


}
