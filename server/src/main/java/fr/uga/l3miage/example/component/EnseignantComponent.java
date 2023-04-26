package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnseignantComponent {
    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;

    //test de creation d'un enseignant
    public void createEnseignant(final Enseignant enseignant) throws Exception{
        if(enseignant.getMail().isEmpty() == false){
            if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
                throw new Exception("Mail deja existant dans la base de données");
            }else{
                if(enseignantRepository.findByPseudo(enseignant.getPseudo()).isPresent()){
                    throw new Exception("Pseudo deja existant dans la base de données");
                }
                else{
                    if(enseignant.getMdp().isEmpty()){
                        throw new Exception("Le mot de passe ne peut pas etre vide");
                    }else{
                        enseignantRepository.save(enseignant);
                    }
                }
            }
        }else throw new Exception("Mail non renseigné");
    }
}
