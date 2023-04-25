package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.response.ReponseDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ReponseMapper {
    ReponseDTO entityToDTO(Reponse reponse);

    Collection<ReponseDTO> entityToDTO(Iterable<Reponse> reponses);

    Reponse dtoToEntity(ReponseDTO reponse);

    Collection<Reponse> dtoToEntity(Iterable<ReponseDTO> reponses);
}
