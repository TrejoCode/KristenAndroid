package mx.edu.upqroo.kristenandroid.data.models;

public class NotificationLoaded {
    private boolean general;
    private boolean career;

    public NotificationLoaded() {

    }

    public boolean isGeneral() {
        return general;
    }

    public void setGeneral(boolean general) {
        this.general = general;
    }

    public boolean isCareer() {
        return career;
    }

    public void setCareer(boolean career) {
        this.career = career;
    }
}
