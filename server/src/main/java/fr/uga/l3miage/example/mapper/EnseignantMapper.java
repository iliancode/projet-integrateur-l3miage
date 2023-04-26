package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface EnseignantMapper {
    EnseignantDTO entityToDTO(Enseignant enseignant);

    Collection<EnseignantDTO> entityToDTO(Iterable<Enseignant> enseignants);

    Enseignant dtoToEntity(EnseignantDTO enseignant);

    Collection<Enseignant> dtoToEntity(Iterable<EnseignantDTO> enseignants);


    @Mapping(target = "testInt", source = ".", qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    @Mapping(target = "fieldMapping", source = "fieldNotMappingAutomatically")
    Enseignant toEntity(CreateEnseignantRequest request);
}
