package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MiahootMapper {

    Miahoot toEntity(CreateMiahootRequest request);
    // @Mapping(source = "miahoots",target = "miahootsDTO",qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    MiahootDTO toDto(Miahoot testEntity);

    QuestionDTO map(Question question);
}
