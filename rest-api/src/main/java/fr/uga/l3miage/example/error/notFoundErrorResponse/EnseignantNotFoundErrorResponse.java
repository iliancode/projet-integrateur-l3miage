package fr.uga.l3miage.example.error.notFoundErrorResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.uga.l3miage.example.error.ErrorCode;
import fr.uga.l3miage.example.error.errorResponse.EnseignantErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@JsonTypeName(EnseignantNotFoundErrorResponse.TYPE_NAME)
@Getter
@ToString(callSuper = true, exclude = "errorCodeSwaggerDocumentation")
@EqualsAndHashCode(callSuper = true)
public class EnseignantNotFoundErrorResponse extends EnseignantErrorResponse {

    protected static final String TYPE_NAME = "ENSEIGNANT_IS_NOT_FOUND";

    @Schema(name = "errorCode", description = "Ce code d'erreur est aussi le discriminant pour le polymorphisme", allowableValues = TYPE_NAME,
            implementation = String.class, accessMode = Schema.AccessMode.READ_WRITE)
    @JsonProperty(access = WRITE_ONLY)
    private final String errorCodeSwaggerDocumentation = "Field used only to generate documentation, don't use it";

    @Schema(description = "l'uid utilisé pour la recherche",example = "STHDJ6457DD")
    private final String uidEnseignant;

    @Builder
    @Jacksonized
    public EnseignantNotFoundErrorResponse(String uri, HttpStatus httpStatus, ErrorCode errorCode, String errorMessage, String uidEnseignant) {
        super(uri, httpStatus, errorCode, errorMessage);
        this.uidEnseignant = uidEnseignant;
    }
}