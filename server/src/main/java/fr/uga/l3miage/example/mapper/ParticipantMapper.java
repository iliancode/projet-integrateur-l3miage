package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.response.ParticipantDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantDTO entityToDTO(Participant participant);

    Collection<ParticipantDTO> entityToDTO(Iterable<Participant> participants);

    Participant dtoToEntity(ParticipantDTO participant);

    Collection<Participant> dtoToEntity(Iterable<ParticipantDTO> participants);
}
