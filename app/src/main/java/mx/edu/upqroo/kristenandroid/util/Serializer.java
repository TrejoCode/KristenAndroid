package mx.edu.upqroo.kristenandroid.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * <h1>Serializer</h1>
 * This class is used to serialize and deserialize objects
 */
public class Serializer {

    /**
     * Serialize an object
     * @param obj object to be serialized
     * @param <T> Any object type
     * @return Returns the string of the object serialized
     */
    public static <T> String Serialize(T obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * Deserialize an object
     * @param json object to be deserialize
     * @param type type of the object to be serialized
     * @param <T> Any object type
     * @return The object deserialize
     */
    public static <T> T Deserialize(String json, Type type){
        Gson gson = new Gson();
        return (T)gson.fromJson(json, type);
    }
}
