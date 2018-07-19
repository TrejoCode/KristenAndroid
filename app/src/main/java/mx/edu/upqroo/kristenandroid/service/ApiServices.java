package mx.edu.upqroo.kristenandroid.service;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.edu.upqroo.kristenandroid.common.ApiHelper;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiServices {
    public static void getPublicationsList(int career, int page) {
        Retrofit retrofit = ApiHelper.getNewInstance();
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<List<Publicacion>> repos = service.listPublications(career, page);
        repos.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, Response<List<Publicacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Publicacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new NewsListMessage(convertPublicationListToNewsList(data)));
                        }
                        break;
                    case 400:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    private static News convertPublicationToNews(Publicacion publicacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yy", Locale.getDefault());
        return new News(publicacion.getTitulo(), publicacion.getDescripcion(),
                publicacion.getCategorias(), publicacion.getPortada(), formatter.format(publicacion.getFecha()));
    }

    private static List<News> convertPublicationListToNewsList(List<Publicacion> publicacionList) {
        List<News> newsList = new ArrayList<>();
        for (Publicacion p : publicacionList) {
            newsList.add(convertPublicationToNews(p));
        }
        return newsList;
    }
}
