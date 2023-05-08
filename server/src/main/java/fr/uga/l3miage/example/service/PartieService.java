package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.PartieComponent;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.MiahootEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.PartieEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.PartieEntityNotFoundException;
import fr.uga.l3miage.example.mapper.PartieMapper;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreatePartieRequest;
import fr.uga.l3miage.example.response.PartieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PartieService {

    private  final PartieComponent partieComponent;
    private final PartieMapper partieMapper;


    public void addPartieToEnseignant(String uidEnseignant, Long idMiahoot, CreatePartieRequest createPartieRequest) {
        try {
            Partie newPartie = partieMapper.toEntity(createPartieRequest);
            partieComponent.addPartieToEnseignant(uidEnseignant, idMiahoot, newPartie);
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),uidEnseignant,e);
        } catch (MiahootEntityNotFoundException e) {
            throw new MiahootEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),idMiahoot,e);
        }
    }

    public PartieDTO getPartieFromEnseignant(String uidEnseignant, Long codePartie) throws Exception {
        return partieMapper.toDto(partieComponent.getPartieFromEnseignant(uidEnseignant, codePartie));
    }

    @Transactional
    public void deletePartieFromEnseignant(String uidEnseignant, Long codePartie) throws Exception {
        try{
            partieComponent.deletePartieFromEnseignant(uidEnseignant, codePartie);
        }catch (Exception ex) {
            throw new Exception("Erreur lors de la suppression de la partie");
        }
    }

}
