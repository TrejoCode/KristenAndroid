package mx.edu.upqroo.kristenandroid.common;

/**
 * <h1>PostTypeHelper</h1>
 * This is used to separate the different kinds of post that can be done.
 */
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
