package kz.news.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_news_source")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_source")
    private String name;

    @Column(name="url_source")
    private String url;

    // One-to-Many relationship with News
    @OneToMany(mappedBy = "newsSource", cascade = CascadeType.ALL)
    private List<News> newsList;

}
