package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface ReponseMapper {

    /**
     * Cette fonction va faire le mapping d'une entité vers le <b color="yellow">DTO</b> de Reponse
     * @param reponse l'entité à mapper en <b color="yellow">DTO</b>
     * @return le <b color="yellow">DTO</b> d'une entité Réponse
     */
    ReponseDTO toDto(Reponse reponse);

    /**
     * Cette fonction fait le mapping entre une requête de création d'une entité reponse et l'entité elle même.
     * @param request de création d'une entité qui va être  transformée en Reponse
     * @return le Reponse correspondant
     */
    Reponse toEntity(CreateReponseRequest request);
}
