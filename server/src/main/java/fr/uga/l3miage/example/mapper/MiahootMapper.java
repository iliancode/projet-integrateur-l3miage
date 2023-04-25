package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.response.MiahootDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface MiahootMapper {
    MiahootDTO entityToDTO(Miahoot miahoot);

    Collection<MiahootDTO> entityToDTO(Iterable<Miahoot> miahoots);

    Miahoot dtoToEntity(MiahootDTO miahoot);

    Collection<Miahoot> dtoToEntity(Iterable<MiahootDTO> miahoots);
}
