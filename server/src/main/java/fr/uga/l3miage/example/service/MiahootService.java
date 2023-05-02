package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MiahootService {

    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;
    private final QuestionMapper questionMapper;

    public MiahootDTO getMiahoot(final Long id) throws Exception {
        try {
            return miahootMapper.toDto(miahootComponent.getMiahoot(id));
        }catch(Exception ex){
            throw new Exception( "Impossible de charger l'entité. Raison" );
        }
    }

    public void createMiahoot(final CreateMiahootRequest createMiahootRequest){
        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahoot(newMiahoot);
    }

    @Transactional
    public void deleteParticipant(Long id) throws Exception {
        try {
            miahootComponent.deleteMiahoot(id);
        } catch (Exception e) {
            throw new Exception("pas pu delete le participant lol");
        }
    }

    public QuestionDTO getQuestion(Long idMiahoot, Long idQuestion) throws Exception {
        try {
            log.info("entre dans service getQuestion");
            return questionMapper.toDto(miahootComponent.getQuestion(idMiahoot, idQuestion));
        } catch (Exception e) {
            throw new Exception("Impossible de charger l'entitée ");
        }
    }

    @Transactional
    public void deleteQuestion(Long idMiahoot, Long idQuestion) throws Exception {
        try {
            miahootComponent.deleteQuestion(idMiahoot, idQuestion);
        } catch (Exception e) {
            throw new Exception("Impossible de supprimer l'entitée ");
        }
    }
}