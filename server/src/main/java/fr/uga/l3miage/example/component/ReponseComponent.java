package fr.uga.l3miage.example.component;
import fr.uga.l3miage.example.exception.technical.*;
import fr.uga.l3miage.example.mapper.TestMapper;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.repository.ReponseRepository;
import fr.uga.l3miage.example.repository.TestRepository;
import fr.uga.l3miage.example.response.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import fr.uga.l3miage.example.config.HelloWordConfig;
import fr.uga.l3miage.example.mapper.ReponseMapper;

@Component
@RequiredArgsConstructor
public class ReponseComponent {
    private final ReponseRepository reponseRepository;
    private final ReponseMapper reponseMapper;

    public void createReponse(final Reponse reponse) throws DescriptionAlreadyExistException {

    }
}
