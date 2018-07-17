package mx.edu.upqroo.kristenandroid.models;

public class News {
    private String title;
    private String subtitle;
    private String description;
    private String content;
    private String category;
    private String coverUrl;

    public News() {

    }

    public News(String title, String subtitle, String description,String content,String category,String coverUrl) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.content = content;
        this.category = category;
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
