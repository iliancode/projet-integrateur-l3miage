package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MiahootComponent {
    private final MiahootRepository miahootRepository;
    private final MiahootMapper miahootMapper;
    private final QuestionRepository questionRepository;


    public Miahoot getMiahoot(final Long id) throws ParticipantEntityNotFoundException {
        return miahootRepository.findById(id)
                .orElseThrow(() -> new ParticipantEntityNotFoundException(String.format("Aucune entité Miahoot n'a été trouvée pour l'id [%d]", id), id));
    }

    public void createMiahoot(final Miahoot entity){
        miahootRepository.save(entity);
    }

    public void deleteMiahoot(final Long id) throws ParticipantEntityNotFoundException {
        try {
            Miahoot deleted = getMiahoot(id);
            miahootRepository.deleteById(id);
        }catch(ParticipantEntityNotFoundException e){
            throw new ParticipantEntityNotFoundException("L'entité Miahoot à supprimer n'a pas été trouvée", id);
        }
    }

    public Question getQuestion(Long idMiahoot, Long idQuestion) throws Exception {

        log.info("j'entre dans getQuestion");
        Miahoot m =   miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception("Aucune entité Miahoot n'a été trouvée pour l'id "));

        log.info("Miahoot trouvé");
        if(m.containsQuestion(idQuestion)){
            log.info("Question trouvée");
            return m.getQuestion(idQuestion);
        }else {
            throw new Exception("Aucune entité Question n'a été trouvée pour l'id ");
        }
    }

    public void deleteQuestion(Long idMiahoot, Long idQuestion) throws Exception {
        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(()  -> new Exception("Aucune entité Miahoot n'a été trouvée pour l'id "));

        if(m.containsQuestion(idQuestion)){
            m.deleteQuestion(idQuestion);
            questionRepository.deleteById(idQuestion);
            miahootRepository.save(m);
        }
    }
}
