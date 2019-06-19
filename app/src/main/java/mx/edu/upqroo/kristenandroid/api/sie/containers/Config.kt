package mx.edu.upqroo.kristenandroid.api.sie.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Config {
    @SerializedName("topic")
    @Expose
    var topic: String? = null
    @SerializedName("topic_general")
    @Expose
    var generalTopic: String? = null
    @SerializedName("url_base")
    @Expose
    var urlBase: String? = null
    @SerializedName("token")
    @Expose
    var token: String? = null
}
