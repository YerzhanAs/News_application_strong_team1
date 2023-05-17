package kz.news.service.Impl;


import kz.news.dto.TopicDto;
import kz.news.entity.Topic;
import kz.news.repository.TopicRepository;
import kz.news.service.TopicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, ModelMapper modelMapper) {
        this.topicRepository = topicRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public void saveTopic(TopicDto topicDto) {
        topicRepository.save(convertToTopic(topicDto));
    }

    @Override
    public List<TopicDto> findAllTopic() {
        return topicRepository.findAll().stream().map(this::convertToTopicDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TopicDto> findById(Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent()) {
            Topic existingSource =  optionalTopic.get();

            return Optional.of(convertToTopicDTO(existingSource));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<TopicDto> updateTopic(Long id, TopicDto topic) {
        Optional<Topic> optionalSource = topicRepository.findById(id);

        if (optionalSource.isPresent()) {
            Topic existingTopic = optionalSource.get();
            existingTopic.setName(topic.getName());
            existingTopic.setDescription(topic.getDescription());

            Topic updatedTopic = topicRepository.save(existingTopic);
            return Optional.of(convertToTopicDTO(updatedTopic));
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteTopic(Long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private TopicDto convertToTopicDTO(Topic topic){
        return modelMapper.map(topic, TopicDto.class);
    }

    private Topic convertToTopic(TopicDto topicDto) {
        return modelMapper.map(topicDto, Topic.class);
    }
}
