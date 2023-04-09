package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Quest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto implements Serializable {

    private Long id;

    @JsonProperty("img_url")
    private String imgUrl;

    @JsonProperty("response_message")
    private String responseMessage;

    @JsonProperty("user")
    private GamificationUserDto user;
}