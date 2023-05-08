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
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String label;

    @OneToMany
    private List<Reponse> reponses;




    public void addReponse(Reponse reponse) {
        this.reponses.add(reponse);
    }

   public Reponse getReponse(long id) {
        for (Reponse reponse : this.reponses) {
            if (reponse.getId() == id) {
                return reponse;
            }
        }
        return null;
    }

    public void removeReponse(Long idReponse) {
        for (Reponse reponse : this.reponses) {
            if (reponse.getId() == idReponse) {
                this.reponses.remove(reponse);
            }
        }
    }
}
