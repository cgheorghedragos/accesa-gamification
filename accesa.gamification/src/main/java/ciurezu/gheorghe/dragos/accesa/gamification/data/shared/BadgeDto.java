package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("max_value")
    private Integer maxValue;

    @JsonProperty("img_url")
    private String imgUrl;

    @JsonProperty("badge_category")
    private BadgeCategoryDto badgeCategory;
}