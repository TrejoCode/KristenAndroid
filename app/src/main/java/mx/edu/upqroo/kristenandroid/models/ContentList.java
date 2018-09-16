package mx.edu.upqroo.kristenandroid.models;

import java.util.ArrayList;

public class ContentList extends Content {
    private boolean ordered;
    private int quantity;
    private ArrayList<String> elements;

    public ContentList() {
        super();
    }

    public ContentList(int quantity, ArrayList<String> element) {
        this.quantity = quantity;
        this.elements = element;
    }

    public ContentList(int quantity, String element) {
        this.quantity = quantity;
        this.elements = new ArrayList<>();
        this.elements.add(element);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> getElements() {
        return elements;
    }

    public void setElement(ArrayList<String> element) {
        this.elements = element;
    }

    public void addElement(String element) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element);
    }
}
