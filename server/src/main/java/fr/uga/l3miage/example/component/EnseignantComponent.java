package fr.uga.l3miage.example.component;

//import fr.uga.l3miage.example.mapper.EnseignantMapper;

import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.repository.*;
import fr.uga.l3miage.example.response.EnseignantDTO;
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

    private final PartieRepository partieRepository;


    public void createEnseignant(final Enseignant enseignant) throws MailAlreadyExistException {
        if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe deja dans la base de données", enseignant.getMail()), enseignant.getMail());
        }

        enseignantRepository.save(enseignant);
    }


    public Enseignant getEnseignantById(final Long idEnseignant) throws Exception {

        Enseignant enseignant = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("L'entité à supprimer n'a pas été trouvée " + idEnseignant));

        return enseignant;
    }


    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public void deleteEnseignantById(final Long idEnseignant) throws EnseignantEntityNotFoundException {
        enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), idEnseignant));

        enseignantRepository.deleteById(idEnseignant);
    }


    public void updateEnseignantById(final Long idEnseignant, final EnseignantDTO enseignant) throws EnseignantEntityNotFoundException, MailAlreadyExistException {
        Enseignant actuelEnseignant = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), idEnseignant));

        if (!actuelEnseignant.getMail().equals(enseignant.getMail()) && enseignantRepository.findByMail(enseignant.getMail()).isPresent()) {
            throw new MailAlreadyExistException(String.format("Ce mail [%s] existe deja dans la base de données", enseignant.getMail()), enseignant.getMail());
        }

        enseignantMapper.mergeEnseignantEntity(actuelEnseignant, enseignant);
        enseignantRepository.save(actuelEnseignant);
    }

    public void createQuestionInMiahoot(final Long idEnseignant, final Long idMiahoot, final Question question) throws Exception {
        log.info("enseignant component atteint");

        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        if (e.containsMiahoot(idMiahoot)) {

            questionRepository.save(question);

            e.getMiahoot(idMiahoot).getQuestions().add(question);
            enseignantRepository.save(e);
            miahootRepository.save(m);


        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }

    }


    public void createMiahootFromEnseignant(final Long idEnseignant, final Miahoot miahoot) throws Exception {
        try {
            Enseignant enseignant = enseignantRepository.findById(idEnseignant)
                    .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));
            enseignant.getMiahoots().add(miahoot);
        } catch (Exception ex) {
            throw new Exception("Impossible de créer le Miahoot et de l'ajouter. Raison :" + ex.getMessage());
        }
    }

    public List<Miahoot> getAllMiahootsOfEnseignant(Long idEnseignant) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        return e.getMiahoots();
    }

    public Miahoot getMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));
        return e.getMiahoot(idMiahoot);
    }

    public void deleteMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvée pour le mail [%s]", idMiahoot), "erreur"));

        if (e.containsMiahoot(idMiahoot)) {
            e.removeMiahoot(m);
            enseignantRepository.save(e);
            miahootRepository.delete(m);
        }
    }

    public List<Question> getAllQuestionsOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id"));

        if (e.containsMiahoot(idMiahoot)) {
            return e.getMiahoot(idMiahoot).getQuestions();
        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }

    public void addReponseToQuestionOfMiahoot(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final Reponse newReponse) throws Exception {

        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        Question q = questionRepository.findById(idQuestion)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        if (e.containsMiahoot(idMiahoot)) {

            if (m.containsQuestion(idQuestion)) {
                reponseRepository.save(newReponse);
                e.getMiahoot(idMiahoot).getQuestion(idQuestion).getReponses().add(newReponse);
                questionRepository.save(q);
            } else {
                throw new Exception("Question non contenu dans ce miahoot");
            }
        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }

    }

    public List<Reponse> getAllReponsesOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));

        if (e.containsMiahoot(idMiahoot)) {
            if (e.getMiahoot(idMiahoot).containsQuestion(idQuestion)) {

                return e.getMiahoot(idMiahoot).getQuestion(idQuestion).getReponses();
            } else {
                throw new Exception("Question non contenu dans ce miahoot");
            }

        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }

    public Reponse getReponseOfQuestionOfMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final Long idReponse) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id Enseignant"));
        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id"));
        if (e.containsMiahoot(idMiahoot)) {
            if (m.containsQuestion(idQuestion)) {
                return e.getMiahoot(idMiahoot).getQuestion(idQuestion).getReponse(idReponse);
            }else {
                throw new Exception("Question non contenu dans ce miahoot");
            }
        }else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }

    public void deleteReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour le mail [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idMiahoot), "erreur"));

        if (e.containsMiahoot(idMiahoot)) {
            if (m.containsQuestion(idQuestion)) {
                e.getMiahoot(idMiahoot).getQuestion(idQuestion).removeReponse(idReponse);
                enseignantRepository.save(e);
                miahootRepository.save(m);
            } else {
                throw new Exception("Aucune entité n'a été trouvé pour l'id");
            }
        } else {
            throw new Exception("Aucune entité n'a été trouvé pour l'id");
        }
    }

    public void addPartieToEnseignant(final Long idEnseignant, final Long idMiahoot, final Partie newPartie) throws EnseignantEntityNotFoundException, MiahootEntityNotFoundException {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité Enseignant n'a été trouvée pour l'id [%s]", idEnseignant), idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new MiahootEntityNotFoundException(String.format("Aucune entité Miahoot n'a été trouvée pour l'id [%s]", idMiahoot), idMiahoot));
        log.info("component atteint");

        if (e.containsMiahoot(idMiahoot)) {
            newPartie.setMiahoot(m);
            e.addPartie(newPartie);
            partieRepository.save(newPartie);
            enseignantRepository.save(e);
        }
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

