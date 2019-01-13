package mx.edu.upqroo.kristenandroid.services.sie;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import mx.edu.upqroo.kristenandroid.services.AuthenticationInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SieApiClient {
    private static final String BASE_URL = "http://api.upqroo.edu.mx/kristensie/";
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient().newBuilder()
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    /**
     * Creates a service using the retrofit builder.
     * @param serviceClass Class
     * @param <S> S
     * @return Returns the service
     */
    static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
