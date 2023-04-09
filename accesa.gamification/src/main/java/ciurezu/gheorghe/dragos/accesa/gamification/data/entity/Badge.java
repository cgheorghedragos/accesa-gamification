package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "badge")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private Integer level;

    @Column(name = "max_value")
    private Integer maxValue;

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
