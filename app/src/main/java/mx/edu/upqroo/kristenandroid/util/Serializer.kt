package mx.edu.upqroo.kristenandroid.util

import com.google.gson.Gson

import java.lang.reflect.Type

/**
 * <h1>Serializer</h1>
 * This class is used to serialize and deserialize objects
 */
object Serializer {

    /**
     * serialize an object
     * @param obj object to be serialized
     * @param <T> Any object type
     * @return Returns the string of the object serialized
    </T> */
    fun <T> serialize(obj: T): String {
        return Gson().toJson(obj)
    }

    //region serialize
    fun <T> serialize(obj: T, listItemType: Type): String {
        return Gson().toJson(obj, listItemType)
    }
    //endregion

    /**
     * deserialize an object
     * @param json object to be deserialize
     * @param type type of the object to be serialized
     * @param <T> Any object type
     * @return The object deserialize
    </T> */
    @Suppress("UNCHECKED_CAST")
    fun <T> deserialize(json: String, type: Type): T {
        return Gson().fromJson<Any>(json, type) as T
    }
}
