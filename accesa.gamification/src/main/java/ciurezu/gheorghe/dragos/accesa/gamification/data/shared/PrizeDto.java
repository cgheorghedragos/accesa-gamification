package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrizeDto implements Serializable {
    @JsonIgnore
    private Long id;

    @JsonProperty("tokens")
    private Integer tokens;

    @JsonProperty("quest")
    private QuestDto quest;

    @JsonProperty("badge_for_reward")
    private Set<BadgeForRewardDto> badgeForRewards;
}