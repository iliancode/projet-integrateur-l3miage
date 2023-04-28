package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(uses = QuestionMapper.class)
public interface MiahootMapper {

    MiahootDTO toDto(Miahoot miahoot);
    Miahoot toEntity(CreateMiahootRequest request);
    Miahoot toEntity(MiahootDTO miahootDTO);

}
