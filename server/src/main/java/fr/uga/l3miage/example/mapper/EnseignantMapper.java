package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.PartieDTO;
import org.mapstruct.Mapper;

@Mapper (uses = { MiahootMapper.class, PartieMapper.class})
public interface EnseignantMapper {


    Enseignant toEntity(CreateEnseignantRequest request);
    // @Mapping(source = "miahoots",target = "miahootsDTO",qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    EnseignantDTO toDto(Enseignant testEntity);

    PartieDTO map(Partie partie);

}
