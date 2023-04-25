package fr.uga.l3miage.example.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "label")
    private String label;

    @OneToMany(mappedBy = "question")
    private List<Reponse> reponses;


    // Getters
    public String getLabel() {
        return label;
    }
    public long getId() {
        return id;
    }
    public List<Reponse> getReponses() {
        return reponses;
    }

    // Setters
    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setId(long id) {
        this.id = id;
    }
}
