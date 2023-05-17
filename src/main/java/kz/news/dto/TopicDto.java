package kz.news.dto;


import kz.news.entity.News;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class TopicDto {

    private String name;

    private String description;


    private List<News> newsList;
}
