package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "prize")
public class Prize implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tokens")
    private Integer tokens;
    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @OneToMany(mappedBy = "prize")
    private Set<BadgeForReward> badgeForRewards;

    @OneToOne(mappedBy = "prize")
    private Winner winner;
    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", tokens=" + tokens +
                ", quest=" + quest +
                ", badgeForRewards=" + badgeForRewards +
                '}';
    }
}
