package kz.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.news.entity.Source;
import kz.news.entity.Topic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsSaveDto {

    private String title;

    private String content;

    private LocalDateTime publishDate;

    private Source newsSource;

    private Topic newsTopic;
}
