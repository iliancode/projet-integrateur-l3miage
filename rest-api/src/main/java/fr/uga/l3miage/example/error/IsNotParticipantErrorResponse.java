package fr.uga.l3miage.example.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@JsonTypeName(IsNotParticipantErrorResponse.TYPE_NAME)
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true, exclude = "errorCodeSwaggerDocumentation")
public class IsNotParticipantErrorResponse extends ErrorResponse{

    protected static final String TYPE_NAME = "IS_NOT_PARTICIPANT_ERROR";

    @Schema(name = "errorCode", description = "Ce code d'erreur est aussi le discriminant pour le polymorphisme", allowableValues = TYPE_NAME,
            implementation = String.class, accessMode = Schema.AccessMode.READ_WRITE)
    @JsonProperty(access = WRITE_ONLY)
    private final String errorCodeSwaggerDocumentation = "Field used only to generate documentation, don't use it";

    @Schema(description = "requête reçue")
    private final CreateParticipantRequest request;

    @Builder
    @Jacksonized
    public IsNotParticipantErrorResponse(String uri, HttpStatus httpStatus, ErrorCode errorCode, String errorMessage, CreateParticipantRequest request) {
        super(uri, httpStatus, errorCode, errorMessage);
        this.request = request;
    }

}
