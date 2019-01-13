package mx.edu.upqroo.kristenandroid.models;

import java.util.ArrayList;
import java.util.List;

public class ContentGallery extends Content {
    private int quantity;
    private List<String> images;

    public ContentGallery() {
        super();
    }

    public ContentGallery(int quantity, List<String> images) {
        this.quantity = quantity;
        this.images = images;
    }

    public ContentGallery(int quantity, String image) {
        this.quantity = quantity;
        this.images = new ArrayList<>();
        this.images.add(image);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void addImage(String image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }
}
