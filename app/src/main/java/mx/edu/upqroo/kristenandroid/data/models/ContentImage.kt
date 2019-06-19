package mx.edu.upqroo.kristenandroid.data.models

class ContentImage : Content {
    var source: String? = null

    constructor() : super() {}

    constructor(text: String, source: String) : super(text) {
        this.source = source
    }
}
