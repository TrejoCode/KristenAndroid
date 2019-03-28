package mx.edu.upqroo.kristenandroid.database.entities;

import java.util.ArrayList;
import java.util.List;

public class ContentGallery extends Content {
    private List<String> images;

    public ContentGallery() {
        super();
    }

    public ContentGallery(List<String> images) {
        this.images = images;
    }

    public ContentGallery(int quantity, String image) {
        this.images = new ArrayList<>();
        this.images.add(image);
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
