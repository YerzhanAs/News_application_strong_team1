package kz.news.repository;

import kz.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findByNewsSourceId(Long sourceId, Pageable pageable);

    Page<News> findByNewsTopicId(Long topicId, Pageable pageable);
}
