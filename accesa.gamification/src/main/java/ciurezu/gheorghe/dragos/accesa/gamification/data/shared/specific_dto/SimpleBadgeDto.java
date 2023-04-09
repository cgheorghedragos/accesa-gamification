package ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBadgeDto {
    @JsonIgnore
    private Long id;

    @JsonProperty("badge")
    private BadgeDto badge;
}
