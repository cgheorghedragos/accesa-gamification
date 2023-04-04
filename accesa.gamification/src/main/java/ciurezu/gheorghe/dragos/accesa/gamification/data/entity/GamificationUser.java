package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
public class GamificationUser {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "username")
    private String username;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "password")
    private String password;

    @Getter @Setter
    @Column(name = "tokens")
    private Integer tokens;

    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private Set<Participant> participants;

    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private Set<BadgeUser> badges;

    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private Set<Quest> quests;
}
