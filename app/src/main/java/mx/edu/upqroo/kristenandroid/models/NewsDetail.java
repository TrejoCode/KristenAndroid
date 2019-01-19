package mx.edu.upqroo.kristenandroid.models;

import java.util.List;

public class NewsDetail {
    private String newsId;
    private String url;
    private String title;
    private String description;
    private String imageCover;
    private String categories;
    private String date;
    private int newsTypeId;
    private String author;
    private List<Content> contentList;

    public NewsDetail(String newsId, String url,String title, String description, String imageCover,
                      String categories, String date, int newsTypeId, String author,
                      List<Content> contentList) {
        this.newsId = newsId;
        this.url = url;
        this.title = title;
        this.description = description;
        this.imageCover = imageCover;
        this.categories = categories;
        this.date = date;
        this.newsTypeId = newsTypeId;
        this.author = author;
        this.contentList = contentList;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNewsTypeId() {
        return newsTypeId;
    }

    public void setNewsTypeId(int newsTypeId) {
        this.newsTypeId = newsTypeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }
}
