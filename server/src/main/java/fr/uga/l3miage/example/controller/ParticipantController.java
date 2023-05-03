package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ParticipantEndpoint;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import fr.uga.l3miage.example.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController implements ParticipantEndpoint {
    public final ParticipantService participantService;

    @Override
    public void createParticipantOfPartie(final Long codePartie, final CreateParticipantRequest request) {
        participantService.createParticipantByPartie(codePartie, request);
    }


    @Override
    public List<ParticipantDTO> getAllParticipantsOfPartie(final Long codePartie) {
        return participantService.getAllParticipantsByPartie(codePartie);
    }


    @Override
    public ParticipantDTO getParticipantOfPartie(final Long codePartie, final Long id) {
        return participantService.getParticipantByPartie(codePartie, id);
    }


    @Override
    public void deleteAllParticipantsOfPartie(String mail, Long codePartie) {
        participantService.deleteAllParticipantsFromPartie(mail,codePartie);
    }
}
