package ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestCategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestWithCategoryDto {
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

    @JsonProperty("quest_category")
    private QuestCategoryDto questCategory;
}
