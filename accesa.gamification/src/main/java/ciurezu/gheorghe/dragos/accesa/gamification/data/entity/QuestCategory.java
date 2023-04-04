package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "quest_category")
public class QuestCategory {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter @Getter
    @OneToMany(mappedBy = "questCategory")
    private Set<Quest> quest;
}
