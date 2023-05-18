package kz.news.util;


import kz.news.entity.Source;
import kz.news.service.NewsService;
import kz.news.service.SourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewsStatisticsTask implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsStatisticsTask.class);

    private NewsService newsService;
    private SourceService sourceService;
    private String filePath;


    public NewsStatisticsTask(NewsService newsService, SourceService sourceService, String filePath) {
        this.newsService = newsService;
        this.sourceService = sourceService;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        LOGGER.info("Start running thread");
        try {
            List<Source> sources = sourceService.getAllSources();
            Map<String, Integer> statistics = new HashMap<>();

            for (Source source : sources) {
                statistics.put(source.getName(), source.getNewsList().size());
            }

            LOGGER.info("Put data to file from database");
            generateStatisticsFile(statistics, filePath);



        } catch (Exception e) {
            LOGGER.error("Exception in thread");
            e.printStackTrace();
        }
    }


    private void generateStatisticsFile(Map<String, Integer> statistics, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            LOGGER.info("start entry file ");
            for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }
            LOGGER.info("finish entry file");
        }
    }
}
