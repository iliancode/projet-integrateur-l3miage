package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnseignantComponent {
    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;

    public void deleteEnseignant(final long id) {}


}
