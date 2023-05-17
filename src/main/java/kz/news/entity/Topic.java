package kz.news.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_news_topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_topic")
    private String name;

    @Column(name="description")
    private String description;

    // One-to-Many relationship with News
    @OneToMany(mappedBy = "newsTopic", cascade = CascadeType.ALL)
    private List<News> newsList;
}
