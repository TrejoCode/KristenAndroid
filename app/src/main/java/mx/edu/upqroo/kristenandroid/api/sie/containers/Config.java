package mx.edu.upqroo.kristenandroid.api.sie.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("topic_general")
    @Expose
    private String generalTopic;
    @SerializedName("url_base")
    @Expose
    private String urlBase;
    @SerializedName("token")
    @Expose
    private String token;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
