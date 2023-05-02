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

@Mapper (uses = { MiahootMapper.class, PartieMapper.class, QuestionMapper.class, ReponseMapper.class, PartieMapper.class})
public interface EnseignantMapper {


    Enseignant toEntity(CreateEnseignantRequest request);
    EnseignantDTO toDto(Enseignant testEntity);

    //PartieDTO map(Partie partie);

    void mergeEnseignantEntity(@MappingTarget @NonNull Enseignant ancientEnseignant, EnseignantDTO newEnseignant);
    //Miahoot map(MiahootDTO miahootDTO);

    List<EnseignantDTO> toDto(List<Enseignant> testEntities);
   // Miahoot map(MiahootDTO miahootDTO);

    List<MiahootDTO> toDtoMiahoot(List<Miahoot> testEntities);

    List<QuestionDTO> toDtoQuestion(List<Question> testEntities);

    @IterableMapping(qualifiedByName = "useThis")
    List<ReponseDTO> toDtoReponse(List<Reponse> testEntities);

    @Named(value = "useThis")
    ReponseDTO map(Reponse reponse);

}
