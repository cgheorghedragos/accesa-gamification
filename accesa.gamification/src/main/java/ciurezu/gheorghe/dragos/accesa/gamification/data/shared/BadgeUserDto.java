package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeUserDto implements Serializable {
    private Long id;
    private Integer currentProgress;
    private BadgeDto badge;
    private GamificationUserDto user;

}