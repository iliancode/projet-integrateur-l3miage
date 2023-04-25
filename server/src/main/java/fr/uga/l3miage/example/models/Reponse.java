package fr.uga.l3miage.example.models;
import javax.persistence.*;

@Entity
@Table(name = "reponse")
public class Reponse {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String label;

    @Column
    private boolean esValide;

    @ManyToOne
    private Question question;
}
