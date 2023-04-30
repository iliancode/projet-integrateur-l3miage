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
@Table(name = "miahoot")
public class Miahoot {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nom;

    @OneToMany
    private List<Question> questions;

    public void addQuestion(Question question){
        this.questions.add(question);
    }
}
