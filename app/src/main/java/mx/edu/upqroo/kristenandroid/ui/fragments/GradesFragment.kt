package mx.edu.upqroo.kristenandroid.ui.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.helpers.ScrollToTop
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.viewModels.GradesViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class GradesFragment : Fragment() {

    private lateinit var mViewModel: GradesViewModel
    private lateinit var mRecyclerGrade: RecyclerView
    private lateinit var mGradeAdapter: GradesItemAdapter
    private lateinit var mProgress: ProgressBar
    private lateinit var mImageEmptyGrades: ConstraintLayout
    private lateinit var mSwipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(GradesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_grades, container, false)
        mRecyclerGrade = v.findViewById(R.id.recycler_grades)
        mRecyclerGrade.setHasFixedSize(true)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerGrade.layoutManager = LinearLayoutManager(context)
        } else {
            mRecyclerGrade.layoutManager = GridLayoutManager(context, 2)
        }
        mRecyclerGrade.visibility = View.GONE
        mProgress = v.findViewById(R.id.progress_grades)
        mProgress.visibility = View.VISIBLE

        mImageEmptyGrades = v.findViewById(R.id.image_empty_grades)

        mGradeAdapter = context?.let { GradesItemAdapter(it, ArrayList()) }!!
        mRecyclerGrade.adapter = mGradeAdapter

        mSwipeContainer = v.findViewById(R.id.refreshLayout_grades)
        mSwipeContainer.setOnRefreshListener { mViewModel.updateGradesFromService() }

        mViewModel.getGrades(SessionManager.instance.session.userId)
                .observe(this, Observer<List<Grade>> { grades ->
                    if (grades.isEmpty()) {
                        mImageEmptyGrades.visibility = View.VISIBLE
                    } else {
                        mRecyclerGrade.visibility = View.VISIBLE
                        mGradeAdapter.setData(grades)
                        mImageEmptyGrades.visibility = View.GONE
                    }
                    mSwipeContainer.isRefreshing = false
                    mProgress.visibility = View.GONE
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
    fun scrollOnTop(who: ScrollToTop) {
        if (who.id == R.id.grades) {
            mRecyclerGrade.smoothScrollToPosition(0)
        }
    }
}
