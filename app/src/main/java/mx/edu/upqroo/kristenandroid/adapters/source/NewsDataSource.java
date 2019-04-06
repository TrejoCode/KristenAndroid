package mx.edu.upqroo.kristenandroid.adapters.source;

import com.crashlytics.android.Crashlytics;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import mx.edu.upqroo.kristenandroid.data.models.News;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.api.kristen.KristenApiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataSource extends PageKeyedDataSource<Integer, News> {

    private static final int FIRST_PAGE = 0;
    public static final int PAGE_SIZE = 5;
    private final String mFilter = "{\"fields\": {\"contenidos\": false},\"where\": {\"or\": [{\"idCarrera\": 99},{\"idCarrera\": X}]}, \"order\": \"fecha DESC\", \"skip\": Y, \"limit\":  " + PAGE_SIZE + " }";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, News> callback) {
        String filter = mFilter.replace("X", SessionManager.getInstance().getSession().getCareer());
        filter = filter.replace("Y", String.valueOf(FIRST_PAGE));

        KristenApiServices.getInstance().getService().getNews(filter)
                .enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<News>> call,
                                           @NotNull Response<List<News>> response) {
                        switch (response.code()) {
                            case 200:
                                List<News> data = response.body();
                                if (data != null) {
                                    callback.onResult(data, null, FIRST_PAGE + 1);
                                } else {
                                    Crashlytics.log("200 Error data null while getting notices");
                                }
                                break;
                            default:
                                Crashlytics.log(response.code() + "Error code while getting notices");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<News>> call, @NotNull Throwable t) {
                        Crashlytics.log(t.getMessage() + " - Error code while getting notices");
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, News> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, News> callback) {
        String filter = mFilter.replace("X", SessionManager.getInstance().getSession().getCareer());
        filter = filter.replace("Y", String.valueOf(params.key * PAGE_SIZE));

        KristenApiServices.getInstance().getService().getNews(filter)
                .enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<News>> call,
                                           @NotNull Response<List<News>> response) {
                        switch (response.code()) {
                            case 200:
                                List<News> data = response.body();
                                if (data != null) {
                                    callback.onResult(data, params.key + 1);
                                } else {
                                    Crashlytics.log("200 Error data null while getting notices");
                                }
                                break;
                            default:
                                Crashlytics.log(response.code() + "Error code while getting notices");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<News>> call, @NotNull Throwable t) {
                        Crashlytics.log(t.getMessage() + " - Error code while getting notices");
                    }
                });
    }
}