package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface ReponseMapper {
    ReponseDTO entityToDTO(Reponse reponse);

    Collection<ReponseDTO> entityToDTO(Iterable<Reponse> reponses);

    Reponse dtoToEntity(ReponseDTO reponse);

    Reponse toEntity(CreateReponseRequest request);
    Collection<Reponse> dtoToEntity(Iterable<ReponseDTO> reponses);
}
