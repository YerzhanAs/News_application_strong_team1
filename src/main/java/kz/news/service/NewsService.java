package kz.news.service;

import kz.news.dto.NewsDto;
import kz.news.dto.NewsSaveDto;
import kz.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Page<NewsDto> getAllNewsPage(Pageable pageable);

    Page<NewsDto> getNewsBySourceId(Long sourceId, Pageable pageable);

    Page<NewsDto> getNewsByTopicId(Long topicId, Pageable pageable);
    List<NewsDto> getAllNews();
    Optional<NewsDto> getNewsById(Long id);
    News createNews(NewsSaveDto newsSaveDto);
    Optional<NewsSaveDto> updateNews(Long id, NewsSaveDto newsSaveDto);
    boolean deleteNews(Long id);
}
