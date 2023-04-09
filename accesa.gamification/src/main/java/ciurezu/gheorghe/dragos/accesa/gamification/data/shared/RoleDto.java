package ciurezu.gheorghe.dragos.accesa.gamification.data.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Role} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {
    private Long Id;
    private String name;
}