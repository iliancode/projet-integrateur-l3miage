package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.response.QuestionDTO;
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
    private final QuestionMapper questionMapper;

    public Miahoot getMiahoot(final Long id) throws Exception {
        return miahootRepository.findById(id)
                .orElseThrow(() -> new Exception("Aucune entité Miahoot n'a été trouvée pour l'id "));
    }

    public void createMiahoot(final Miahoot entity){
        miahootRepository.save(entity);
    }

    public void deleteMiahoot(final Long id) throws Exception {
        try {
            Miahoot deleted = getMiahoot(id);
            miahootRepository.deleteById(id);
        }catch(Exception e){
            throw new Exception("L'entité Miahoot à supprimer n'a pas été trouvée");
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

    public void updateQuestion(Long idMiahoot, Long idQuestion, QuestionDTO questionDTO) {
        Miahoot m = miahootRepository.findById(idMiahoot).get();
        if(m.containsQuestion(idQuestion)){
            Question q = m.getQuestion(idQuestion);
            questionMapper.mergeQuestionEntity(q, questionDTO);
            questionRepository.save(q);
        }
    }
}
