package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.entityNotFoundException.PartieEntityNotFoundException;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.repository.PartieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PartieComponent {
    private final PartieRepository partieRepository;


    public void createPartie(final Partie partie)throws Exception{
        if(!Objects.isNull(partie.getCodePartie())){
            if(!Objects.isNull(partie.getMiahoot())){
                partieRepository.save(partie);
            }else{
                throw new Exception("La partie ne contient pas de miahoot");
            }
        }else{
            throw new Exception("le code de la partie est null");
        }
    }

    /**
     * @param codePartie de l'entité Partie à récupérer
     * @return une {@link Partie} correspondant à description donnée
     * @throws PartieEntityNotFoundException si aucune entité Partie n'est trouvée
     */
    public Partie getPartie(final Long codePartie) throws PartieEntityNotFoundException {
        return partieRepository.findByCodePartie(codePartie)
                .orElseThrow(() -> new PartieEntityNotFoundException(String.format("Aucune entité Partie n'a été trouvée pour le codePartie [%d]", codePartie), codePartie));
    }

    public void deletePartieById(final long id)throws Exception{
        int deleted = partieRepository.deleteById(id);
        if (deleted > 1)
            throw new Exception("Plusieurs entités partie ont le même id alors que c'est impossible niveau métier !!");
        else if (deleted == 0)
            throw new Exception("L'entité à supprimer n'a pas été trouvée " +  id);

    }
}
