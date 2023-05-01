package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
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

    public MiahootDTO getMiahoot(final Long id) throws Exception {
        try {
            return miahootMapper.toDto(miahootComponent.getMiahoot(id));
        }catch(Exception ex){
            throw new Exception( "Impossible de charger l'entité. Raison" );
        }
    }

    public void createMiahoot(final CreateMiahootRequest createMiahootRequest){
        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahoot(newMiahoot);
    }

    @Transactional
    public void deleteParticipant(Long id) throws Exception {
        try {
            miahootComponent.deleteMiahoot(id);
        } catch (Exception e) {
            throw new Exception("pas pu delete le participant lol");
        }
    }
}
