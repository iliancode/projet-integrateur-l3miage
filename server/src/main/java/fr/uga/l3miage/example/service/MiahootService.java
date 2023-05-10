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
    private final EnseignantComponent enseignantComponent;
    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;


    @Transactional
    public void createMiahootFromEnseignant(final String uidEnseignant, final CreateMiahootRequest createMiahootRequest) throws Exception {
        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahootFromEnseignant(uidEnseignant, newMiahoot);
    }


    public List<MiahootDTO> getAllMiahootsOfEnseignant(String uidEnseignant) throws Exception {
        return miahootMapper.toDto(miahootComponent.getAllMiahootsOfEnseignant(uidEnseignant));
    }


    public MiahootDTO getMiahootOfEnseignant(final String uidEnseignant, final Long idMiahoot) {
        try {
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(uidEnseignant, idMiahoot);
            return miahootMapper.toDto(miahoot);
        } catch (MiahootEntityNotFoundException e) {
            throw new MiahootEntityNotFoundRestException(e.getMessage(), idMiahoot, e);
        }
    }


    public void deleteMiahootOfEnseignant(String uidEnseignant, Long idMiahoot) {
        try {
            Enseignant enseignant = enseignantComponent.getEnseignantByUid(uidEnseignant);
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(uidEnseignant, idMiahoot);
            miahootComponent.deleteMiahootOfEnseignant(enseignant, miahoot);
        } catch (EnseignantEntityNotFoundException | MiahootEntityNotFoundException e) {
            throw new MiahootEntityNotFoundRestException(e.getMessage(), idMiahoot, e);
        }
    }

    public void createMiahootOfEnseignant(final String uidEnseignant, final CreateFullMiahootRequest createFullMiahootRequest) {
        try {
            Miahoot newMiahoot = miahootMapper.toEntity(createFullMiahootRequest);
            miahootComponent.createMiahootOfEnseignant(uidEnseignant, newMiahoot);
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Le miahoot n'a pas pu être créé car l'enseignant [%s] n'existe pas", uidEnseignant), uidEnseignant, e);
        }
    }


    public void updateFullMiahootOfEnseignant(final String uidEnseignant, final Long idMiahoot, final MiahootDTO request) {
        try {
            miahootComponent.updateFullMiahootOfEnseignant(uidEnseignant, idMiahoot, request);
        } catch (EnseignantEntityNotFoundException | MiahootEntityNotFoundException e) {
            throw new MiahootEntityNotFoundRestException(e.getMessage(), idMiahoot, e);
        }
    }
}
