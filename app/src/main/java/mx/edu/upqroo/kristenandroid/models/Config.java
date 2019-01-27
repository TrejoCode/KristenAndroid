package mx.edu.upqroo.kristenandroid.models;

public class Config {
    private String generalTopic;
    private String userTopic;
    private String baseAddress;
    private String userToken;

    public Config(){

    }

    public Config(String generalTopic, String userTopic, String baseAddress, String userToken){
        this.generalTopic = generalTopic;
        this.userTopic = userTopic;
        this.baseAddress = baseAddress;
        this.userToken = userToken;
    }

    public String getGeneralTopic() {
        return generalTopic;
    }

    public void setGeneralTopic(String generalTopic) {
        this.generalTopic = generalTopic;
    }

    public String getUserTopic() {
        return userTopic;
    }

    public void setUserTopic(String userTopic) {
        this.userTopic = userTopic;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
