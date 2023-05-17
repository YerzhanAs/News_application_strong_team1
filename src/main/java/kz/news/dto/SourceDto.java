package kz.news.dto;

import kz.news.entity.News;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class SourceDto {

    private String name;

    private String url;


    private List<News> newsList;
}
