package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "badge_for_reward")
public class BadgeForReward {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @Setter @Getter
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @ManyToOne
    @Setter @Getter
    @JoinColumn(name = "prize_id")
    private Prize prize;

}
