package fr.uga.l3miage.example.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partie")
public class Partie {

    @Id
    @GeneratedValue
    private long codePartie;

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
