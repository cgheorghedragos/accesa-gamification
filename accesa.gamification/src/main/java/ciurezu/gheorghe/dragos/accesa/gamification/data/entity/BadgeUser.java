package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "badge_user")
public class BadgeUser {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "current_progress")
    private Integer currentProgress;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "users_id",nullable = false)
    private GamificationUser user;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge;
}
