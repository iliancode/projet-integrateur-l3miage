package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootEndpoint;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.service.MiahootService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MiahootController implements MiahootEndpoint {

    public final MiahootService miahootService;


    @Override
    public MiahootDTO getEntityMiahoot(final Long id) {
        return miahootService.getMiahoot(id);
    }

    @Override
    public void createEntityMiahoot(CreateMiahootRequest request) {
        miahootService.createMiahoot(request);
    }

    @Override
    public void deleteMiahootEntity(final Long id) {
         miahootService.deleteParticipant(id);
    }
}
