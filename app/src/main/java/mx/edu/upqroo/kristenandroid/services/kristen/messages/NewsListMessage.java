package mx.edu.upqroo.kristenandroid.services.kristen.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.data.models.News;

public class NewsListMessage {
    private boolean successful;
    private final List<News> newsList;

    public NewsListMessage(boolean successful, List<News> newsList) {
        this.successful = successful;
        this.newsList = newsList;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
