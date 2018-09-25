package mx.edu.upqroo.kristenandroid.common;

public enum PostTypeHelper {
    EVENT(1),
    NEWS(2),
    WORK(3),
    PLANT(4);

    private int id;

    PostTypeHelper(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
