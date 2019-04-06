package mx.edu.upqroo.kristenandroid.api.kristen.messages;

import mx.edu.upqroo.kristenandroid.data.models.NewsDetail;

public class NewsDetailMessage {
    private boolean isSuccessful;
    private NewsDetail newsDetail;

    public NewsDetailMessage() {

    }

    public NewsDetailMessage(boolean isSuccessful, NewsDetail newsDetail) {
        this.isSuccessful = isSuccessful;
        this.newsDetail = newsDetail;
    }

    public NewsDetail getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
