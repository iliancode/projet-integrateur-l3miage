package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnseignantService {

    private final EnseignantComponent enseignantComponent;
    private final EnseignantMapper enseignantMapper;

    public void createEnseignant( final CreateEnseignantRequest createEnseignantRequest){
        Enseignant newEnseignant = enseignantMapper.dtoToEntity(createEnseignantRequest);

    }

}
