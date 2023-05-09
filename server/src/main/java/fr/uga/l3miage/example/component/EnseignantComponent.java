package fr.uga.l3miage.example.component;


import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.repository.*;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnseignantComponent {

    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;


    /**
     * Component pour créer un enseignant
     * @param enseignant l'enseignant à créer
     * @throws MailAlreadyExistException si le mail existe déjà dans la base de données
     */
    public void createEnseignant(final Enseignant enseignant) throws MailAlreadyExistException {
        if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe déjà dans la base de données", enseignant.getMail()), enseignant.getMail());
        }
        enseignantRepository.save(enseignant);
    }


    /**
     * Component pour récupérer un enseignant par son uid
     * @param uid l'uid de l'enseignant
     * @return l'enseignant
     * @throws EnseignantEntityNotFoundException si l'enseignant n'a pas été trouvé
     */
    public Enseignant getEnseignantByUid(final String uid) throws EnseignantEntityNotFoundException {
        return enseignantRepository.findByUid(uid)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("L'enseignant [%s] n'a pas été trouvée", uid), uid));
    }


    /**
     * Component pour récupérer tous les enseignants
     * @return la liste des enseignants
     */
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }


    /**
     * Component pour supprimer un enseignant par son uid
     * @param uidEnseignant l'uid de l'enseignant
     * @throws EnseignantEntityNotFoundException si l'enseignant n'a pas été trouvé
     */
    public void deleteEnseignantByUid(final String uidEnseignant) throws EnseignantEntityNotFoundException {
        enseignantRepository.findByUid(uidEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", uidEnseignant), uidEnseignant));

        enseignantRepository.deleteByUid(uidEnseignant);
    }


    // OPTIONNEL
    public void updateEnseignantByUid(final String uidEnseignant, final CreateEnseignantRequest enseignant) throws EnseignantEntityNotFoundException, MailAlreadyExistException {
        Enseignant actuelEnseignant = enseignantRepository.findByUid(uidEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", uidEnseignant), uidEnseignant));

        if (!actuelEnseignant.getMail().equals(enseignant.getMail()) && enseignantRepository.findByMail(enseignant.getMail()).isPresent()) {
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe deja dans la base de données", enseignant.getMail()), enseignant.getMail());
        }

        enseignantMapper.mergeEnseignantEntity(actuelEnseignant, enseignant);
        enseignantRepository.save(actuelEnseignant);
    }

}

