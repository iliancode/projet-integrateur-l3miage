package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.PartieDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper (uses = { MiahootMapper.class, PartieMapper.class})
public interface EnseignantMapper {


    Enseignant toEntity(CreateEnseignantRequest request);
    // @Mapping(source = "miahoots",target = "miahootsDTO",qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    EnseignantDTO toDto(Enseignant testEntity);

    PartieDTO map(Partie partie);

    void mergeEnseignantEntity(@MappingTarget @NonNull Enseignant ancientEnseignant, EnseignantDTO newEnseignant);
    //Miahoot map(MiahootDTO miahootDTO);

    List<EnseignantDTO> toDto(List<Enseignant> testEntities);
   // Miahoot map(MiahootDTO miahootDTO);


}
