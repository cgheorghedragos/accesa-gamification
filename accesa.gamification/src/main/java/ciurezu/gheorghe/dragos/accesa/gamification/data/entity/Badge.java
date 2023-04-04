package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "badge")
public class Badge {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "level")
    private Integer level;

    @Getter @Setter
    @Column(name = "max_value")
    private Integer maxValue;

    @Getter @Setter
    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "badge")
    private Set<BadgeUser> badgeUsers;

    @OneToMany(mappedBy = "badge")
    private Set<BadgeForReward> badgeForRewards;

    @ManyToOne
    @JoinColumn(name = "badge_category_id")
    private BadgeCategory badgeCategory;

}
