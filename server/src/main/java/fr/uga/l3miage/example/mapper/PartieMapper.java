package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreatePartieRequest;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper
public interface PartieMapper {


    Partie toDto(Optional<Partie> testEntity);
    Partie toEntity(CreatePartieRequest request);



}
