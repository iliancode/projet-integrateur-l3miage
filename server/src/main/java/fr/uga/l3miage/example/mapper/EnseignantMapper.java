package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.*;
import lombok.NonNull;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper (uses = {MiahootMapper.class, PartieMapper.class})
public interface EnseignantMapper {

    Enseignant toEntity(CreateEnseignantRequest request);

    EnseignantDTO toDto(Enseignant enseignant);
    List<EnseignantDTO> toDto(List<Enseignant> enseignants);

    void mergeEnseignantEntity(@MappingTarget @NonNull Enseignant ancientEnseignant, CreateEnseignantRequest request);

}
