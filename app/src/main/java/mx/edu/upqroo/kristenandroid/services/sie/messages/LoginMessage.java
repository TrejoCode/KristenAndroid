package mx.edu.upqroo.kristenandroid.services.sie.messages;

import mx.edu.upqroo.kristenandroid.database.entities.UserInformation;

public class LoginMessage {
    private boolean result;
    private UserInformation student;
    private String message;

    public LoginMessage(boolean result, UserInformation student) {
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

    public UserInformation getStudent() {
        return student;
    }

    public void setStudent(UserInformation student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
