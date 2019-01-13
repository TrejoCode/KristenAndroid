package mx.edu.upqroo.kristenandroid.models;

public class ContentVideo extends Content {
    private String id;
    private String server;

    public ContentVideo() {
        super();
    }

    public ContentVideo(String id, String server) {
        this.id = id;
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
