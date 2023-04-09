package ciurezu.gheorghe.dragos.accesa.gamification.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class GamificationResponse<T> {
    @JsonProperty("payload")
    private T payload;
    @JsonProperty("additional_message")
    private String additionalMessage;
}
