package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.ParticipantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.ParticipantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MiahootService {

    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;

    public MiahootDTO getMiahoot(final Long id){
        try {
            return miahootMapper.toDto(miahootComponent.getMiahoot(id));
        }catch(ParticipantEntityNotFoundException ex){
            throw new ParticipantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",ex.getMessage()),id,ex);
        }
    }

    public void createMiahoot(final CreateMiahootRequest createMiahootRequest){
        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahoot(newMiahoot);
    }

    @Transactional
    public void deleteParticipant(Long id) {
        try {
            miahootComponent.deleteMiahoot(id);
        } catch (ParticipantEntityNotFoundException e) {
            throw new ParticipantEntityNotDeletedRestException(e.getMessage());
        }
    }
}
