package kz.news.controller;

import kz.news.dto.TopicDto;
import kz.news.entity.Topic;
import kz.news.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news-topic")
public class TopicController {

    private  final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllNewsTopics() {
        List<TopicDto> topicList = topicService.findAllTopic();
        return ResponseEntity.ok(topicList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getNewsTopicById(@PathVariable("id") Long id) {
        Optional<TopicDto> topic = topicService.findById(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewsTopic(@RequestBody TopicDto topicDto) {
        topicService.saveTopic(topicDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable("id") Long id, @RequestBody TopicDto topicDto) {
        Optional<TopicDto> updatedTopic = topicService.updateTopic(id, topicDto);
        return updatedTopic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        boolean deleted = topicService.deleteTopic(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
