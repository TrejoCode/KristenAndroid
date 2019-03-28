package mx.edu.upqroo.kristenandroid.database.entities;

import java.util.ArrayList;
import java.util.List;

public class ContentList extends Content {
    private String title;
    private boolean ordered;
    private List<String> elements;

    public ContentList() {
        super();
    }

    public ContentList(String title, boolean ordered, List<String> element) {
        this.title = title;
        this.ordered = ordered;
        this.elements = element;
    }

    public ContentList(String element) {
        this.elements = new ArrayList<>();
        this.elements.add(element);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElement(List<String> element) {
        this.elements = element;
    }

    public void addElement(String element) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element);
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}
