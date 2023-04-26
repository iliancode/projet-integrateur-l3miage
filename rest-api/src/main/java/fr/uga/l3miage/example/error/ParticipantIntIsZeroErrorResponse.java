package fr.uga.l3miage.example.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@JsonTypeName(ParticipantIntIsZeroErrorResponse.TYPE_NAME)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "errorCodeSwaggerDocumentation")
@Schema(subTypes = ErrorResponse.class)
public class ParticipantIntIsZeroErrorResponse extends ErrorResponse{

    protected static final String TYPE_NAME = "PARTICIPANT_INT_IS_ZERO_ERROR";

    @Schema(name = "errorCode", description = "Ce code d'erreur est aussi le discriminant pour le polymorphisme", allowableValues = TYPE_NAME,
            implementation = String.class, accessMode = Schema.AccessMode.READ_WRITE)
    @JsonProperty(access = WRITE_ONLY)
    private final String errorCodeSwaggerDocumentation = "Field used only to generate documentation, don't use it";

    @Builder
    @Jacksonized
    public ParticipantIntIsZeroErrorResponse(String uri, HttpStatus httpStatus, ErrorCode errorCode, String errorMessage) {
        super(uri, httpStatus, errorCode, errorMessage);
    }
}
