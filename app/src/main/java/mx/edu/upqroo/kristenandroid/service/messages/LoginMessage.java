package mx.edu.upqroo.kristenandroid.service.messages;

import mx.edu.upqroo.kristenandroid.models.GeneralInfo;

public class LoginMessage {
    private boolean result;
    private GeneralInfo student;
    private String message;

    public LoginMessage(boolean result, GeneralInfo student) {
        this.result = result;
        this.student = student;
    }

    public LoginMessage(boolean result, String message) {
        this.result = result;
        this.message = message;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public GeneralInfo getStudent() {
        return student;
    }

    public void setStudent(GeneralInfo student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
