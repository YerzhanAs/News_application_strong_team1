package kz.news.controller;

import kz.news.dto.NewsDto;
import kz.news.dto.NewsSaveDto;
import kz.news.entity.News;
import kz.news.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;


    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

//    GET method for getting list of all news (with pagination)
    @GetMapping("/filter")
    public ResponseEntity<Page<NewsDto>> getAllNews(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<NewsDto> news = newsService.getAllNewsPage(paging);

        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

//    GET method for getting list of news by source id (with pagination);
    @GetMapping("/filter-by-sourceid")
    public ResponseEntity<Page<NewsDto>> getNewsBySourceId(@RequestParam Long sourceId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NewsDto> news = newsService.getNewsBySourceId(sourceId, pageable);
        System.out.println(news);
        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

//    GET method for getting list of news by topic id (with pagination);
    @GetMapping("/filter-by-topicid")
    public ResponseEntity<Page<NewsDto>> getNewsByTopicId(@RequestParam Long topicId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NewsDto> news = newsService.getNewsByTopicId(topicId, pageable);

        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable Long id) {
        Optional<NewsDto> news = newsService.getNewsById(id);
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNews(@RequestBody NewsSaveDto newsSaveDto) {
        newsService.createNews(newsSaveDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsSaveDto> updateNews(@PathVariable Long id, @RequestBody NewsSaveDto newsSaveDto) {
        Optional<NewsSaveDto> updatedNews = newsService.updateNews(id, newsSaveDto);
        return updatedNews.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        boolean deleted = newsService.deleteNews(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
