package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
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


    public void createEnseignant(final Enseignant enseignant) throws MailAlreadyExistException {
        if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe déjà dans la base de données", enseignant.getMail()), enseignant.getMail());
        }

        enseignantRepository.save(enseignant);
    }


    public Enseignant getEnseignantById(final Long idEnseignant) throws EnseignantEntityNotFoundException {
        return enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("L'enseignant [%s] n'a pas été trouvée", idEnseignant), idEnseignant));
    }


    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }


    public void deleteEnseignantById(final Long idEnseignant) throws EnseignantEntityNotFoundException {
        enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), idEnseignant));

        enseignantRepository.deleteById(idEnseignant);
    }


    public void updateEnseignantById(final Long idEnseignant, final CreateEnseignantRequest enseignant) throws EnseignantEntityNotFoundException, MailAlreadyExistException {
        Enseignant actuelEnseignant = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), idEnseignant));

        if (!actuelEnseignant.getMail().equals(enseignant.getMail()) && enseignantRepository.findByMail(enseignant.getMail()).isPresent()) {
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe deja dans la base de données", enseignant.getMail()), enseignant.getMail());
        }

        enseignantMapper.mergeEnseignantEntity(actuelEnseignant, enseignant);
        enseignantRepository.save(actuelEnseignant);
    }

}

