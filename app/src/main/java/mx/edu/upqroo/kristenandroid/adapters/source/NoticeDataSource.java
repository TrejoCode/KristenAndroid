package mx.edu.upqroo.kristenandroid.adapters.source;

import com.crashlytics.android.Crashlytics;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDataSource extends PageKeyedDataSource<Integer, Notice> {

    private static final int FIRST_PAGE = 0;
    public static final int PAGE_SIZE = 10;
    private final String mFilter = "{\"where\": {\"or\": [{\"idCarrera\": 99},{\"idCarrera\": X}]}, \"order\": \"fecha DESC\", \"skip\": Y, \"limit\":  " + PAGE_SIZE + " }";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, Notice> callback) {
        String filter = mFilter.replace("X", SessionManager.getInstance().getSession().getCareer());
        filter = filter.replace("Y", String.valueOf(FIRST_PAGE));

        KristenApiServices.getInstance().getService().getNotices(filter)
                .enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(@NotNull Call<List<Notice>> call,
                                   @NotNull Response<List<Notice>> response) {
                switch (response.code()) {
                    case 200:
                        List<Notice> data = response.body();
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
            public void onFailure(@NotNull Call<List<Notice>> call, @NotNull Throwable t) {
                Crashlytics.log(t.getMessage() + " - Error code while getting notices");
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Notice> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, Notice> callback) {
        String filter = mFilter.replace("X", SessionManager.getInstance().getSession().getCareer());
        filter = filter.replace("Y", String.valueOf(params.key * PAGE_SIZE));

        KristenApiServices.getInstance().getService().getNotices(filter)
                .enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(@NotNull Call<List<Notice>> call,
                                   @NotNull Response<List<Notice>> response) {
                switch (response.code()) {
                    case 200:
                        List<Notice> data = response.body();
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
            public void onFailure(@NotNull Call<List<Notice>> call, @NotNull Throwable t) {
                Crashlytics.log(t.getMessage() + " - Error code while getting notices");
            }
        });
    }
}
