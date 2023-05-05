package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreatePartieRequest;
import fr.uga.l3miage.example.response.PartieDTO;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(uses = MiahootMapper.class)
public interface PartieMapper {


    PartieDTO toDto(Partie testEntity);
    Partie toEntity(CreatePartieRequest request);
}
