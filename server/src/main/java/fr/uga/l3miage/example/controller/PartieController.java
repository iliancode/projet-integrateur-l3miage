package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.PartieEndpoint;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.service.ParticipantService;
import fr.uga.l3miage.example.service.PartieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PartieController implements PartieEndpoint {
    public final PartieService partieService;
    public final ParticipantService participantService;

    @Override
    public void createParticipantOfPartie(final Long codePartie, final CreateParticipantRequest request) {
        participantService.createParticipantByPartie(codePartie, request);
    }

}