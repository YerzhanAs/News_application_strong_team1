package kz.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.news.entity.Source;
import kz.news.entity.Topic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDto {

    private String title;

    private String content;

    private LocalDateTime publishDate;


}
