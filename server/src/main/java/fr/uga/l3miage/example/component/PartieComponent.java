package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.PartieEntityNotFoundException;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.PartieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PartieComponent {
    private final PartieRepository partieRepository;
    private final EnseignantRepository enseignantRepository;
    private final MiahootRepository miahootRepository;


    public void addPartieToEnseignant(final Long idEnseignant, final Long idMiahoot, final Partie newPartie) throws EnseignantEntityNotFoundException, MiahootEntityNotFoundException {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité Enseignant n'a été trouvée pour l'id [%s]", idEnseignant), idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new MiahootEntityNotFoundException(String.format("Aucune entité Miahoot n'a été trouvée pour l'id [%s]", idMiahoot), idMiahoot));

        if (e.containsMiahoot(idMiahoot)) {
            newPartie.setMiahoot(m);
            e.addPartie(newPartie);
            partieRepository.save(newPartie);
            enseignantRepository.save(e);
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


    public Partie getPartieFromEnseignant(Long idEnseignant, Long codePartie) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));
        try{
            return e.getPartie(codePartie);
        }catch (Exception ex){
            throw new Exception("Aucune partie de cet id n'a été trouvé pour dans cet enseinant ");
        }

    }


    public void deletePartieFromEnseignant(Long idEnseignant, Long codePartie) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id Enseignant"));
        e.removePartie(codePartie);
        enseignantRepository.save(e);
    }

}
