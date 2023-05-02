package fr.uga.l3miage.example.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reponse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
