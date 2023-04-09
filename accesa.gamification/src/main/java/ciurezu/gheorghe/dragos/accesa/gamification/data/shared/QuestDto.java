package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class QuestDto implements Serializable {
    @JsonIgnore
    private Long id;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("entrance_price")
    private Integer entrancePrice;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("prize")
    private Set<PrizeDto> prize;

    @JsonProperty("quest_category")
    private QuestCategoryDto questCategory;

    @JsonProperty("user")
    private GamificationUserDto user;
}