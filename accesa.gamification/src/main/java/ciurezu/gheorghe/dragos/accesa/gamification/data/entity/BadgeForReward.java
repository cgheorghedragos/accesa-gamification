package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "badge_for_reward")
public class BadgeForReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @ManyToOne
    @JoinColumn(name = "prize_id")
    private Prize prize;

}
