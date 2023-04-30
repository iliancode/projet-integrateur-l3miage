package fr.uga.l3miage.example.component;

//import fr.uga.l3miage.example.mapper.EnseignantMapper;

import fr.uga.l3miage.example.exception.technical.DescriptionAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.IsNotTestException;
import fr.uga.l3miage.example.exception.technical.MultipleEntityHaveSameDescriptionException;
import fr.uga.l3miage.example.exception.technical.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
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


    //test de creation d'un enseignant
    public void createEnseignant(final Enseignant enseignant) throws Exception{
      /* if(enseignant.getMail().isEmpty() == false){
            if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
                throw new Exception("Mail deja existant dans la base de données");
            }else{
                if(enseignantRepository.findByPseudo(enseignant.getPseudo()).isPresent()){
                    throw new Exception("Pseudo deja existant dans la base de données");
                }
                else{
                    if(enseignant.getMdp().isEmpty()){
                        throw new Exception("Le mot de passe ne peut pas etre vide");
                    }else{*/
        log.info("ICI : enseignant component : " + enseignant);
                        enseignantRepository.save(enseignant);
                  /*  }
                }
            }
        }else throw new Exception("Mail non renseigné");*/
    }

    public void deleteEnseignantById(final long id) throws Exception {
        int deleted = enseignantRepository.deleteById(id);
        if (deleted > 1)
            throw new Exception("Plusieurs entités ont le même mail alors que c'est impossible niveau métier !!");
        else if (deleted == 0)
            throw new Exception("L'entité à supprimer n'a pas été trouvée " +  id);

    }

    public Enseignant getEnseignantByMail(final String mail) throws Exception {

        return enseignantRepository.findByMail(mail)
                .orElseThrow(() -> new Exception("L'entité à supprimer n'a pas été trouvée " + mail));
    }

    //get all enseignants
    public List<Enseignant> getAllEnseignants() throws Exception {
        return enseignantRepository.findAll();
    }

    public void deleteEnseignantByMail(final String mail) throws MultipleEntityHaveSameDescriptionException, TestEntityNotFoundException {
        int deleted = enseignantRepository.deleteByMail(mail);
        log.info("deleted : " + deleted);
        if (deleted > 1)
            throw new MultipleEntityHaveSameDescriptionException("Plusieurs entités ont le même mail alors que c'est impossible niveau métier !!");
        else if (deleted == 0)
            throw new TestEntityNotFoundException("L'entité à supprimer n'a pas été trouvée " ,  mail);

    }

    public void updateEnseignantByMail(final String lastMail , final EnseignantDTO enseignant) throws TestEntityNotFoundException, IsNotTestException, DescriptionAlreadyExistException{

        if(!lastMail.equals(enseignant.getMail()) && enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new DescriptionAlreadyExistException(String.format("Ce mail existe deja dans la base de données"), enseignant.getMail());
        }

        Enseignant actuelEnseignant = enseignantRepository.findByMail(lastMail)
                .orElseThrow(() -> new TestEntityNotFoundException( String.format("Aucune entité n'a été trouvé pour le mail [%s]", lastMail), lastMail));

        enseignantMapper.mergeEnseignantEntity(actuelEnseignant,enseignant);
        enseignantRepository.save(actuelEnseignant);
    }

    public void createQuestionInMiahoot(final String mail, final Long idMiahoot,  final Question question) throws Exception {
        log.info("enseignant component atteint");

        Enseignant e = enseignantRepository.findByMail(mail)
                .orElseThrow(() -> new TestEntityNotFoundException( String.format("Aucune entité n'a été trouvé pour le mail [%s]", mail), mail));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception( "Aucune entité n'a été trouvé pour l'id "));

        if(e.containsMiahoot(idMiahoot)){

            questionRepository.save(question);

            e.getMiahoot(idMiahoot).getQuestions().add(question);
            enseignantRepository.save(e);
            miahootRepository.save(m);


        }else{
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }

    }


    public void createMiahootFromEnseignant(final String mail, final Miahoot miahoot) throws Exception {
        try {
            Enseignant enseignant = enseignantRepository.findByMail(mail)
                    .orElseThrow(() -> new TestEntityNotFoundException( String.format("Aucune entité n'a été trouvé pour le mail [%s]", mail), mail));
            enseignant.getMiahoots().add(miahoot);
        } catch (Exception ex) {
            throw new Exception("Impossible de créer le Miahoot et de l'ajouter. Raison :" + ex.getMessage());
        }
    }

}
