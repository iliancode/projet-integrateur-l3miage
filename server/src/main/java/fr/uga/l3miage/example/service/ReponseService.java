package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.exception.rest.ParticipantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.ReponseEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.ReponseEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReponseService {

    private final ReponseComponent reponseComponent;
    private final ReponseMapper reponseMapper;

    // Recuperer le DTO réponse correspondant a la description
    public ReponseDTO getReponse(final String label){
        try {
            return reponseMapper.toDto(reponseComponent.getReponse(label));
        } catch (ReponseEntityNotFoundException e) {
            throw new ReponseEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),label,e);
        }
    }

    //On creer l'entité réponse
    public void createReponse(final CreateReponseRequest createReponseRequest) {
        Reponse newReponse = reponseMapper.toEntity(createReponseRequest);
        reponseComponent.createReponse(newReponse);
        /**
        if(newReponse.getLabel().length()  != 0) {
            try {
                reponseComponent.createReponse(newReponse);
            }catch(Exception exception){
                throw new Exception("Erreur de création de l'entité réponse partie service");
            }
        } **/
    }

    // On supprime l'entité réponse
    @Transactional
    public void deleteReponse(final String label){
        try {
            reponseComponent.deleteReponse(label);
        }catch(ReponseEntityNotFoundException e){
            throw new ReponseEntityNotDeletedRestException(e.getMessage());
        }
    }
}
