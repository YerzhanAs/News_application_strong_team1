package kz.news.service.Impl;

import kz.news.dto.NewsDto;
import kz.news.dto.NewsSaveDto;
import kz.news.entity.News;
import kz.news.repository.NewsRepository;
import kz.news.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream().map(this::convertToNewsDTO).collect(Collectors.toList());//JACKSON КОНВЕНТИРУЕТ ЭТИ ОБЬЕКТЫ В JSON;
    }

    @Override
    public Page<NewsDto> getAllNewsPage(Pageable pageable) {
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.map(this::convertToNewsDTO);
    }

    @Override
    public Page<NewsDto> getNewsBySourceId(Long sourceId, Pageable pageable) {
        Page<News> newsPage = newsRepository.findByNewsSourceId(sourceId, pageable);
        return newsPage.map(this::convertToNewsDTO);
    }

    @Override
    public Page<NewsDto> getNewsByTopicId(Long topicId, Pageable pageable) {
        Page<News> newsPage = newsRepository.findByNewsTopicId(topicId, pageable);
        return newsPage.map(this::convertToNewsDTO);
    }

    @Override
    public Optional<NewsDto> getNewsById(Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);

        if (optionalNews.isPresent()) {
            News existingNews = optionalNews.get();

            return Optional.of(convertToNewsDTO(existingNews));
        }else {
            return Optional.empty();
        }

    }

    @Override
    public News createNews(NewsSaveDto newsSaveDto) {
        newsSaveDto.setPublishDate(LocalDateTime.now());
        return newsRepository.save(convertToNews(newsSaveDto));
    }

    @Override
    public Optional<NewsSaveDto> updateNews(Long id, NewsSaveDto newsSaveDto) {
        Optional<News> optionalNews = newsRepository.findById(id);
        News newsDto=convertToNews(newsSaveDto);
        if (optionalNews.isPresent()) {
            News existingNews = optionalNews.get();
            existingNews.setNewsSource(newsDto.getNewsSource());
            existingNews.setNewsTopic(newsDto.getNewsTopic());
            existingNews.setContentNews(newsDto.getContentNews());
            existingNews.setTitle(newsDto.getTitle());
            // Update other fields as needed
            return Optional.of(convertToNewsSaveDTO(newsRepository.save(existingNews)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteNews(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    //этот код можно перевести в другой класс

    private News convertToNews(NewsSaveDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }

    private News convertToNews(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }

    private NewsSaveDto convertToNewsSaveDTO(News news){
        return modelMapper.map(news, NewsSaveDto.class);
    }

    private NewsDto convertToNewsDTO(News news){
        return modelMapper.map(news, NewsDto.class);
    }


}
