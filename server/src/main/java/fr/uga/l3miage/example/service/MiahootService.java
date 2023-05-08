package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.MiahootEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.TestEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MiahootService {

    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;
    private final EnseignantMapper enseignantMapper;


    @Transactional
    public void createMiahootFromEnseignant(final Long idEnseignant, final CreateMiahootRequest createMiahootRequest) throws Exception {

        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahootFromEnseignant(idEnseignant, newMiahoot);
    }


    public List<MiahootDTO> getAllMiahootsOfEnseignant(Long idEnseignant) throws Exception {
        return miahootMapper.toDto(miahootComponent.getAllMiahootsOfEnseignant(idEnseignant));
    }


    public MiahootDTO getMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot) {
        try {
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(idEnseignant, idMiahoot);
            return miahootMapper.toDto(miahoot);
        } catch (MiahootEntityNotFoundException e) {
            throw new MiahootEntityNotFoundRestException(e.getMessage(), idMiahoot, e);
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

}
