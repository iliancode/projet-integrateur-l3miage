package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.PartieComponent;
import fr.uga.l3miage.example.mapper.PartieMapper;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreatePartieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PartieService {

    private  final PartieComponent partieComponent;
    private final PartieMapper partieMapper;

    public void createPartie(final CreatePartieRequest createPartieRequest) throws Exception{
        Partie newPartie = partieMapper.toPartie(createPartieRequest);

        if(newPartie.getCodePartie()> 0){
            if(!Objects.isNull(newPartie.getMiahoot())){
                partieComponent.createPartie(newPartie);
            }else throw new Exception("miahoot null -> creation de partie impossible");

        }else throw new Exception("code partie <0, creation de partie impossible");
    }

    public <Optional> Partie getPartieById(final Long id) throws Exception{
        try{
            return partieMapper.toDto(partieComponent.getPartieById(id) );
        }catch (Exception e){
            throw new Exception("aucune partie trouvée avec cet id");
        }
    }


    @Transactional
    public void deletePartieById(long id) throws Exception {
        try {
            partieComponent.deletePartieById(id);
        } catch ( Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
