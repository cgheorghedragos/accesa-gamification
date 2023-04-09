package ciurezu.gheorghe.dragos.accesa.gamification.data.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "quest_category")
@Setter @Getter
public class QuestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "questCategory")
    private Set<Quest> quest;
}
