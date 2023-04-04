package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "is_active")
    private Boolean isActive;

    @Getter @Setter
    @Column(name = "entrance_price")
    private Integer entrancePrice;

    @Getter @Setter
    @Column(name = "title")
    private String title;

    @Getter @Setter
    @Column(name = "description")
    private String description;

    @Getter @Setter
    @OneToMany( mappedBy = "quest")
    private Set<Participant> participants;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "users_id",nullable = false)
    private GamificationUser user;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "quest_id")
    private QuestCategory questCategory;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "prize_id")
    private Prize prize;
}
