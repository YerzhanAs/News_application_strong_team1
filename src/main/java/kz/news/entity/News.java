package kz.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="t_news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="contentNews")
    private String contentNews;

    @Column(name="publishDate")
    private LocalDateTime publishDate;

    // Many-to-One relationship with NewsSource
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_source_id")
    @JsonIgnore
    private Source newsSource;


    // Many-to-One relationship with NewsTopic
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_topic_id")
    @JsonIgnore
    private Topic newsTopic;
}
