package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeCategoryDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("category_name")
    private String categoryName;
}