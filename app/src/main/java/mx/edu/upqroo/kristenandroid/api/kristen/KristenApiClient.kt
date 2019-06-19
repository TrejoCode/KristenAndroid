package mx.edu.upqroo.kristenandroid.api.kristen

import android.text.TextUtils

import java.util.concurrent.TimeUnit

import mx.edu.upqroo.kristenandroid.api.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <h1>KristenApiClient</h1>
 * Class that contains all the main logic for the consume of the API.
 */
internal object KristenApiClient {
    private const val BASE_URL = "http://api.upqroo.edu.mx/kristen/"
    //private static final String BASE_URL = "https://kristen-mongodb.glitch.me/";

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient().newBuilder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())

    private var retrofit = builder.build()
    private val httpClient = OkHttpClient.Builder()

    /**
     * Creates a service using the retrofit builder.
     * @param serviceClass Class
     * @param <S> S
     * @return Returns the service
    </S> */
    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    fun <S> createService(serviceClass: Class<S>, authToken: String): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken)

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)

                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }

        return retrofit.create(serviceClass)
    }
}
