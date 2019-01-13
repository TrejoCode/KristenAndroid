package mx.edu.upqroo.kristenandroid.models;

public class ContentImage extends Content {
    private String source;

    public ContentImage() {
        super();
    }

    public ContentImage(String text, String source) {
        super(text);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
