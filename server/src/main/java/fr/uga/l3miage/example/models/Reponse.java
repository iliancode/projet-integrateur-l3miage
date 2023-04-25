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

    //getters
    public long getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public boolean isEsValide() {
        return esValide;
    }

    //setters
    public void setLabel(String label) {
        this.label = label;
    }
    public void setEsValide(boolean esValide) {
        this.esValide = esValide;
    }
    public void setId(long id) {
        this.id = id;
    }
}
