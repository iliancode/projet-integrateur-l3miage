package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.response.PartieDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface PartieMapper {
    PartieDTO entityToDTO(Partie partie);

    Collection<PartieDTO> entityToDTO(Iterable<Partie> parties);

    Partie dtoToEntity(PartieDTO partie);

    Collection<Partie> dtoToEntity(Iterable<PartieDTO> parties);
}
