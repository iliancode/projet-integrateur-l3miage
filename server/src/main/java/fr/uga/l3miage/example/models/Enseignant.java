package fr.uga.l3miage.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enseignant")
public class Enseignant {

    @Id
    private String uid;

    @Column
    private String mail;

    @Column
    private String mdp;

    @Column
    private String pseudo;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Miahoot> miahoots;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Partie> parties;



    //search in miahoots if there is a miahoot with the same id
    public boolean containsMiahoot(long id){
        for(Miahoot m : miahoots){
            if(m.getId() == id){
                return true;
            }
        }
        return false;
    }

    //get the miahoot with the same id
    public Miahoot getMiahoot(long id){
        for(Miahoot m : miahoots){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }

    public void removeMiahoot(Miahoot m) {
        miahoots.remove(m);
    }

    public void addPartie(Partie newPartie) {
        this.parties.add(newPartie);
    }

    public Partie getPartie(Long codePartie) {
        for(Partie p : parties){
            if(p.getCodePartie() == codePartie){
                return p;
            }
        }
        return null;
    }

    public void removePartie(Long codePartie) {
        for(Partie p : parties){
            if(p.getCodePartie() == codePartie){
                parties.remove(p);
                return;
            }
        }
    }
}