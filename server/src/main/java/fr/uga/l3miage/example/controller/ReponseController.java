package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ReponseEndpoint;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import fr.uga.l3miage.example.service.ReponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReponseController implements ReponseEndpoint {

    public final ReponseService reponseService;

    @Override
    public ReponseDTO getEntityReponse(final String label) {
        return reponseService.getReponse(label);
    }

    @Override
    public void createEntityReponse(final CreateReponseRequest request) {
        reponseService.createReponse(request);
    }

    @Override
    public void updateReponseEntity(Integer id, ReponseDTO reponse) {

    }

    @Override
    public void deleteReponseEntity(final String label) {
        reponseService.deleteReponse(label);
    }

}
