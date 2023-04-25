package fr.uga.l3miage.example.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String pseudo;

    @OneToOne
    private Partie partie;
}
