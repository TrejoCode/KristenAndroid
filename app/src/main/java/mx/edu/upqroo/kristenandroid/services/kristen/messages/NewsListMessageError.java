package mx.edu.upqroo.kristenandroid.services.kristen.messages;

public class NewsListMessageError {
    private String error;

    public NewsListMessageError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
