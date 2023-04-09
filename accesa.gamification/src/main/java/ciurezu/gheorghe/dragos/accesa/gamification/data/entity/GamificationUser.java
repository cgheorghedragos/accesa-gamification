package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "users")
public class GamificationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nr_solved_quests")
    private Integer nrSolvedQuests;

    @Column(name = "tokens")
    private Integer tokens;

    @OneToMany(mappedBy = "user")
    private Set<Participant> participants;

    @OneToMany(mappedBy = "user")
    private Set<BadgeUser> badges;

    @OneToMany(mappedBy = "user")
    private Set<Quest> quests;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bird_id", referencedColumnName = "id")
    private Bird bird;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
