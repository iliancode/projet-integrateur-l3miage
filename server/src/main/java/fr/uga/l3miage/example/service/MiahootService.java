package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.TestEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateFullMiahootRequest;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MiahootService {

    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;
    private final EnseignantMapper enseignantMapper;


    @Transactional
    public void createMiahootFromEnseignant(final String uidEnseignant, final CreateMiahootRequest createMiahootRequest) throws Exception {

        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahootFromEnseignant(uidEnseignant, newMiahoot);
    }


    public List<MiahootDTO> getAllMiahootsOfEnseignant(String uidEnseignant) throws Exception {
        return miahootMapper.toDto(miahootComponent.getAllMiahootsOfEnseignant(uidEnseignant));
    }


    public MiahootDTO getMiahootOfEnseignant(final String uidEnseignant, final Long idMiahoot) throws Exception {
        try {
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(uidEnseignant, idMiahoot);
            return miahootMapper.toDto(miahoot);
        } catch (EnseignantEntityNotFoundException e) {
            throw new Exception(String.format("Impossible de charger l'entité enseignant. Raison : [%s"));
        } catch (TestEntityNotFoundRestException e) {
            throw new TestEntityNotFoundRestException(String.format("Impossible de charger l'entité Miahoot. Raison : [%s]", e.getMessage()), "erreur", e);
        }
    }


    public void deleteMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws  Exception {
        try {
            miahootComponent.deleteMiahootOfEnseignant(idEnseignant, idMiahoot);
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité enseignant. Raison : [%s]", e.getMessage()), idEnseignant, e);
        } catch (TestEntityNotFoundRestException e) {
            throw new TestEntityNotFoundRestException(String.format("Impossible de charger l'entité Miahoot. Raison : [%s]", e.getMessage()), "erreur", e);
        }
    }

    public void createMiahootOfEnseignant(String uidEnseignant, CreateFullMiahootRequest createFullMiahootRequest) throws Exception {
        miahootComponent.createMiahootOfEnseignant(uidEnseignant, createFullMiahootRequest);
    }
}
