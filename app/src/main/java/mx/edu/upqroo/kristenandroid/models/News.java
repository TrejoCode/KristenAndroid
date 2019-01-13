package mx.edu.upqroo.kristenandroid.models;

public class News {
    private int id;
    private String url;
    private int postType;
    private String title;
    private String description;
    private String category;
    private String coverUrl;
    private String date;

    public News() {

    }

    public News(int id, String url, int postType, String title, String description, String category,
                String coverUrl, String date) {
        this.id = id;
        this.url = url;
        this.postType = postType;
        this.title = title;
        this.description = description;
        this.category = category;
        this.coverUrl = coverUrl;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
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
