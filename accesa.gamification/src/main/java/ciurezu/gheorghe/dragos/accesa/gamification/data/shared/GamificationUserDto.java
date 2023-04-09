package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamificationUserDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("tokens")
    private Integer tokens;

    @JsonProperty("nr_solved_quests")
    private Integer nrSolvedQuests;
}