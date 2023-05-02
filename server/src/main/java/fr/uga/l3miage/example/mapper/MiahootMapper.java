package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = QuestionMapper.class)
public interface MiahootMapper {

    Miahoot toEntity(CreateMiahootRequest request);
    // @Mapping(source = "miahoots",target = "miahootsDTO",qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    MiahootDTO toDto(Miahoot testEntity);

    //QuestionDTO map(Question question);


    Miahoot toEntity(MiahootDTO miahootDTO);

    void mergeMiahootEntity(@MappingTarget @NonNull Miahoot ancienMiahoot, MiahootDTO newMiahoot);

}
