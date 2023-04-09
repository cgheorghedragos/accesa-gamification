package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "entrance_price")
    private Integer entrancePrice;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany( mappedBy = "quest")
    private Set<Participant> participants;

    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false)
    private GamificationUser user;

    @ManyToOne
    @JoinColumn(name = "quest_category_id")
    private QuestCategory questCategory;

    @OneToMany(mappedBy = "quest")
    private Set<Prize> prize;

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", entrancePrice=" + entrancePrice +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", participants=" + participants +
                ", user=" + user +
                ", questCategory=" + questCategory +
                ", prize=" + prize +
                '}';
    }
}
