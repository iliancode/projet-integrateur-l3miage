package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.MiahootEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.TestEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
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
    private final EnseignantComponent enseignantComponent;
    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;


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


    public void deleteMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) {
        try {
            Enseignant enseignant = enseignantComponent.getEnseignantById(idEnseignant);
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(idEnseignant, idMiahoot);
            miahootComponent.deleteMiahootOfEnseignant(enseignant, miahoot);
        } catch (EnseignantEntityNotFoundException | MiahootEntityNotFoundException e) {
            throw new MiahootEntityNotFoundRestException(e.getMessage(), idMiahoot, e);
        }
    }

}
