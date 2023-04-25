package fr.uga.l3miage.example.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public class Enseignant {

    @Id
    @GeneratedValue
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


    //getters

    public List<Partie> getParties() {
        return parties;
    }

    public List<Miahoot> getMiahoots() {
        return miahoots;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public String getMail() {
        return mail;
    }

    public long getId() {
        return id;
    }


    //Setters

    public void setParties(List<Partie> parties) {
        this.parties = parties;
    }


    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(long id) {
        this.id = id;
    }
}
