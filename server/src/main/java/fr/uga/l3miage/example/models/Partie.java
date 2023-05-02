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
    @GeneratedValue
    private long codePartie;
    @Column
    private String nom;


    @OneToOne
    private Miahoot miahoot;


    //getters
    public Miahoot getMiahoot() {
        return miahoot;
    }
    public long getCodePartie() {
        return codePartie;
    }

    //Setters
    public void setMiahoot(Miahoot miahoot) {
        this.miahoot = miahoot;
    }
    public void setCodePartie(long codePartie) {
        this.codePartie = codePartie;
    }
}
