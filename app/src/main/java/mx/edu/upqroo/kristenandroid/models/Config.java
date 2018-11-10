package mx.edu.upqroo.kristenandroid.models;

public class Config {
    private String generalTopic;
    private String userTopic;
    private String calendarAddress;
    private String baseAddress;

    public Config(){

    }

    public Config(String generalTopic, String userTopic,
                  String calendarAddress, String baseAddress){
        this.generalTopic = generalTopic;
        this.userTopic = userTopic;
        this.calendarAddress = calendarAddress;
        this.baseAddress = baseAddress;
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

    public String getCalendarAddress() {
        return calendarAddress;
    }

    public void setCalendarAddress(String calendarAddress) {
        this.calendarAddress = calendarAddress;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }
}
