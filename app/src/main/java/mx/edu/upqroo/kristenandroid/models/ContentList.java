package mx.edu.upqroo.kristenandroid.models;

import java.util.ArrayList;
import java.util.List;

public class ContentList extends Content {
    private String title;
    private boolean ordered;
    private int quantity;
    private List<String> elements;

    public ContentList() {
        super();
    }

    public ContentList(String title, int quantity, boolean ordered, List<String> element) {
        this.title = title;
        this.quantity = quantity;
        this.ordered = ordered;
        this.elements = element;
    }

    public ContentList(int quantity, String element) {
        this.quantity = quantity;
        this.elements = new ArrayList<>();
        this.elements.add(element);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
