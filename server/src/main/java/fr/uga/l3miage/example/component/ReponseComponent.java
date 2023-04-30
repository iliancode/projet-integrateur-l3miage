package fr.uga.l3miage.example.component;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ReponseEntityNotFoundException;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.repository.ReponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import fr.uga.l3miage.example.mapper.ReponseMapper;

@Component
@RequiredArgsConstructor
public class ReponseComponent {
    private final ReponseRepository reponseRepository;
    private final ReponseMapper reponseMapper;

    //CREATION D'UNE REPONSE
    public void createReponse(final Reponse reponse) /**throws Exception **/ {

        reponseRepository.save(reponse);
        /**
        // on verifie qu'elle ne soit pas vide
        if(reponse.getLabel() != ""){
            // on verifie que la question n'existe pas déja
            if(reponseRepository.findByLabel(reponse.getLabel()).isPresent()) {
                throw new Exception(String.format("La question %s existe déjà en BD.", reponse.getLabel()));
                }
            reponseRepository.save(reponse);
            } else throw new Exception("La nouvelle réponse est vide "); **/

        }
    //Suppression d'une reponse
    public void deleteReponse(final String label) throws ReponseEntityNotFoundException {
        try{
            Reponse deleted = getReponse(label);
            reponseRepository.deleteByLabel(label);
        }catch(ReponseEntityNotFoundException e){
            throw new ReponseEntityNotFoundException("Entite reponse non trouve",label);
        }
    }

    //TROUVER UNE REPONSE
    public Reponse getReponse(final String label) throws ReponseEntityNotFoundException{
        return reponseRepository.findByLabel((label))
                .orElseThrow(()-> new ReponseEntityNotFoundRestException(String.format("Aucune entité Reponse n'a été trouvée pour le label [%s]", label), label));
    }

}
