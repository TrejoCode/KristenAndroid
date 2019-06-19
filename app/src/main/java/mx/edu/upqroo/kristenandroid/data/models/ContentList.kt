package mx.edu.upqroo.kristenandroid.data.models

import java.util.ArrayList

class ContentList(title: String, ordered: Boolean, element: MutableList<String>) : Content() {
    var title: String? = title
    var isOrdered: Boolean = ordered
    private var elements: MutableList<String> = element

    fun getElements(): List<String> {
        return elements
    }

    fun setElement(element: MutableList<String>) {
        this.elements = element
    }

    fun addElement(element: String) {
        elements.add(element)
    }
}
