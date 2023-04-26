package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class EnseignantService {

    private final EnseignantComponent enseignantComponent;
    private final EnseignantMapper enseignantMapper;

    public void createEnseignant( final CreateEnseignantRequest createEnseignantRequest) throws Exception {
        Enseignant newEnseignant = enseignantMapper.toEntity(createEnseignantRequest);

        if(newEnseignant.getMail().length() !=0){
            if(newEnseignant.getPseudo().length() != 0){
                if(newEnseignant.getMdp().length() >9){
                    try{
                        enseignantComponent.createEnseignant(newEnseignant);
                    } catch (Exception e) {
                        throw new Exception("erreur a la creation de l'enseignant");
                    }
                }else throw new Exception("mdp size <=9 ");
            }else throw new Exception("pseudo size = 0 ");
        }else throw new Exception("mail size = 0 ");
    }

    @Transactional
    public void deleteEnseignantByMail(String mail) throws Exception {
        try {
            enseignantComponent.deleteEnseignantByMail(mail);
        } catch ( Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Transactional
    public void deleteEnseignantById(long id) throws Exception {
        try {
            enseignantComponent.deleteEnseignantById(id);
        } catch ( Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


}
