package mx.edu.upqroo.kristenandroid.data.models

import java.util.ArrayList

class ContentGallery(private var images: MutableList<String>) : Content() {

    fun getImages(): List<String> {
        return images
    }

    fun setImages(images: ArrayList<String>) {
        this.images = images
    }

    fun addImage(image: String) {
        images.add(image)
    }
}
