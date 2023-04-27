package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ParticipantMapper {
    /**
     * Cette fonction va faire le mapping d'une entité vers le <b color="yellow">DTO</b> de Participant
     * @param participant l'entité à mapper en <b color="yellow">DTO</b>
     * @return le <b color="yellow">DTO</b> d'une entité Participant
     */
    ParticipantDTO toDto(Participant participant);

    /**
     * Cette fonction fait le mapping entre une requête de création d'une entité participant et l'entité elle même.
     * @param request de création d'une entité qui va être  transformée en Participant
     * @return le Participant correspondant
     */
    Participant toEntity(CreateParticipantRequest request);
}
