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
    private boolean estValide;

    //getters
    public long getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public boolean isEstValide() {
        return estValide;
    }

    //setters
    public void setLabel(String label) {
        this.label = label;
    }
    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }
    public void setId(long id) {
        this.id = id;
    }
}
