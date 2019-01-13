package mx.edu.upqroo.kristenandroid.service.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.models.News;

public class NewsListMessage {
    public final List<News> newsList;

    public NewsListMessage(List<News> newsList) {
        this.newsList = newsList;
    }
}
