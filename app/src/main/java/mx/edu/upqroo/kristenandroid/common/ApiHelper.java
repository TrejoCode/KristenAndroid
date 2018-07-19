package mx.edu.upqroo.kristenandroid.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    public static Retrofit getNewInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://kristen.glitch.me/api/")
                .build();
    }
}
