package fr.uga.l3miage.example.error.errorResponse;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.uga.l3miage.example.error.*;
import fr.uga.l3miage.example.error.alreadyUseErrorResponse.DescriptionAlreadyUseErrorResponse;
import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.MiahootEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.ParticipantEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.TestEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.MiahootNotFoundErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.ParticipantNotFoundErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.TestNotFoundErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

/**
 * Permet de renvoyer toujours un message d'erreur générique avec des champs obligatoires communs, car tous les messages ont pour classe mère
 * cette classe abstraite, donc par <b>inversion de dépendance</b> le client aura une <b>ErrorResponse</b><br>
 * <br>
 * Les annotations :
 * <ul>
 *     <li>{@link JsonTypeInfo} permet de définir les informations utiles pour la désérialisation d'un json correspondant à une errorResponse</li>
 *     <li>{@link JsonSubTypes} permet de définir tous les types possibles de classe fille de ErrorResponse
 *     Les annotations internes :
 *      <ul>
 *          <li>{@link JsonSubTypes.Type} permet de définir les types possibles qui héritent de la classe ErrorResponse</li>
 *      </ul>
 *     </li>
 *     <li>{@link SuperBuilder} permet de créer un builder à une classe qui va être redéfini, et qui va être utilisé par la classe fille. Voir la doc <a href="https://projectlombok.org/features/experimental/SuperBuilder">projetlombok.org/features/SuperBuilder</a></li>
 *     <li>{@link Data} est une annotation raccourcie pour plusieurs annotations de lombok<br>Aller voir la doc sur <a href="https://projectlombok.org/features/Data">projetlombok.org/features/data</a></a></li></li>
 * </ul>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "errorCode", visible = true,
        defaultImpl = MiahootErrorResponse.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MiahootEntityNotDeletedErrorResponse.class),
        @JsonSubTypes.Type(value = MiahootNotFoundErrorResponse.class)
})
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public abstract class MiahootErrorResponse {
    @Schema(description = "endpoint appelé", example = "/miahoots")
    private final String uri;
    @Schema(description = "code http de la réponse", example = "404")
    private final HttpStatus httpStatus;
    @Schema(description = "code de l'erreur", example="MIAHOOT_NOT_FOUND_ERROR")
    private final ErrorCode errorCode;
    @Schema(description = "message d'erreur")
    private final String errorMessage;

    protected MiahootErrorResponse(final ErrorCode errorCode){
        this(null,null,errorCode,null);
    }
}
