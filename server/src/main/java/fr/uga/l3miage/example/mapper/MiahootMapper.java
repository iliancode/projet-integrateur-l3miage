package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateFullMiahootRequest;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = QuestionMapper.class)
public interface MiahootMapper {

    Miahoot toEntity(CreateMiahootRequest request);
    Miahoot toEntity(MiahootDTO miahootDTO);
    Miahoot toEntity(CreateFullMiahootRequest request);

    MiahootDTO toDto(Miahoot miahoot);
    List<MiahootDTO> toDto(List<Miahoot> miahoots);


}
