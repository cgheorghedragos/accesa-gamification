package ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeForRewardDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeWithBadgesDto {
    @JsonIgnore
    private Long id;

    @JsonProperty("tokens")
    private Integer tokens;

    @JsonProperty("badge_for_reward")
    private List<SimpleBadgeDto> badgeForRewards;
}
