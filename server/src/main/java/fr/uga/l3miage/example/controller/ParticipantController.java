package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ParticipantEndpoint;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import fr.uga.l3miage.example.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipantController implements ParticipantEndpoint {
    public final ParticipantService participantService;

    @Override
    public ParticipantDTO getEntityParticipant(final Long id) {
        return participantService.getParticipant(id);
    }

    @Override
    public void createEntityParticipant(final CreateParticipantRequest request) {
        participantService.createParticipant(request);
    }

    @Override
    public void deleteParticipantEntity(final Long id) {
        participantService.deleteParticipant(id);
    }


}
