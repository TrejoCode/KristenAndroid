package mx.edu.upqroo.kristenandroid.services.sie.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("url_calendario")
    @Expose
    private String urlCalendario;
    @SerializedName("topic_general")
    @Expose
    private String generalTopic;
    @SerializedName("url_base")
    @Expose
    private String urlBase;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrlCalendario() {
        return urlCalendario;
    }

    public void setUrlCalendario(String urlCalendario) {
        this.urlCalendario = urlCalendario;
    }

    public String getGeneralTopic() {
        return generalTopic;
    }

    public void setGeneralTopic(String generalTopic) {
        this.generalTopic = generalTopic;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }
}
