package mx.edu.upqroo.kristenandroid.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject
import mx.edu.upqroo.kristenandroid.helpers.ScrollToTop
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.viewModels.ScheduleViewModel
import mx.edu.upqroo.kristenandroid.widget.DataWidgetManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {

    private lateinit var mViewModel: ScheduleViewModel

    private lateinit var recyclerViewSchedule: RecyclerView
    private lateinit var mScheduleAdapter: ScheduleItemAdapter
    private lateinit var mProgress: ProgressBar
    private lateinit var mSwipeContainer: SwipeRefreshLayout
    private lateinit var mImageEmptySchedule: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_schedule, container, false)
        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule)
        recyclerViewSchedule.layoutManager = LinearLayoutManager(context)
        recyclerViewSchedule.visibility = View.VISIBLE
        mScheduleAdapter = ScheduleItemAdapter(ArrayList())
        recyclerViewSchedule.adapter = mScheduleAdapter
        mProgress = v.findViewById(R.id.progress_schedule)
        mProgress.visibility = View.VISIBLE
        mImageEmptySchedule = v.findViewById(R.id.image_empty_schedule)

        mSwipeContainer = v.findViewById(R.id.refreshLayout_schedule)
        mSwipeContainer.setOnRefreshListener { mViewModel.updateScheduleFromService() }

        mViewModel.getDays(SessionManager.instance.session.userId)
                .observe(this, Observer<List<ScheduleSubject>>{ scheduleSubjects ->
                    context?.let { DataWidgetManager.updateWidgetAsync(it) }
                    if (scheduleSubjects.isEmpty()) {
                        mImageEmptySchedule.visibility = View.VISIBLE
                    } else {
                        recyclerViewSchedule.visibility = View.VISIBLE
                        mScheduleAdapter.setData(scheduleSubjects)
                        mImageEmptySchedule.visibility = View.GONE
                    }
                    mProgress.visibility = View.GONE
                    mSwipeContainer.isRefreshing = false
                })
        return v
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun scrollOnTop(who: ScrollToTop) {
        if (who.id == R.id.schedule) {
            recyclerViewSchedule.smoothScrollToPosition(0)
        }
    }
}

