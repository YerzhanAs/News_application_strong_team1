package kz.news.service;

import kz.news.dto.TopicDto;
import kz.news.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    void saveTopic(TopicDto topicDto);

    List<TopicDto> findAllTopic();

    Optional<TopicDto> updateTopic(Long id, TopicDto topic);

    Optional<TopicDto> findById(Long id);

    boolean deleteTopic(Long id);
}
