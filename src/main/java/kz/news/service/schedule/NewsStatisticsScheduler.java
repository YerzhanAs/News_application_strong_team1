package kz.news.service.schedule;

import kz.news.service.NewsService;
import kz.news.service.SourceService;
import kz.news.util.NewsStatisticsTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class NewsStatisticsScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsStatisticsScheduler.class);
    private NewsService newsService;
    private SourceService sourceService;
    private String filePath;

    @Autowired
    public NewsStatisticsScheduler(NewsService newsService, SourceService sourceService) {
        this.newsService = newsService;
        this.sourceService = sourceService;
        this.filePath = "src/main/resources/schedulfile/file.txt";
    }

    @Scheduled(fixedDelay = 2000)// Runs daily at midnight
    public void runStatisticsTask() {
        LOGGER.info("Start scheduled");
        NewsStatisticsTask task = new NewsStatisticsTask(newsService, sourceService, filePath);
        Thread thread = new Thread(task);
        thread.start();

        try {
            Thread.sleep(24 * 60 * 60 * 1000);
        } catch (InterruptedException e) {
            LOGGER.error("Sleep interrupted");
        }
        LOGGER.info("Finish scheduled");
    }
}
