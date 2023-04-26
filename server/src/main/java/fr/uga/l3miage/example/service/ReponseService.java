package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;

import javax.transaction.Transactional;

public class ReponseService {

    private final ReponseComponent reponseComponent;
    private final ReponseMapper reponseMapper;

    // Recuperer le DTO réponse correspondant a la description
    public ReponseDTO getReponse(final String label) throws Exception {
        try {
            return reponseMapper.entityToDTO(reponseComponent.getReponse(label))
        } catch (Exception ex) {
            throw new Exception("On ne peut pas recuperer le DTO");
        }
    }

    //On creer l'entité réponse
    public void createReponse(final CreateReponseRequest createReponseRequest) throws Exception {
        Reponse newReponse = reponseMapper.toEntity(createReponseRequest);
        if(newReponse.getLabel().length()  != 0) {
            try {
                reponseComponent.createReponse(newReponse);
            }catch(Exception exception){
                throw new Exception("Erreur de création de l'entité réponse partie service");
            }
        }
    }

    // On supprime l'entité réponse
    @Transactional
    public void deleteReponse(final String label) throws Exception{
        try {
            reponseComponent.deleteReponse(label);
        }catch(Exception ex){
            throw new Exception("erreur de suppression de l'entité dans la partie service");
        }
    }
}
