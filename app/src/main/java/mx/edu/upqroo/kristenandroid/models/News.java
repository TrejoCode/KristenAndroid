package mx.edu.upqroo.kristenandroid.models;

public class News {
    private String title;
    private String description;
    private String category;
    private String coverUrl;
    private String date;

    public News() {

    }

    public News(String title, String description, String category, String coverUrl, String date) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.coverUrl = coverUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
