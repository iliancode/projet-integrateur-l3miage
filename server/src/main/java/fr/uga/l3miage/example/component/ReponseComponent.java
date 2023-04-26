package fr.uga.l3miage.example.component;
import fr.uga.l3miage.example.exception.technical.*;
import fr.uga.l3miage.example.mapper.TestMapper;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.repository.ReponseRepository;
import fr.uga.l3miage.example.repository.TestRepository;
import fr.uga.l3miage.example.response.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import fr.uga.l3miage.example.config.HelloWordConfig;
import fr.uga.l3miage.example.mapper.ReponseMapper;

@Component
@RequiredArgsConstructor
public class ReponseComponent {
    private final ReponseRepository reponseRepository;
    private final ReponseMapper reponseMapper;

    //CREATION D'UNE REPONSE
    public void createReponse(final Reponse reponse) throws Exception {
        // on verifie qu'elle ne soit pas vide
        if(reponse.getLabel() != ""){
            // on verifie que la question n'existe pas déja
            if(reponseRepository.findByLabel(reponse.getLabel()).isPresent()) {
                throw new Exception(String.format("La question %s existe déjà en BD.", reponse.getLabel()));
                }
            reponseRepository.save(reponse);
            } else throw new Exception("La nouvelle réponse est vide ");

        }
    //Suppression d'une reponse
    public void deleteReponse(final String label) throws Exception{
        int deleted = reponseRepository.deleteByLabel(label);
        if(deleted > 1)
            throw new Exception("Comment c'est possible sachant quil ne peut pas avoir 2 reponses avec le meme label");
        else if(deleted == 0)
            throw new Exception("La question a supprimé n'existe pas ");
    }

    //TROUVER UNE REPONSE
    public Reponse getReponse(final String label) throws Exception{
        return reponseRepository.findByLabel((label))
                .orElseThrow(()-> new Exception("Aucune réponse n'a été trouve"));
    }

}
