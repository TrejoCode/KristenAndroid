package mx.edu.upqroo.kristenandroid.models;

public class SessionLoaded {
    private String user;
    private String password;

    public SessionLoaded() {
        user = "";
        password = "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
