package mx.edu.upqroo.kristenandroid.models;

public class ContentLink extends Content {
    private String url;

    public ContentLink() {
        super();
    }

    public ContentLink(String text, String url) {
        super(text);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
