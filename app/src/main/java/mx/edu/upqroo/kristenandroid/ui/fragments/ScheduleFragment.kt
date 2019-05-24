package mx.edu.upqroo.kristenandroid.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import java.util.ArrayList
import java.util.Objects
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.fabric.sdk.android.services.concurrency.AsyncTask
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.data.database.entities.Day
import mx.edu.upqroo.kristenandroid.viewModels.ScheduleViewModel
import mx.edu.upqroo.kristenandroid.widget.DataWidgetManager

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
        mViewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_schedule, container, false)
        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule)
        recyclerViewSchedule.layoutManager = LinearLayoutManager(v.context)
        recyclerViewSchedule.visibility = View.VISIBLE
        mScheduleAdapter = ScheduleItemAdapter(ArrayList())
        recyclerViewSchedule.adapter = mScheduleAdapter
        mProgress = v.findViewById(R.id.progress_schedule)
        mProgress.visibility = View.VISIBLE
        mImageEmptySchedule = v.findViewById(R.id.image_empty_schedule)

        mSwipeContainer = v.findViewById(R.id.refreshLayout_schedule)
        mSwipeContainer.setOnRefreshListener { mViewModel.updateScheduleFromService() }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mViewModel.getDays(SessionManager.instance.session.userId)
                .observe(this, Observer<List<ScheduleSubject>>{ scheduleSubjects ->
                    DataWidgetManager.updateWidgetAsync(context)
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
}

