package mx.edu.upqroo.kristenandroid.managers;

public enum FragmentManager {
    CALENDAR_FRAGMENT("CALENDAR"),
    GRADES_FRAGMENT("GRADES"),
    KARDEX_FRAGMENT("KARDEX"),
    NEWS_FRAGMENT("NEWS"),
    NOTICES_FRAGMENT("NOTICES"),
    SCHEDULE_FRAGMENT("SCHEDULE"),
    USER_FRAGMENT("USER"),
    CONTACT_FRAGMENT("CONTACTS");

    private String mName;

    FragmentManager(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
