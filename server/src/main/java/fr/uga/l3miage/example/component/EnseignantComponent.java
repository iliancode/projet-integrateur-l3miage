package fr.uga.l3miage.example.component;


import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
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
    private final MiahootRepository miahootRepository;
    private final QuestionRepository questionRepository;
    private final ReponseRepository reponseRepository;

    private final QuestionMapper questionMapper;
    private final PartieRepository partieRepository;


    public void createEnseignant(final Enseignant enseignant) throws MailAlreadyExistException {
        if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe déjà dans la base de données", enseignant.getMail()), enseignant.getMail());
        }

        enseignantRepository.save(enseignant);
    }


    public Enseignant getEnseignantByUid(final String uid) throws EnseignantEntityNotFoundException {
        return enseignantRepository.findByUid(uid)
                .orElseThrow(() -> new EnseignantEntityNotFoundException("L'entité à supprimer n'a pas été trouvée", uid));
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

