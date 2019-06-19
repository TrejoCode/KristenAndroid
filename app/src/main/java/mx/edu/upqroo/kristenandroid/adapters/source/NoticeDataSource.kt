package mx.edu.upqroo.kristenandroid.adapters.source

import androidx.paging.PageKeyedDataSource

import com.crashlytics.android.Crashlytics

import mx.edu.upqroo.kristenandroid.api.kristen.KristenApiServices
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeDataSource : PageKeyedDataSource<Int, Notice>() {
    private val mFilter = "{\"where\": {\"or\": [{\"idCarrera\": 99},{\"idCarrera\": X}]}, \"order\": \"fecha DESC\", \"skip\": Y, \"limit\":  $PAGE_SIZE }"

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, Notice>) {
        var filter = mFilter.replace("X", SessionManager.instance.session.career)
        filter = filter.replace("Y", FIRST_PAGE.toString())

        KristenApiServices.instance.service.getNotices(filter)
                .enqueue(object : Callback<List<Notice>> {
                    override fun onResponse(call: Call<List<Notice>>,
                                            response: Response<List<Notice>>) {
                        if (response.code() == 200) {
                            val data = response.body()
                            if (data != null) {
                                callback.onResult(data, null, FIRST_PAGE + 1)
                            } else {
                                Crashlytics.log("200 Error data null while getting notices")
                            }
                        } else {
                            Crashlytics.log(response.code().toString() + "Error code while getting notices")
                        }
                    }

                    override fun onFailure(call: Call<List<Notice>>, t: Throwable) {
                        Crashlytics.log(t.message + " - Error code while getting notices")
                    }
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Notice>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Notice>) {
        var filter = mFilter.replace("X", SessionManager.instance.session.career)
        filter = filter.replace("Y", (params.key * PAGE_SIZE).toString())

        KristenApiServices.instance.service.getNotices(filter)
                .enqueue(object : Callback<List<Notice>> {
                    override fun onResponse(call: Call<List<Notice>>,
                                            response: Response<List<Notice>>) {
                        if (response.code() == 200) {
                            val data = response.body()
                            if (data != null) {
                                callback.onResult(data, params.key + 1)
                            } else {
                                Crashlytics.log("200 Error data null while getting notices")
                            }
                        } else {
                            Crashlytics.log(response.code().toString() + "Error code while getting notices")
                        }
                    }

                    override fun onFailure(call: Call<List<Notice>>, t: Throwable) {
                        Crashlytics.log(t.message + " - Error code while getting notices")
                    }
                })
    }

    companion object {
        private const val FIRST_PAGE = 0
        const val PAGE_SIZE = 10
    }
}
