package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @Setter @Getter
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "users_id",nullable = false)
    private GamificationUser user;
}
