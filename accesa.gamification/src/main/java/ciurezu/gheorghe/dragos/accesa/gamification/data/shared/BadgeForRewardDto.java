package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeForReward;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeForRewardDto implements Serializable {
    @JsonIgnore
    private Long id;

    @JsonProperty("badge")
    private BadgeDto badge;

    @JsonProperty("prize")
    private PrizeDto prize;

    @Override
    public String toString() {
        return "BadgeForRewardDto{" +
                "id=" + id +
                ", badge=" + badge +
                ", prize=" + prize +
                '}';
    }
}